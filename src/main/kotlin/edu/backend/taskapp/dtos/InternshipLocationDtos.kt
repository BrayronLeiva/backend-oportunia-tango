package edu.backend.taskapp.dtos

data class InternshipLocationInput(
    var id: Long? = null,
    var locationCompany: LocationCompanyInput? = null,
    var internship: InternshipInput? = null,
    var requests: List<RequestInput>? = null
)

data class InternshipLocationOutput(
    var id: Long,
    var locationCompany: LocationCompanyOutput,
    var internship: InternshipOutput,
    var requests: List<RequestOutput>
)