package edu.backend.taskapp.DTOs

import edu.backend.taskapp.entities.Qualification
import edu.backend.taskapp.entities.User

data class StudentDto(
    var id: Long? = null,
    var name: String? = null,
    var identification: String? = null,
    var personalInfo: String? = null,
    var experience: String? = null,
    var rating: Double? = null,
    var qualifications: List<QualificationDto>? = null,
    //var requests: List<Request>? = null,
    //var rating: List<RatingCompanyStudentDto>? = null,
    var user: User? = null


    )
