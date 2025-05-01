package edu.backend.taskapp.services

import edu.backend.taskapp.dtos.InternshipLocationInput
import edu.backend.taskapp.dtos.InternshipLocationOutput
import edu.backend.taskapp.mappers.InternshipLocationMapper
import edu.backend.taskapp.InternshipLocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface InternshipLocationService {
    fun findAll(): List<InternshipLocationOutput>?
    fun findById(id: Long): InternshipLocationOutput?
    fun create(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput?
    fun update(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput?
    fun deleteById(id: Long)
}

@Service
class AbstractInternshipLocationService(
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
        val entity = internshipLocationMapper.internshipLocationInputToInternshipLocation(internshipLocationInput)
        return internshipLocationMapper.internshipLocationToInternshipLocationOutput(
            internshipLocationRepository.save(entity)
        )
    }

    override fun update(internshipLocationInput: InternshipLocationInput): InternshipLocationOutput? {
        val existing = internshipLocationRepository.findById(internshipLocationInput.id!!)
        if (existing.isEmpty) {
            throw NoSuchElementException("The internship location with the id: ${internshipLocationInput.id} not found!")
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
}