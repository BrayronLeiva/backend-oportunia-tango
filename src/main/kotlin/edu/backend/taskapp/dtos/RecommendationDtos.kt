package edu.backend.taskapp.dtos

class RecommendationInput {
    var id: Long? = null
    var details: String? = null
    var student: StudentInput? = null
    //var company: CompanyDto? = null
}

class RecommendationOutput {
    var id: Long? = null
    var details: String? = null
    var student: StudentOutput? = null
    //var company: CompanyDto? = null
}