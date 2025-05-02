package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.dtos.RatingCompanyStudentInput
import edu.backend.taskapp.dtos.RatingCompanyStudentOutput
import edu.backend.taskapp.mappers.RatingCompanyStudentMapper
import edu.backend.taskapp.RatingCompanyStudentRepository
import edu.backend.taskapp.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface RatingCompanyStudentService {
    fun findAll(): List<RatingCompanyStudentOutput>?
    fun findById(id: Long): RatingCompanyStudentOutput?
    fun create(ratingCompanyStudentInput: RatingCompanyStudentInput): RatingCompanyStudentOutput?
    fun update(ratingCompanyStudentInput: RatingCompanyStudentInput): RatingCompanyStudentOutput?
    fun deleteById(id: Long)
}

@Service
class AbstractRatingCompanyStudentService(
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val ratingCompanyStudentRepository: RatingCompanyStudentRepository,
    @Autowired
    val ratingCompanyStudentMapper: RatingCompanyStudentMapper
) : RatingCompanyStudentService {

    override fun findAll(): List<RatingCompanyStudentOutput>? {
        return ratingCompanyStudentMapper.ratingCompanyStudentListToRatingCompanyStudentOutputList(
            ratingCompanyStudentRepository.findAll()
        )
    }

    override fun findById(id: Long): RatingCompanyStudentOutput? {
        val ratingCompanyStudent = ratingCompanyStudentRepository.findById(id)
        if (ratingCompanyStudent.isEmpty) {
            throw NoSuchElementException("The rating company student with the id: $id not found!")
        }
        return ratingCompanyStudentMapper.ratingCompanyStudentToRatingCompanyStudentOutput(ratingCompanyStudent.get())
    }

    override fun create(ratingCompanyStudentInput: RatingCompanyStudentInput): RatingCompanyStudentOutput? {
        val student = studentRepository.findById(ratingCompanyStudentInput.studentId!!)
            .orElseThrow { NoSuchElementException("Student with ID ${ratingCompanyStudentInput.studentId} not found") }

        val company = companyRepository.findById(ratingCompanyStudentInput.companyId!!)
            .orElseThrow { NoSuchElementException("Company with ID ${ratingCompanyStudentInput.companyId} not found") }

        val ratingCompanyStudent = ratingCompanyStudentMapper.ratingCompanyStudentInputToRatingCompanyStudent(
            ratingCompanyStudentInput,
            student,
            company
        )
        ratingCompanyStudent.student = student
        ratingCompanyStudent.company = company

        return ratingCompanyStudentMapper.ratingCompanyStudentToRatingCompanyStudentOutput(
            ratingCompanyStudentRepository.save(ratingCompanyStudent)
        )
    }

    override fun update(ratingCompanyStudentInput: RatingCompanyStudentInput): RatingCompanyStudentOutput? {
        val ratingCompanyStudent = ratingCompanyStudentRepository.findById(ratingCompanyStudentInput.idRating!!)
        if (ratingCompanyStudent.isEmpty) {
            throw NoSuchElementException("The rating company student with the id: ${ratingCompanyStudentInput.idRating} not found!")
        }
        val updated = ratingCompanyStudent.get()
        ratingCompanyStudentMapper.ratingCompanyStudentInputToRatingCompanyStudent(ratingCompanyStudentInput, updated)
        return ratingCompanyStudentMapper.ratingCompanyStudentToRatingCompanyStudentOutput(
            ratingCompanyStudentRepository.save(updated)
        )
    }

    override fun deleteById(id: Long) {
        if (!ratingCompanyStudentRepository.findById(id).isEmpty) {
            ratingCompanyStudentRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The rating company student with the id: $id not found!")
        }
    }
}