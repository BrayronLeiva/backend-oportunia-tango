package edu.backend.taskapp.services

import edu.backend.taskapp.dtos.InternshipLocationInput
import edu.backend.taskapp.dtos.InternshipLocationOutput
import edu.backend.taskapp.mappers.InternshipLocationMapper
import edu.backend.taskapp.InternshipLocationRepository
import edu.backend.taskapp.InternshipRepository
import edu.backend.taskapp.LocationCompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface InternshipLocationService {
    fun findAll(): List<InternshipLocationOutput>?
    fun findById(id: Long): InternshipLocationOutput?
    fun create(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput?
    fun update(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput?
    fun deleteById(id: Long)
    fun findByLocationCompanyId(id: Long): List<InternshipLocationOutput>
}

@Service
class AbstractInternshipLocationService(
    @Autowired
    val internshipRepository: InternshipRepository,
    @Autowired
    val locationCompanyRepository: LocationCompanyRepository,
    @Autowired
    val internshipLocationRepository: InternshipLocationRepository,
    @Autowired
    val internshipLocationMapper: InternshipLocationMapper,
) : InternshipLocationService {

    override fun findAll(): List<InternshipLocationOutput>? {
        return internshipLocationMapper.internshipLocationListToInternshipLocationOutputList(
            internshipLocationRepository.findAll()
        )
    }

    override fun findById(id: Long): InternshipLocationOutput? {
        val entity = internshipLocationRepository.findById(id)
        if (entity.isEmpty) {
            throw NoSuchElementException("The internship location with the id: $id not found!")
        }
        return internshipLocationMapper.internshipLocationToInternshipLocationOutput(entity.get())
    }

    override fun create(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput? {
        val internship = internshipRepository.findById(internshipLocationInput.internshipId!!)
            .orElseThrow { NoSuchElementException("Internship with ID ${internshipLocationInput.internshipId} not found") }

        val locationCompany = locationCompanyRepository.findById(internshipLocationInput.locationCompanyId!!)
            .orElseThrow { NoSuchElementException("LocationCompany with ID ${internshipLocationInput.locationCompanyId} not found") }

        val entity = internshipLocationMapper.internshipLocationInputToInternshipLocation(
            internshipLocationInput,
            locationCompany,
            internship
        )
        entity.internship = internship
        entity.locationCompany = locationCompany

        return internshipLocationMapper.internshipLocationToInternshipLocationOutput(
            internshipLocationRepository.save(entity)
        )
    }

    override fun update(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput? {
        val existing = internshipLocationRepository.findById(internshipLocationInput.idInternshipLocation!!)
        if (existing.isEmpty) {
            throw NoSuchElementException("The internship location with the id: ${internshipLocationInput.idInternshipLocation} not found!")
        }
        val entity = existing.get()
        internshipLocationMapper.internshipLocationInputToInternshipLocation(internshipLocationInput, entity)
        return internshipLocationMapper.internshipLocationToInternshipLocationOutput(
            internshipLocationRepository.save(entity)
        )
    }

    override fun deleteById(id: Long) {
        if (!internshipLocationRepository.findById(id).isEmpty) {
            internshipLocationRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The internship location with the id: $id not found!")
        }
    }

    override fun findByLocationCompanyId(id: Long): List<InternshipLocationOutput> {
        val entities = internshipLocationRepository.findByLocationCompany_IdLocationCompany(id)
        return internshipLocationMapper.internshipLocationListToInternshipLocationOutputList(entities)
    }
}