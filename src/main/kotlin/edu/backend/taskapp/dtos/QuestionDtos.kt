package edu.backend.taskapp.dtos

data class QuestionOutput(
    var id: Long? = null,
    var question: String? = null,
    var answer: String? = null,
)

data class QuestionInput(
    var id: Long? = null,
    var question: String? = null,
    var answer: String? = null,
    //var company : CompanyDto? = null
)

data class QuestionCreate(
    var question: String? = null,
    var answer: String? = null,
    var companyId : Long
)

data class QuestionUpdate(
    var id: Long? = null,
    var question: String? = null,
    var answer: String? = null,
    //var company : CompanyDto? = null
)
