package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.dtos.RecommendationInput
import edu.backend.taskapp.dtos.RecommendationOutput
import edu.backend.taskapp.mappers.RecommendationMapper
import edu.backend.taskapp.RecommendationRepository
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.dtos.RecommendationCreate
import edu.backend.taskapp.dtos.RecommendationUpdate
import edu.backend.taskapp.entities.Recommendation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional


interface RecommendationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    fun findAll(): List<RecommendationOutput>?

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    fun findById(id: Long): RecommendationOutput?

    /**
     * Save and flush a Task entity in the database
     * @param recommendationInput
     * @return the user created
     */
    fun create(recommendationInput: RecommendationInput): RecommendationOutput?

    /**
     * Save and flush a Task entity in the database
     * @param recommendationCreate
     * @return the user created
     */
    fun create(recommendationCreate: RecommendationCreate): RecommendationOutput?

    /**
     * Update a Task entity in the database
     * @param recommendationInput the dto input for Task
     * @return the new Task created
     */
    fun update(recommendationInput: RecommendationInput): RecommendationOutput?

    /**
     * Update a Task entity in the database
     * @param recommendationUpdate the dto input for Task
     * @return the new Task created
     */
    fun update(recommendationUpdate: RecommendationUpdate): RecommendationOutput?


    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    fun deleteById(id: Long)
}

@Service
class AbstractRecommendationService(
    @Autowired
    val recommendationRepository: RecommendationRepository,
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val recommendationMapper: RecommendationMapper,

    ) : RecommendationService {
    /**
     * Find all Task
     * @return a list of Users
     */
    override fun findAll(): List<RecommendationOutput>? {
        return recommendationMapper.recommendationListToRecommendationOutputList(
            recommendationRepository.findAll()
        )
    }

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): RecommendationOutput? {
        val recommendation: Optional<Recommendation> = recommendationRepository.findById(id)
        if (recommendation.isEmpty) {
            throw NoSuchElementException(String.format("The recommendation with the id: %s not found!", id))
        }
        return recommendationMapper.recommendationToRecommendationOutput(
            recommendation.get(),
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param recommendationInput
     * @return the user created
     */
    override fun create(recommendationInput: RecommendationInput): RecommendationOutput? {
        val recommendation: Recommendation = recommendationMapper.recommendationInputToRecommendation(recommendationInput)
        return recommendationMapper.recommendationToRecommendationOutput(
            recommendationRepository.save(recommendation)
        )
    }

    /**
     * Save and flush a Task entity in the database
     * @param recommendationCreate
     * @return the user created
     */
    override fun create(recommendationCreate: RecommendationCreate): RecommendationOutput? {
        val company = companyRepository.findById(recommendationCreate.companyId)
            .orElseThrow { IllegalArgumentException("Company not found with ID: ${recommendationCreate.companyId}") }

        val student = studentRepository.findById(recommendationCreate.studentId)
            .orElseThrow { IllegalArgumentException("Student not found with ID: ${recommendationCreate.studentId}") }

        val recommendation: Recommendation = recommendationMapper.recommendationCreateToRecommendation(recommendationCreate)
        recommendation.student = student
        recommendation.company = company

        return recommendationMapper.recommendationToRecommendationOutput(
            recommendationRepository.save(recommendation)
        )
    }

    /**
     * Update a Task entity in the database
     * @param recommendationInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(recommendationInput: RecommendationInput): RecommendationOutput? {
        val recommendation: Optional<Recommendation> = recommendationRepository.findById(recommendationInput.id!!)
        if (recommendation.isEmpty) {
            throw NoSuchElementException(String.format("The recommendation with the id: %s not found!", recommendationInput.id))
        }
        val recommendationUpdated: Recommendation = recommendation.get()
        recommendationMapper.recommendationInputToRecommendation(recommendationInput, recommendationUpdated)
        return recommendationMapper.recommendationToRecommendationOutput(recommendationRepository.save(recommendationUpdated))
    }

    /**
     * Update a Task entity in the database
     * @param recommendationInput the dto input for Task
     * @return the new Task created
     */
    @Throws(NoSuchElementException::class)
    override fun update(recommendationUpdate: RecommendationUpdate): RecommendationOutput? {
        val recommendation: Optional<Recommendation> = recommendationRepository.findById(recommendationUpdate.id!!)
        if (recommendation.isEmpty) {
            throw NoSuchElementException(String.format("The recommendation with the id: %s not found!", recommendationUpdate.id))
        }
        val recommendationUpdatedResult: Recommendation = recommendation.get()
        recommendationMapper.recommendationUptadeToRecommendation(recommendationUpdate, recommendationUpdatedResult)
        return recommendationMapper.recommendationToRecommendationOutput(recommendationRepository.save(recommendationUpdatedResult))
    }

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    @Throws(NoSuchElementException::class)
    override fun deleteById(id: Long) {
        if (!recommendationRepository.findById(id).isEmpty) {
            recommendationRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The recommendation with the id: %s not found!", id))
        }
    }
}
