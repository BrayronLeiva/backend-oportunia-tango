package edu.backend.taskapp.webservices

import edu.backend.taskapp.dtos.RequestInput
import edu.backend.taskapp.dtos.RequestOutput
import edu.backend.taskapp.services.RequestService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.requests}")
class RequestController(private val requestService: RequestService) {

    @GetMapping
    @ResponseBody
    fun findAll() = requestService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = requestService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody requestInput: RequestInput): RequestOutput? {
        return requestService.create(requestInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody requestInput: RequestInput): RequestOutput? {
        return requestService.update(requestInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        requestService.deleteById(id)
    }
}