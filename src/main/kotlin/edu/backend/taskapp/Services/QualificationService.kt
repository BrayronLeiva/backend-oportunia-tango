package edu.backend.taskapp.Services

import edu.backend.taskapp.CertificationRepository
import edu.backend.taskapp.DTOs.CertificationInput
import edu.backend.taskapp.DTOs.CertificationOutput
import edu.backend.taskapp.DTOs.QualificationInput
import edu.backend.taskapp.DTOs.QualificationOutput
import edu.backend.taskapp.Mappers.CertificationMapper
import edu.backend.taskapp.Mappers.QualificationMapper
import edu.backend.taskapp.QualificationRepository
import edu.backend.taskapp.entities.Certification
import edu.backend.taskapp.entities.Qualification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface QualificationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    fun findAll(): List<QualificationOutput>?

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    fun findById(id: Long): QualificationOutput?

    /**
     * Save and flush a Task entity in the database
     * @param certificationInput
     * @return the user created
     */
    fun create(certificationInput: QualificationInput): QualificationOutput?

    /**
     * Update a Task entity in the database
     * @param certificationInput the dto input for Task
     * @return the new Task created
     */
    fun update(certificationInput: QualificationInput): QualificationOutput?

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    fun deleteById(id: Long)
}

@Service
class AbstractQualificationService(
    @Autowired
    val qualificationRepository: QualificationRepository,
    @Autowired
    val qualificationMapper: QualificationMapper,

) : QualificationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    override fun findAll(): List<QualificationOutput>? {
        return qualificationMapper.qualificationListToQualificationOutputList(
            qualificationRepository.findAll()
        )
    }

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): QualificationOutput? {
        val qualification: Optional<Qualification> = qualificationRepository.findById(id)
        if (qualification.isEmpty) {
            throw NoSuchElementException(String.format("The qualificatipm with the id: %s not found!", id))
        }
        return qualificationMapper.qualificationToQualificationOutput(
            qualification.get(),
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param qualificationInput
     * @return the user created
     */
    override fun create(qualificationInput: QualificationInput): QualificationOutput? {
        val qualification: Qualification = qualificationMapper.qualificationInputToQualification(qualificationInput)
        return qualificationMapper.qualificationToQualificationOutput(
            qualificationRepository.save(qualification)
        )
    }

    /**
     * Update a Task entity in the database
     * @param qualificationInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(qualificationInput: QualificationInput): QualificationOutput? {
        val qualification: Optional<Qualification> = qualificationRepository.findById(qualificationInput.id!!)
        if (qualification.isEmpty) {
            throw NoSuchElementException(String.format("The qualificatipm with the id: %s not found!", qualificationInput.id))
        }
        val qualificationUpdated: Qualification = qualification.get()
        qualificationMapper.qualificationInputToQualification(qualificationInput, qualificationUpdated)
        return qualificationMapper.qualificationToQualificationOutput(qualificationRepository.save(qualificationUpdated))
    }

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    @Throws(NoSuchElementException::class)
    override fun deleteById(id: Long) {
        if (!qualificationRepository.findById(id).isEmpty) {
            qualificationRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The qualificatipm with the id: %s not found!", id))
        }
    }

}