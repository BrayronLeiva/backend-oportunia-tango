package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.UserRepository
import edu.backend.taskapp.dtos.CompanyInput
import edu.backend.taskapp.dtos.CompanyOutput
import edu.backend.taskapp.dtos.StudentMatchResult
import edu.backend.taskapp.entities.Company
import edu.backend.taskapp.mappers.CompanyMapper
import edu.backend.taskapp.mappers.StudentMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface CompanyService {
    fun findAll(): List<CompanyOutput>?
    fun findById(id: Long): CompanyOutput?
    fun create(companyInput: CompanyInput): CompanyOutput?
    fun update(companyInput: CompanyInput): CompanyOutput?
    fun deleteById(id: Long)
    suspend fun findRecommendedStudentsByCompany(id: Long): List<StudentMatchResult>
}

@Service
class AbstractCompanyService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val companyMapper: CompanyMapper,
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val studentMapper: StudentMapper,
    @Autowired
    val aiService: AIService
) : CompanyService {

    override fun findAll(): List<CompanyOutput>? {
        return companyMapper.companyListToCompanyOutputList(
            companyRepository.findAll()
        )
    }

    override fun findById(id: Long): CompanyOutput? {
        val company: Optional<Company> = companyRepository.findById(id)
        if (company.isEmpty) {
            throw NoSuchElementException("The company with the id: $id not found!")
        }
        return companyMapper.companyToCompanyOutput(company.get())
    }

    override fun create(companyInput: CompanyInput): CompanyOutput? {
        val user = userRepository.findById(companyInput.userId!!)
            .orElseThrow { NoSuchElementException("User with ID ${companyInput.userId} not found") }

        val company: Company = companyMapper.companyInputToCompany(companyInput, user)
        company.user = user

        return companyMapper.companyToCompanyOutput(
            companyRepository.save(company)
        )
    }

    override fun update(companyInput: CompanyInput): CompanyOutput? {
        val existing: Optional<Company> = companyRepository.findById(companyInput.idCompany!!)
        if (existing.isEmpty) {
            throw NoSuchElementException("The company with the id: ${companyInput.idCompany} not found!")
        }
        val updated: Company = existing.get()
        companyMapper.companyInputToCompany(companyInput, updated)
        return companyMapper.companyToCompanyOutput(
            companyRepository.save(updated)
        )
    }

    override fun deleteById(id: Long) {
        if (!companyRepository.findById(id).isEmpty) {
            companyRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The company with the id: $id not found!")
        }
    }

    /**
     * Get recommended students by company
     * @param id of the Task
     * @return the Task found
     */
    @Throws(java.util.NoSuchElementException::class)
    override suspend fun findRecommendedStudentsByCompany(id: Long): List<StudentMatchResult> {
        val company = companyRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Company $id not found") }

        val students = studentRepository.findStudentsRequestingByCompanyId(id) // By the moment

        // Aquí deberías mapear a tus DTOs CompanyOutput y StudentOutput
        val companyDto = companyMapper.companyToCompanyOutput(company)
        val studentsDtos = studentMapper.studentListToStudentOutputList(students)

        return aiService.matchStudentsWithCompany(companyDto, studentsDtos)
    }


}