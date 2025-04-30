package edu.backend.taskapp.DTOs

import edu.backend.taskapp.entities.Company

data class QuestionDto(
    var id: Long? = null,
    var question: String? = null,
    var answer: String? = null,
    //var company : CompanyDto? = null
)
