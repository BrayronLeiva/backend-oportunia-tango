package edu.backend.taskapp.dtos

import java.util.Date

data class UserInput(
    var id: Long?=null,
    var firstName: String?=null,
    var lastName: String?=null,
    var email: String?=null,
    var password: String?=null,
    var enabled: Boolean?=null,
    var roles: List<RoleDetails>?=null,
)

data class UserCertificationInput(
    var id: Long?=null,
    var username: String,
    var password: String,
)

data class UserLoginInput(
    var username: String,
    var password: String,
)

data class UserResult(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var enabled: Boolean?,
    var tokenExpired: Boolean?,
    var createDate: Date,
    var roles: List<RoleDetails>,
)

data class UserSignUpInput(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
)