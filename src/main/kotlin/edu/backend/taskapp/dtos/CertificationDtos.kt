package edu.backend.taskapp.dtos

data class CertificationInput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null,
    var studentInput: StudentInput? = null
)
data class CertificationCreate(
    val name: String,
    val provider: String,
    val studentId: Long
)
data class CertificationUptade(
    var id: Long? = null,
    val name: String,
    val provider: String,
    val studentId: Long
)

data class CertificationOutput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null
)