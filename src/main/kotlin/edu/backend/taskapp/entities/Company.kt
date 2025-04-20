package edu.backend.taskapp.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "companies")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    var description: String,

    var history: String,

    var mision: String,

    var vision: String,

    var corporateCultur: String,

    var contact: Int,

    var rating: Double,

    var internshipType: String,

    @OneToOne
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id_user")
    var user: User,

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
    var locationCompanies: List<LocationCompany> = mutableListOf(),

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
    var ratingCompanyStudents: List<RatingCompanyStudent> = mutableListOf(),

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
    var recommendations: List<Recommendation> = mutableListOf(),

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
    var questions: List<Question> = mutableListOf()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Company) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Company(id=$id, name='$name', description='$description', contact=$contact, rating=$rating)"
    }
}