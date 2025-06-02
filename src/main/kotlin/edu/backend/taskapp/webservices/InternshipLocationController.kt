package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.InternshipLocationInput
import edu.backend.taskapp.dtos.InternshipLocationOutput
import edu.backend.taskapp.services.InternshipLocationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.internshipLocations}")
class InternshipLocationController(private val internshipLocationService: InternshipLocationService) {

    @GetMapping
    @ResponseBody
    fun findAll() = internshipLocationService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = internshipLocationService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody internshipLocationInput: InternshipLocationInput): InternshipLocationOutput? {
        return internshipLocationService.create(internshipLocationInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody internshipLocationInput: InternshipLocationInput): InternshipLocationOutput? {
        return internshipLocationService.update(internshipLocationInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        internshipLocationService.deleteById(id)
    }

    @GetMapping("/location/{locationId}")
    @ResponseBody
    fun findByLocationCompanyId(@PathVariable locationId: Long): List<InternshipLocationOutput> {
        return internshipLocationService.findByLocationCompanyId(locationId)
    }
}