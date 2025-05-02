package edu.backend.taskapp.dtos

data class InternshipLocationInput(
    var idInternshipLocation: Long? = null,
    var locationCompanyId: Long? = null,
    var internshipId: Long? = null,
    var requests: List<RequestInput>? = emptyList()
)

data class InternshipLocationOutput(
    var idInternshipLocation: Long,
    var locationCompany: LocationCompanyOutput,
    var internship: InternshipOutput,
    //var requests: List<RequestOutput>
)