package edu.backend.taskapp.dtos

data class RecommendationInput (
    var id: Long? = null,
    var details: String? = null,
    var student: StudentInput? = null,
    var company: CompanyInput? = null
)

data class RecommendationOutput (
    var id: Long,
    var details: String,
    var student: StudentOutput,
    var company: CompanyOutput
)