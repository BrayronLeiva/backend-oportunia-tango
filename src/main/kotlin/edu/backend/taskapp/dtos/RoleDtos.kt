package edu.backend.taskapp.dtos

data class RoleDetails (
    var id: Long? = null,
    var name: String? = null,
    var privileges: List<PrivilegeDetails>? = null,
)