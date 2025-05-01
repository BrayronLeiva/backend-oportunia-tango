package edu.backend.taskapp.dtos

data class PriorityDetails(
    var id:Long? = null,
    var label: String? = null,
)

data class PrivilegeDetails (
    var id: Long? = null,
    var name: String? = null
)