package connectors

import models.*
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.neo4j.driver.Record

class Neo4jConnector(uri: String?, user: String?, password: String?) : AutoCloseable {
    private val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))
    val nodes: List<NodeData>
        get() {
            val nodes = mutableListOf<NodeData>()
            query("MATCH (f) RETURN f") { record ->
                record[0].asNode().let { node ->
                    nodes.add(NodeData(node.id(), node.labels(), node.asMap()))
                }
            }
            return nodes
        }

    val relationships: List<RelationshipData>
        get() {
            val rels = mutableListOf<RelationshipData>()
            query("MATCH ()-[f]->() RETURN f") { record ->
                record[0].asRelationship().let { rel ->
                    rels.add(
                        RelationshipData(
                            rel.id(),
                            rel.startNodeId(),
                            rel.endNodeId(),
                            rel.type(),
                            rel.asMap()
                        )
                    )
                }
            }
            return rels
        }

    override fun close() { driver.close() }

    fun query(cipher: String, cb: (Record) -> Unit = {}) {
        driver.session().use { session ->
            session.readTransaction { tx ->
                val result = tx.run(cipher)
                while (result.hasNext()) cb(result.next())
            }
            session.close()
        }
    }

    fun getNode(id: Long): NodeData? {
        var node: NodeData? = null
        query("MATCH (n) WHERE id(n) = $id RETURN n") { record -> 
            val neo4Node = record[0].asNode()
            node = NodeData(neo4Node.id(), neo4Node.labels(), neo4Node.asMap())
        } 
        return node
    }

    fun addNode(node: NodeData) {
        val labels = node.labels.joinToString(":")
        var properties = ""
        node.properties.forEach { (key, v) ->
            properties += "$key: \"$v\", "
        }
        properties = properties.substring(0 until properties.length - 2)
        query("CREATE (n: $labels {$properties})")
    }

    fun deleteNode(id: Long) {
        query("MATCH (n) WHERE id(n) = $id DETACH DELETE n")
    }

    fun getRelationship(id: Long): RelationshipData? {
        var rel: RelationshipData? = null
        query("MATCH ()-[f]->() WHERE id(f) = $id RETURN f") { record -> 
            val neo4Rel = record[0].asRelationship()
            rel = RelationshipData(
                neo4Rel.id(), 
                neo4Rel.startNodeId(), 
                neo4Rel.endNodeId(), 
                neo4Rel.type(), 
                neo4Rel.asMap())
        } 
        return rel
    }

    fun addRelationship(relation: RelationshipData) {
        var properties = ""
        relation.properties.forEach { (key, v) ->
            properties += "$key: \"$v\", "
        }
        properties = properties.substring(0 until properties.length - 2)
        query("""MATCH (a),(b) WHERE id(a) = ${relation.startNodeId} 
                 AND id(b) = ${relation.endNodeId}
                 CREATE (a)-[r:${relation.type} {$properties}]->(b)""")
    }

    fun deleteRelationship(id: Long) {
        query("MATCH ()-[f]->() WHERE id(f) = $id DELETE f")
    }
}
