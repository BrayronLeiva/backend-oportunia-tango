package edu.backend.taskapp.entities

import jakarta.persistence.*

@Entity
@Table(name = "internships_locations")
data class InternshipLocation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "location", nullable = false, referencedColumnName = "id_location")
    var locationCompany: LocationCompany,

    @ManyToOne
    @JoinColumn(name = "internship", nullable = false, referencedColumnName = "id_internship")
    var internship: Internship,

    @OneToMany(mappedBy = "internshipLocations", cascade = [CascadeType.ALL], orphanRemoval = true)
    var requests: List<Request> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InternshipLocation) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "InternshipLocation(id=$id)"
    }
}