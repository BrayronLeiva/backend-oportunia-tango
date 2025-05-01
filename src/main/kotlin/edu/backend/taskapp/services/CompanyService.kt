package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.dtos.CompanyInput
import edu.backend.taskapp.dtos.CompanyOutput
import edu.backend.taskapp.entities.Company
import edu.backend.taskapp.mappers.CompanyMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface CompanyService {
    fun findAll(): List<CompanyOutput>?
    fun findById(id: Long): CompanyOutput?
    fun create(companyInput: CompanyInput): CompanyOutput?
    fun update(companyInput: CompanyInput): CompanyOutput?
    fun deleteById(id: Long)
}

@Service
class AbstractCompanyService(
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val companyMapper: CompanyMapper,
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
        val company: Company = companyMapper.companyInputToCompany(companyInput)
        return companyMapper.companyToCompanyOutput(
            companyRepository.save(company)
        )
    }

    override fun update(companyInput: CompanyInput): CompanyOutput? {
        val existing: Optional<Company> = companyRepository.findById(companyInput.id!!)
        if (existing.isEmpty) {
            throw NoSuchElementException("The company with the id: ${companyInput.id} not found!")
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
}