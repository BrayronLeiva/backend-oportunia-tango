package edu.backend.taskapp.dtos

data class StudentInput(
    var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationInput>? = null,
    var requests: List<RequestInput>? = null,
    var ratings: List<RatingCompanyStudentInput>? = null,
    var user: UserInput? = null

)

data class StudentOutput(
    var id: Long,
    var name: String,
    var identification: String,
    var personalInfo: String,
    var experience: String,
    var rating: Double,
    var qualifications: List<QualificationOutput>,
    var requests: List<RequestOutput>,
    var ratings: List<RatingCompanyStudentOutput>,
    var user: UserOutput
    )

