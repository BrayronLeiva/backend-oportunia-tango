package edu.backend.taskapp.dtos

data class LocationCompanyInput(
    var id: Long? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var email: String? = null,
    var contact: Int? = null,
    var company: CompanyInput? = null,
    var internshipLocations: List<InternshipLocationInput>? = null
)

data class LocationCompanyOutput(
    var id: Long,
    var latitude: Double,
    var longitude: Double,
    var email: String,
    var contact: Int,
    var company: CompanyOutput,
    var internshipLocations: List<InternshipLocationOutput>
)