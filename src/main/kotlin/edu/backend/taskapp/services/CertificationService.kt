package edu.backend.taskapp.services

import edu.backend.taskapp.CertificationRepository
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.dtos.CertificationCreate
import edu.backend.taskapp.dtos.CertificationInput
import edu.backend.taskapp.dtos.CertificationOutput
import edu.backend.taskapp.mappers.CertificationMapper
import edu.backend.taskapp.entities.Certification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface CertificationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    fun findAll(): List<CertificationOutput>?

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    fun findById(id: Long): CertificationOutput?

    /**
     * Save and flush a Task entity in the database
     * @param certificationInput
     * @return the user created
     */
    fun create(certificationInput: CertificationInput): CertificationOutput?


    /**
     * Save and flush a Task entity in the database
     * @param certificationInput
     * @return the user created
     */
    fun createFromRequest(request: CertificationCreate): CertificationOutput

    /**
     * Update a Task entity in the database
     * @param certificationInput the dto input for Task
     * @return the new Task created
     */
    fun update(certificationInput: CertificationInput): CertificationOutput?

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    fun deleteById(id: Long)
}

@Service
class AbstractCertificationService(
    @Autowired
    val certificationRepository: CertificationRepository,
    @Autowired
    val certificationMapper: CertificationMapper,
    private val studentRepository: StudentRepository,
) : CertificationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    override fun findAll(): List<CertificationOutput>? {
        return certificationMapper.certificationListToCertificationOutputList(
            certificationRepository.findAll()
        )
    }

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): CertificationOutput? {
        val certification: Optional<Certification> = certificationRepository.findById(id)
        if (certification.isEmpty) {
            throw NoSuchElementException(String.format("The Certification with the id: %s not found!", id))
        }
        return certificationMapper.certificationToCertificationOutput(
            certification.get(),
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param certificationInput
     * @return the user created
     */
    override fun create(certificationInput: CertificationInput): CertificationOutput? {
        val certification: Certification = certificationMapper.certificationInputToCertification(certificationInput)
        return certificationMapper.certificationToCertificationOutput(
            certificationRepository.save(certification)
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param request
     * @return the user created
     */

    override fun createFromRequest(request: CertificationCreate): CertificationOutput {

        val student = studentRepository.findById(request.studentId)
            .orElseThrow { IllegalArgumentException("Student not found with ID: ${request.studentId}") }

        val certification = Certification(
            name = request.name,
            provider = request.provider,
            file_path = "",
            student = student
        )

        val savedCertification = certificationRepository.save(certification)

        return certificationMapper.certificationToCertificationOutput(savedCertification)
    }


    /**
     * Update a Task entity in the database
     * @param certificationInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(certificationInput: CertificationInput): CertificationOutput? {
        val certification: Optional<Certification> = certificationRepository.findById(certificationInput.id!!)
        if (certification.isEmpty) {
            throw NoSuchElementException(String.format("The Certification with the id: %s not found!", certificationInput.id))
        }
        val certificationUpdated: Certification = certification.get()
        certificationMapper.certificationInputToCertification(certificationInput, certificationUpdated)
        return certificationMapper.certificationToCertificationOutput(certificationRepository.save(certificationUpdated))
    }

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    @Throws(NoSuchElementException::class)
    override fun deleteById(id: Long) {
        if (!certificationRepository.findById(id).isEmpty) {
            certificationRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The Certification with the id: %s not found!", id))
        }
    }

}