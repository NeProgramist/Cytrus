package connectors

fun transfer(
    neo4jURL: String = "localhost:7687",
    neo4jLogin: String = "neo4j",
    neo4jPassword: String,
    sqlURL: String = "jdbc:postgresql://localhost:5432/cytrus",
    sqlDriver: String = "org.postgresql.Driver",
    sqlLogin: String,
    sqlPassword: String
) {
    val neo4j = connectors.Neo4jConnector(
        "bolt://$neo4jURL",
        neo4jLogin,
        neo4jPassword
    )

    val nodes = neo4j.nodes
    val rels = neo4j.relationships

    neo4j.close()

    val sqlConnector = SqlConnector(
        sqlLogin,
        sqlPassword,
        sqlDriver,
        sqlURL
    )

    sqlConnector.saveNodes(nodes)
    sqlConnector.saveRelationships(rels)
}
