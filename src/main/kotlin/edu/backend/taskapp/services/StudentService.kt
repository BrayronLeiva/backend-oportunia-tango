package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.dtos.StudentInput
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.mappers.StudentMapper
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.UserRepository
import edu.backend.taskapp.dtos.InternshipMatchResult
import edu.backend.taskapp.dtos.StudentMatchResult
import edu.backend.taskapp.entities.Student
import edu.backend.taskapp.mappers.CompanyMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

interface StudentService {
    /**
     * Find all Task
     * @return a list of Users
     */
    fun findAll(): List<StudentOutput>?

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    fun findById(id: Long): StudentOutput?

    /**
     * Save and flush a Task entity in the database
     * @param studentInput
     * @return the user created
     */
    fun create(studentInput: StudentInput): StudentOutput?

    /**
     * Update a Task entity in the database
     * @param studentInput the dto input for Task
     * @return the new Task created
     */
    fun update(studentInput: StudentInput): StudentOutput?

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    fun deleteById(id: Long)

    /**
     * Find the recommended students for a company
     * @param companyId of the Company
     */
    fun getStudentsRequestingForCompany(companyId: Long): List<StudentOutput>
    fun findRecommendedStudentsByCompany(id: Long): List<StudentMatchResult>
}

@Service
class AbstractStudentService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val studentMapper: StudentMapper,
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val companyMapper: CompanyMapper,
    @Autowired
    val aiService: AIService

    ) : StudentService {
    /**
     * Find all Task
     * @return a list of Users
     */
    override fun findAll(): List<StudentOutput>? {
        return studentMapper.studentListToStudentOutputList(
            studentRepository.findAll()
        )
    }

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): StudentOutput? {
        val student: Optional<Student> = studentRepository.findById(id)
        if (student.isEmpty) {
            throw NoSuchElementException(String.format("The student with the id: %s not found!", id))
        }
        return studentMapper.studentToStudentOutput(
            student.get(),
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param studentInput
     * @return the user created
     */
    override fun create(studentInput: StudentInput): StudentOutput? {
        val user = userRepository.findById(studentInput.userId!!)
            .orElseThrow { NoSuchElementException("User with ID ${studentInput.userId} not found") }

        val student = studentMapper.studentInputToStudent(studentInput, user)
        student.user = user

        return studentMapper.studentToStudentOutput(
            studentRepository.save(student)
        )
    }

    /**
     * Update a Task entity in the database
     * @param studentInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(studentInput: StudentInput): StudentOutput? {
        val student: Optional<Student> = studentRepository.findById(studentInput.idStudent!!)
        if (student.isEmpty) {
            throw NoSuchElementException(String.format("The student with the id: %s not found!", studentInput.idStudent))
        }
        val studentUpdated: Student = student.get()
        studentMapper.studentInputToStudent(studentInput, studentUpdated)
        return studentMapper.studentToStudentOutput(studentRepository.save(studentUpdated))
    }

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    @Throws(NoSuchElementException::class)
    override fun deleteById(id: Long) {
        if (!studentRepository.findById(id).isEmpty) {
            studentRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The student with the id: %s not found!", id))
        }
    }

    override fun getStudentsRequestingForCompany(companyId: Long): List<StudentOutput> {
        return studentMapper.studentListToStudentOutputList(
            studentRepository.findStudentsRequestingByCompanyId(companyId)
        )
    }

    /**
     * Get recommended students by company
     * @param id of the Task
     * @return the Task found
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun findRecommendedStudentsByCompany(id: Long): List<StudentMatchResult> {
        val company = companyRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Company $id not found") }

        val students = studentRepository.findStudentsRequestingByCompanyId(id) // By the moment

        // Aquí deberías mapear a tus DTOs CompanyOutput y StudentOutput
        val companyDto = companyMapper.companyToCompanyOutput(company)
        val studentsDtos = studentMapper.studentListToStudentOutputList(students)

        return aiService.matchStudentsWithCompany(companyDto, studentsDtos)
    }

}
