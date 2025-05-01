package edu.backend.taskapp.dtos

data class CertificationInput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null,
    var studentDto: StudentInput? = null
)


data class CertificationOutput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null
)