package edu.backend.taskapp.entities

import jakarta.persistence.*

@Entity
@Table(name = "ratings_company_student")
data class RatingCompanyStudent(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var rating: Double,

    var type: String,

    var comment: String,

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    var student: Student,

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    var company: Company,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RatingCompanyStudent) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "RatingCompanyStudent(id=$id, rating=$rating, type='$type', comment='$comment')"
    }
}