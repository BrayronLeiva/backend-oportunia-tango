package edu.backend.taskapp.DTOs

import jakarta.persistence.Id

data class QualificationDto(
    var id: Long? = null,
    var name: String? = null
)
