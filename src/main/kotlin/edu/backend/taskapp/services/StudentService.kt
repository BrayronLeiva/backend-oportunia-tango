package edu.backend.taskapp.services

import edu.backend.taskapp.dtos.StudentInput
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.mappers.StudentMapper
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.UserRepository
import edu.backend.taskapp.dtos.StudentCreate
import edu.backend.taskapp.dtos.StudentUpdate
import edu.backend.taskapp.entities.Student
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
     * Save and flush a Task entity in the database
     * @param studentCreate
     * @return the user created
     */
    fun create(studentCreate: StudentCreate): StudentOutput?

    /**
     * Update a Task entity in the database
     * @param studentInput the dto input for Task
     * @return the new Task created
     */
    fun update(studentInput: StudentInput): StudentOutput?


    /**
     * Update a Task entity in the database
     * @param studentUpdate the dto input for Task
     * @return the new Task created
     */
    fun update(studentUpdate: StudentUpdate): StudentOutput?
    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    fun deleteById(id: Long)
}

@Service
class AbstractStudentService(
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val studentMapper: StudentMapper,

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
        val student: Student = studentMapper.studentInputToStudent(studentInput)
        return studentMapper.studentToStudentOutput(
            studentRepository.save(student)
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param studentCreate
     * @return the user created
     */
    override fun create(studentCreate: StudentCreate): StudentOutput? {
        val student: Student = studentMapper.studentCreateToStudent(studentCreate)
        val user = userRepository.findById(studentCreate.userId)
            .orElseThrow { IllegalArgumentException("User not found with ID: ${studentCreate.userId}") }
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
        val student: Optional<Student> = studentRepository.findById(studentInput.id!!)
        if (student.isEmpty) {
            throw NoSuchElementException(String.format("The student with the id: %s not found!", studentInput.id))
        }
        val studentUpdated: Student = student.get()
        studentMapper.studentInputToStudent(studentInput, studentUpdated)
        return studentMapper.studentToStudentOutput(studentRepository.save(studentUpdated))
    }


    /**
     * Update a Task entity in the database
     * @param studentInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(studentUpdate: StudentUpdate): StudentOutput? {
        val student: Optional<Student> = studentRepository.findById(studentUpdate.id!!)
        if (student.isEmpty) {
            throw NoSuchElementException(String.format("The student with the id: %s not found!", studentUpdate.id))
        }
        val studentUpdatedResult: Student = student.get()
        studentMapper.studentUpdateToStudent(studentUpdate, studentUpdatedResult)
        return studentMapper.studentToStudentOutput(studentRepository.save(studentUpdatedResult))
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
}
