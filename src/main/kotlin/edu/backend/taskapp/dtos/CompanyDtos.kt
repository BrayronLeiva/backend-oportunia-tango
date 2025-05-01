package edu.backend.taskapp.dtos

data class CompanyInput(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var history: String? = null,
    var mision: String? = null,
    var vision: String? = null,
    var corporateCultur: String? = null,
    var contact: Int? = null,
    var rating: Double? = null,
    var internshipType: String? = null,
    var user: UserInput? = null,
    var locationCompanies: List<LocationCompanyInput>? = null,
    var ratingCompanyStudents: List<RatingCompanyStudentInput>? = null,
    var recommendations: List<RecommendationInput>? = null,
    var questions: List<QuestionInput>? = null
)

data class CompanyOutput(
    var id: Long,
    var name: String,
    var description: String,
    var history: String,
    var mision: String,
    var vision: String,
    var corporateCultur: String,
    var contact: Int,
    var rating: Double,
    var internshipType: String,
    var user: UserOutput,
    var locationCompanies: List<LocationCompanyOutput>,
    var ratingCompanyStudents: List<RatingCompanyStudentOutput>,
    var recommendations: List<RecommendationOutput>,
    var questions: List<QuestionOutput>
)