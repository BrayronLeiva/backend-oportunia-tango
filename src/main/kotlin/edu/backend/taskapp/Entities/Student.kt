package edu.backend.taskapp.Entities

import edu.backend.taskapp.Priority
import edu.backend.taskapp.Status
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.util.Date


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