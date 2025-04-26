package edu.backend.taskapp.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "recommendations")
data class Recommendation(
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var details: String,


    // Entity Relationship
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    var student: Student,

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    var company: Company,


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Recommendation) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Recommendation(id=$id, details='$details', student=$student)"
    }


}