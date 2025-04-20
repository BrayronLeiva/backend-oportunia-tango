package edu.backend.taskapp.entities

import jakarta.persistence.*

@Entity
@Table(name = "locations_company")
data class LocationCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var latitude: Double,

    var longitude: Double,

    var email: String,

    var contact: Int,

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id_company")
    var company: Company,

    @OneToMany(mappedBy = "locationCompany", cascade = [CascadeType.ALL], orphanRemoval = true)
    var internshipLocations: MutableList<InternshipLocation> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocationCompany) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "LocationCompany(id=$id, email='$email', latitude=$latitude, longitude=$longitude, contact=$contact)"
    }
}
