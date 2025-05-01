package edu.backend.taskapp.dtos

data class QuestionInput(
    var id: Long? = null,
    var question: String? = null,
    var answer: String? = null,
    var company : CompanyInput? = null
)

data class QuestionOutput(
    var id: Long,
    var question: String,
    var answer: String,
    var company : CompanyOutput
)
