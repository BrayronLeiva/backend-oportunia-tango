package edu.backend.taskapp.entities

import jakarta.persistence.*

@Entity
@Table(name = "internships")
data class Internship(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var details: String,

    @OneToMany(mappedBy = "internship", cascade = [CascadeType.ALL], orphanRemoval = true)
    var internshipLocations: List<InternshipLocation> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Internship) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Internship(id=$id, details='$details')"
    }
}