package edu.backend.taskapp.dtos

data class CertificationInput(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null,
    var student: StudentInput? = null
)


data class CertificationOutput(
    var id: Long,
    var name: String,
    var provider: String,
    var student: StudentOutput
)