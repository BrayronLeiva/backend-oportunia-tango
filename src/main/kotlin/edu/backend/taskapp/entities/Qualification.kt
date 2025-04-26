package edu.backend.taskapp.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "qualifications")
data class Qualification(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qualifications_seq")
    @SequenceGenerator(
        name = "qualifications_seq",
        sequenceName = "qualifications_id_seq",
        allocationSize = 1
    )
    var id: Long? = null,

    var name: String,



) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Qualification) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Qualification(id=$id, name='$name')"
    }


}