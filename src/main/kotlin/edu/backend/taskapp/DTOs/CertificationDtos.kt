package edu.backend.taskapp.DTOs

data class CertificationInput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null,
    var studentDto: StudentDto? = null
)


data class CertificationOutput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null
)