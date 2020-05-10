import connectors.transfer

fun main() {
    // From Neo4j to MySQL
//    transfer(
//        neo4jLogin = "neo4j",
//        neo4jPassword = "agb",
//        sqlURL = "jdbc:mysql://localhost:3306/",
//        sqlDriver = "com.mysql.jdbc.Driver",
//        sqlLogin = "cytrus",
//        sqlPassword = "agb"
//    )

    // From Neo4j to PostgreSQL
    transfer(
        neo4jLogin = "neo4j",
        neo4jPassword = "agb",
        sqlURL = "jdbc:postgresql://localhost:5432/cytrus",
        sqlDriver = "org.postgresql.Driver",
        sqlLogin = "agb",
        sqlPassword = "agb"
    )
}
