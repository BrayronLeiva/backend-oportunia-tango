package edu.backend.taskapp.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    var identification: String,

    var personalInfo: String,

    var experience: String,

    val rating: Double,


    // Entity Relationship
    @ManyToMany
    @JoinTable(
        name = "student_qualification",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "qualification_id")]
    )
    var qualifications: MutableSet<Qualification> = mutableSetOf(),

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    var user: User,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var requests: MutableList<Request> = mutableListOf(),

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var ratings: MutableList<RatingCompanyStudent> = mutableListOf(),

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Student) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Student(id=$id, name='$name', identification='$identification', personalInfo='$personalInfo', experience='$experience', rating=$rating, user=$user)"
    }


}