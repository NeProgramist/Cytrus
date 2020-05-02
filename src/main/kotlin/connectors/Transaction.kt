package connectors

class Transaction(
    private val neo4jURL: String = "localhost:7687",
    private val neo4jLogin: String = "neo4j",
    private val neo4jPassword: String,
    private val pgURL: String = "localhost:5432/cytrus",
    private val pgLogin: String,
    private val pgPassword: String
) {
    fun transfer() {
        val neo4j = connectors.Neo4jConnector(
          "bolt://$neo4jURL",
          neo4jLogin,
           neo4jPassword
        )

        val nodes = neo4j.nodes
        val rels = neo4j.relationships

        neo4j.close()

        val postgreConnector = PostgreConnector(
            pgLogin,
            pgPassword,
            "org.postgresql.Driver",
            "jdbc:postgresql://$pgURL"
        )

        postgreConnector.saveNodes(nodes)
        postgreConnector.saveRelationships(rels)
    }
}