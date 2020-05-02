package models

data class NodeData(
    val id: Long,
    val labels: Iterable<String>,
    val properties: Map<String, Any>
)
