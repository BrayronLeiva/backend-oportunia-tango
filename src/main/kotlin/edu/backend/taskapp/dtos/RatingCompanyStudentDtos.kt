package edu.backend.taskapp.dtos

data class RatingCompanyStudentInput(
    var id: Long? = null,
    var rating: Double? = null,
    var type: String? = null,
    var comment: String? = null,
    var student: StudentInput? = null,
    var company: CompanyInput? = null
)

data class RatingCompanyStudentOutput(
    var id: Long,
    var rating: Double,
    var type: String,
    var comment: String,
    var student: StudentOutput,
    var company: CompanyOutput
)