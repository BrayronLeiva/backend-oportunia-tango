package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.RecommendationInput
import edu.backend.taskapp.dtos.RecommendationOutput
import edu.backend.taskapp.dtos.StudentCreate
import edu.backend.taskapp.dtos.StudentInput
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.dtos.StudentUpdate
import edu.backend.taskapp.services.RecommendationService
import edu.backend.taskapp.services.StudentService
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
@RequestMapping("\${url.students}")
class StudentController(private val studentService: StudentService) {
    @GetMapping
    @ResponseBody
    fun findAll() = studentService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = studentService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody studentCreate: StudentCreate) : StudentOutput? {
        return studentService.create(studentCreate)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody studentUpdate: StudentUpdate) : StudentOutput? {
        return studentService.update(studentUpdate)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        studentService.deleteById(id)
    }
}