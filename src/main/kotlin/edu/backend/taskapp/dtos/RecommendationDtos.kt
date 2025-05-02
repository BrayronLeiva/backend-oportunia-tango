package edu.backend.taskapp.dtos

data class RecommendationInput (
    var id: Long? = null,
    var details: String? = null,
    var student: StudentInput? = null
    //var company: CompanyDto? = null
)

data class RecommendationOutput (
    var id: Long? = null,
    var details: String? = null,
    var student: StudentOutput? = null
    //var company: CompanyDto? = null
)

data class RecommendationCreate (
    var id: Long? = null,
    var details: String? = null,
    var studentId : Long,
    var companyId : Long
)

data class RecommendationUpdate (
    var id: Long? = null,
    var details: String? = null
)