package edu.backend.taskapp.DTOs

data class CertificationDto(
    var id: Long? = null,
    var name: String? = null,
    var provider: String? = null,
    var file_path: String? = null,
    var studentDto: StudentDto? = null

)
