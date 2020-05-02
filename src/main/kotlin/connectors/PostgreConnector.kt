package connectors

import models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PostgreConnector(user: String, password: String, driver: String, URL: String) {
    init {
        Database.connect(URL, driver, user, password)
    }

    object Nodes: Table("nodes") {
        val id = long("id")
        override val primaryKey = PrimaryKey(id)
    }

    object NodeProperties: Table("node_properties") {
        val nodeId = long("node_id")
        val key = varchar("key", 64)
        val value = varchar("value", 512)
    }

    object NodeLabels: Table("node_labels") {
        val nodeId = long("node_id")
        val label = varchar("label", 64)
    }

    object Connections: Table("connections") {
        val id = long("id")
        val targetId = long("target_id")
        val sourceId = long("source_id")
    }

    object ConnectionProperties: Table("connection_properties") {
        val connectionId = long("connection_id")
        val key = varchar("key", 64)
        val value = varchar("value", 512)
    }

    fun saveNodes(nodes: List<NodeData>) {
        transaction {

            val schema = Schema("cytrus")
            SchemaUtils.setSchema(schema)
            addLogger(StdOutSqlLogger)

            for (node in nodes) {
                Nodes.insert {
                    it[id] = node.id
                }

                node.labels.forEach { l ->
                    NodeLabels.insert {
                        it[nodeId] = node.id
                        it[label] = l
                    }
                }

                node.properties.forEach { (k, v) ->
                    NodeProperties.insert {
                        it[nodeId] = node.id
                        it[key] = k
                        it[value] = v.toString()
                    }
                }

            }
        }
    }

    fun saveRelationships(connections: List<RelationshipData>) {
        transaction {
            addLogger(StdOutSqlLogger)
            val schema = Schema("cytrus")
            SchemaUtils.setSchema(schema)

            for (connection in connections) {
                Connections.insert {
                    it[id] = connection.id
                    it[sourceId] = connection.startNodeId
                    it[targetId] = connection.endNodeId
                }

                connection.properties.forEach { (k,v) ->
                    ConnectionProperties.insert {
                        it[connectionId] = connection.id
                        it[key] = k
                        it[value] = v.toString()
                    }
                }

                ConnectionProperties.insert {
                    it[connectionId] = connection.id
                    it[key] = "TYPE"
                    it[value] = connection.type
                }
            }
        }
    }
}