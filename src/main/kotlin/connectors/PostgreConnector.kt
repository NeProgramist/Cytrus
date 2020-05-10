package connectors

import connectors.PostgreConnector.Connections.references
import models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PostgreConnector(user: String, password: String, driver: String, URL: String) {
    private val schema = Schema("cytrus")
    init {
        Database.connect(URL, driver, user, password)
        transaction {
            addLogger(StdOutSqlLogger)
            with (SchemaUtils) {
                dropSchema(schema, cascade = true)
                createSchema(schema)
                setSchema(schema)
                create(Nodes, NodeProperties, Connections, ConnectionProperties)
            }
        }
    }

    object Nodes: Table("nodes") {
        val id = long("id")
        override val primaryKey = PrimaryKey(id)
    }

    object NodeProperties: Table("node_properties") {
        val nodeId = (long("node_id").references(Nodes.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE))
        val key = varchar("key", 64)
        val value = varchar("value", 512)
    }

    object Connections: Table("connections") {
        val id = long("id")
        val targetId = (long("target_id").references(Nodes.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE))
        val sourceId = (long("source_id").references(Nodes.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE))
        override val primaryKey = PrimaryKey(Connections.id)
    }

    object ConnectionProperties: Table("connection_properties") {
        val connectionId = (long("connection_id").references(Connections.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE))
        val key = varchar("key", 64)
        val value = varchar("value", 512)
    }

    fun saveNodes(nodes: List<NodeData>) {
        transaction {
            SchemaUtils.setSchema(schema)

            for (node in nodes) {
                Nodes.insert {
                    it[id] = node.id
                }

                node.labels.forEach { l ->
                    NodeProperties.insert {
                        it[nodeId] = node.id
                        it[key] = "LABEL"
                        it[value] = l
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