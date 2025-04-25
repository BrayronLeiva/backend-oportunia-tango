package edu.backend.taskapp.entities

import jakarta.persistence.*

@Entity
@Table(name = "requests")
data class Request(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var state: Boolean,

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    var student: Student,

    @ManyToOne
    @JoinColumn(name = "internship_location_id", nullable = false, referencedColumnName = "id")
    var internshipLocations: InternshipLocation,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Request) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String {
        return "Request(id=$id, student=$student, internship=$internshipLocations, state=$state)"
    }
}