package edu.backend.taskapp.dtos

data class QualificationInput(
    var id: Long? = null,
    var name: String? = null
)

data class QualificationOutput(
    var id: Long? = null,
    var name: String? = null
)

data class QualificationCreate(
    var name: String? = null
)

data class QualificationUptade(
    var id: Long? = null,
    var name: String? = null
)
