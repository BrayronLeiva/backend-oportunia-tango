package edu.backend.taskapp.dtos

import edu.backend.taskapp.entities.User

data class StudentInput(
    var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationInput>? = null,
    //var requests: List<Request>? = null,
    //var rating: List<RatingCompanyStudentDto>? = null,
    //var user: User? = null

)

data class StudentOutput(
    var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationOutput>? = null,
    //var requests: List<Request>? = null,
    //var rating: List<RatingCompanyStudentDto>? = null,
)

data class StudentCreate(
    //var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationInput>? = null,
    var userId: Long
)


data class StudentUpdate(
    var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationInput>? = null
)

