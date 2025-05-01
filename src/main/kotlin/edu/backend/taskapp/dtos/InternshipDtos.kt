package edu.backend.taskapp.dtos

data class InternshipInput(
    var id: Long? = null,
    var details: String? = null,
    var internshipLocations: List<InternshipLocationInput>? = null
)

data class InternshipOutput(
    var id: Long? = null,
    var details: String? = null,
    var internshipLocations: List<InternshipLocationOutput>
)