package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.QuestionInput
import edu.backend.taskapp.dtos.QuestionOutput
import edu.backend.taskapp.dtos.RecommendationInput
import edu.backend.taskapp.dtos.RecommendationOutput
import edu.backend.taskapp.services.QuestionService
import edu.backend.taskapp.services.RecommendationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${url.recommendations}")
class RecommendationController(private val recommendationService: RecommendationService) {
    @GetMapping
    @ResponseBody
    fun findAll() = recommendationService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = recommendationService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody recommendationInput: RecommendationInput) : RecommendationOutput? {
        return recommendationService.create(recommendationInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody recommendationInput: RecommendationInput) : RecommendationOutput? {
        return recommendationService.update(recommendationInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        recommendationService.deleteById(id)
    }
}