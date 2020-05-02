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
            executeQuery("MATCH (f) RETURN f") { record ->
                record[0].asNode().let { node ->
                    nodes.add(NodeData(node.id(), node.labels(), node.asMap()))
                }
            }
            return nodes
        }

    val relationships: List<RelationshipData>
        get() {
            val rels = mutableListOf<RelationshipData>()
            executeQuery("MATCH ()-[f]->() RETURN f") { record ->
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

    @Throws(Exception::class)
    override fun close() {
        driver.close()
    }

    fun executeQuery(query: String, cb: (Record) -> Unit) {
        driver.session().use { session ->
            session.writeTransaction { tx ->
                val result = tx.run(query)
                while (result.hasNext()) cb(result.next())
            }
            session.close()
        }
    }
}
