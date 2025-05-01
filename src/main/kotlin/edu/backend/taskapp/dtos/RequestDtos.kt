package edu.backend.taskapp.dtos

data class RequestInput(
    var id: Long? = null,
    var state: Boolean? = null,
    var student: StudentInput? = null,
    var internshipLocations: InternshipLocationInput? = null
)

data class RequestOutput(
    var id: Long,
    var state: Boolean?,
    var student: StudentOutput,
    var internshipLocations: InternshipLocationOutput
)