package edu.backend.taskapp.DTOs

import jakarta.persistence.Id

data class QualificationInput(
    var id: Long? = null,
    var name: String? = null
)

data class QualificationOutput(
    var id: Long? = null,
    var name: String? = null
)
