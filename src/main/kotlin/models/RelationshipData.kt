package models

data class RelationshipData(
    val id: Long,
    val startNodeId: Long,
    val endNodeId: Long,
    val type: String,
    val properties: Map<String, Any>
)

