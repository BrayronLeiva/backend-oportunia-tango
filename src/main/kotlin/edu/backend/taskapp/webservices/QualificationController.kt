package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.CertificationInput
import edu.backend.taskapp.dtos.CertificationOutput
import edu.backend.taskapp.dtos.QualificationCreate
import edu.backend.taskapp.dtos.QualificationInput
import edu.backend.taskapp.dtos.QualificationOutput
import edu.backend.taskapp.dtos.QualificationUptade
import edu.backend.taskapp.services.CertificationService
import edu.backend.taskapp.services.QualificationService
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
@RequestMapping("\${url.qualifications}")
class QualificationController(private val qualificationService: QualificationService) {
    @GetMapping
    @ResponseBody
    fun findAll() = qualificationService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = qualificationService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody qualificationCreate: QualificationCreate) : QualificationOutput? {
        return qualificationService.create(qualificationCreate)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody qualificationUptade: QualificationUptade) : QualificationOutput? {
        return qualificationService.update(qualificationUptade)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        qualificationService.deleteById(id)
    }
}