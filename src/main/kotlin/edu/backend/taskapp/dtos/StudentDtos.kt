package edu.backend.taskapp.dtos

data class StudentInput(
    var idStudent: Long? = null,
    var nameStudent: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var ratingStudent: Double? = null,
    var userId: Long? = null,
    var qualifications: List<QualificationInput>? = emptyList(),
    var requests: List<RequestInput>? = emptyList(),
    var ratings: List<RatingCompanyStudentInput>? = emptyList(),
)

data class StudentOutput(
    var idStudent: Long,
    var nameStudent: String,
    var identification: String,
    var personalInfo: String,
    var experience: String,
    var ratingStudent: Double,
    var user: UserOutput
    //var qualifications: List<QualificationOutput>,
    //var requests: List<RequestOutput>,
    //var ratings: List<RatingCompanyStudentOutput>,
    )


data class StudentMatchResult(
    val idStudent: Long,
    val nameStudent: String,
    var identification: String,
    var personalInfo: String,
    var experience: String,
    var ratingStudent: Double,
    var user: UserOutput,
    val score: Int,
    val reason: String
)

data class StudentQualificationsOutput(
    var idStudent: Long,
    var nameStudent: String,
    var identification: String,
    var personalInfo: String,
    var experience: String,
    var ratingStudent: Double,
    var user: UserOutput,
    var qualifications: List<QualificationOutput>
    //var requests: List<RequestOutput>,
    //var ratings: List<RatingCompanyStudentOutput>,
)
