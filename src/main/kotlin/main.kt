import connectors.Transaction

fun main() {
    Transaction(
        neo4jLogin = "neo4j",
        neo4jPassword = "agb",
        pgLogin = "Cytrus",
        pgPassword = "cytrus"
    ).transfer()
}
