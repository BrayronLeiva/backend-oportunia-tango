package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.CertificationCreate
import edu.backend.taskapp.dtos.CertificationInput
import edu.backend.taskapp.dtos.CertificationOutput
import edu.backend.taskapp.services.CertificationService
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
@RequestMapping("\${url.certifications}")
class CertificationController(private val certificationService: CertificationService) {
    @GetMapping
    @ResponseBody
    fun findAll() = certificationService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = certificationService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody request: CertificationCreate) : CertificationOutput? {
        return certificationService.createFromRequest(request)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody certificationInput: CertificationInput) : CertificationOutput? {
        return certificationService.update(certificationInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        certificationService.deleteById(id)
    }
}