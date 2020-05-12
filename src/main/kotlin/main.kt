import connectors.*
import models.*

fun main() {
    // From Neo4j to MySQL
//   transfer(
//       neo4jLogin = "neo4j",
//       neo4jPassword = "agb",
//       sqlURL = "jdbc:mysql://localhost:3306/",
//       sqlDriver = "com.mysql.jdbc.Driver",
//       sqlLogin = "cytrus",
//       sqlPassword = "agb"
//   )

    // From Neo4j to PostgreSQL
//    transfer(
//        neo4jLogin = "neo4j",
//        neo4jPassword = "agb",
//        sqlURL = "jdbc:postgresql://localhost:5432/cytrus",
//        sqlDriver = "org.postgresql.Driver",
//        sqlLogin = "agb",
//        sqlPassword = "agb"
//    )

    // Usage
//    val connector = Neo4jConnector("bolt://localhost:7687", "neo4j", "agb")
//    connector.addNode(NodeData(
//        1000, listOf("Label1", "Label2"), mapOf("key" to "value", "key2" to "value2")
//    ))
//    connector.close()

    val postgre = SqlConnector("agb", "agb", "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/cytrus")
    postgre.select()
}
