package edu.backend.taskapp.services

import edu.backend.taskapp.InternshipLocationRepository
import edu.backend.taskapp.InternshipRepository
import edu.backend.taskapp.LocationCompanyRepository
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.dtos.InternshipEvaluateOutput
import edu.backend.taskapp.dtos.InternshipInput
import edu.backend.taskapp.dtos.InternshipMatchResult
import edu.backend.taskapp.dtos.InternshipOutput
import edu.backend.taskapp.dtos.LocationRequestDTO
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.mappers.CompanyMapper
import edu.backend.taskapp.mappers.InternshipMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface InternshipService {
    fun findAll(): List<InternshipOutput>?
    fun findById(id: Long): InternshipOutput?
    fun create(internshipInput: InternshipInput): InternshipOutput?
    fun update(internshipInput: InternshipInput): InternshipOutput?
    fun deleteById(id: Long)
    suspend fun findRecommendedInternshipsByStudent(id: Long,  locationRequest: LocationRequestDTO): List<InternshipMatchResult>
}

@Service
class AbstractInternshipService(
    @Autowired
    val internshipRepository: InternshipRepository,
    @Autowired
    val internshipMapper: InternshipMapper,
    @Autowired
    val locationCompanyRepository: LocationCompanyRepository,
    @Autowired
    val internshipLocationRepository: InternshipLocationRepository,
    @Autowired
    val studentService: StudentService,
    @Autowired
    val companyMapper: CompanyMapper,
    @Autowired
    val aiService: AIService

    ) : InternshipService {

    override fun findAll(): List<InternshipOutput>? {
        return internshipMapper.internshipListToInternshipOutputList(
            internshipRepository.findAll()
        )
    }

    override fun findById(id: Long): InternshipOutput? {
        val internship = internshipRepository.findById(id)
        if (internship.isEmpty) {
            throw NoSuchElementException("The internship with the id: $id not found!")
        }
        return internshipMapper.internshipToInternshipOutput(internship.get())
    }

    override fun create(internshipInput: InternshipInput): InternshipOutput? {
        val internship = internshipMapper.internshipInputToInternship(internshipInput)
        return internshipMapper.internshipToInternshipOutput(
            internshipRepository.save(internship)
        )
    }

    override fun update(internshipInput: InternshipInput): InternshipOutput? {
        val internship = internshipRepository.findById(internshipInput.idInternship!!)
        if (internship.isEmpty) {
            throw NoSuchElementException("The internship with the id: ${internshipInput.idInternship} not found!")
        }
        val updated = internship.get()
        internshipMapper.internshipInputToInternship(internshipInput, updated)
        return internshipMapper.internshipToInternshipOutput(
            internshipRepository.save(updated)
        )
    }

    override fun deleteById(id: Long) {
        if (!internshipRepository.findById(id).isEmpty) {
            internshipRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The internship with the id: $id not found!")
        }
    }

    /**
     * Get recommended students by company
     * @param id of the Task
     * @return the Task found
     */
    @Throws(java.util.NoSuchElementException::class)
    override suspend fun findRecommendedInternshipsByStudent(
        studentId: Long,
        locationRequest: LocationRequestDTO
    ): List<InternshipMatchResult> {

        val nearbyLocations = locationCompanyRepository.findLocationsNear(
            locationRequest.latitude,
            locationRequest.longitude,
            radiusKm = 30.0
        )

        val studentDto = studentService.findById(studentId)

        val internshipLocationList = nearbyLocations
            .flatMap { location ->
                internshipLocationRepository.findByLocationCompany(location)
            }

        val internshipEvaluateList = internshipLocationList.mapNotNull { internshipLocation ->
            val internship = internshipLocation.internship ?: return@mapNotNull null
            val company = internshipLocation.locationCompany?.company ?: return@mapNotNull null
            val companyDto = companyMapper.companyToCompanyOutput(company)

            InternshipEvaluateOutput(
                idInternship = internship.idInternship,
                details = internship.details,
                company = companyDto
            )
        }

        return aiService.recommendInternshipsForStudent(studentDto!!, internshipEvaluateList)
    }
}