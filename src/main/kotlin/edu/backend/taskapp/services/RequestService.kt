package edu.backend.taskapp.services

import edu.backend.taskapp.dtos.RequestInput
import edu.backend.taskapp.dtos.RequestOutput
import edu.backend.taskapp.mappers.RequestMapper
import edu.backend.taskapp.RequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface RequestService {
    fun findAll(): List<RequestOutput>?
    fun findById(id: Long): RequestOutput?
    fun create(requestInput: RequestInput): RequestOutput?
    fun update(requestInput: RequestInput): RequestOutput?
    fun deleteById(id: Long)
}

@Service
class AbstractRequestService(
    @Autowired
    val requestRepository: RequestRepository,
    @Autowired
    val requestMapper: RequestMapper
) : RequestService {

    override fun findAll(): List<RequestOutput>? {
        return requestMapper.requestListToRequestOutputList(
            requestRepository.findAll()
        )
    }

    override fun findById(id: Long): RequestOutput? {
        val request = requestRepository.findById(id)
        if (request.isEmpty) {
            throw NoSuchElementException("The request with the id: $id not found!")
        }
        return requestMapper.requestToRequestOutput(request.get())
    }

    override fun create(requestInput: RequestInput): RequestOutput? {
        val request = requestMapper.requestInputToRequest(requestInput)
        return requestMapper.requestToRequestOutput(
            requestRepository.save(request)
        )
    }

    override fun update(requestInput: RequestInput): RequestOutput? {
        val request = requestRepository.findById(requestInput.id!!)
        if (request.isEmpty) {
            throw NoSuchElementException("The request with the id: ${requestInput.id} not found!")
        }
        val updatedRequest = request.get()
        requestMapper.requestInputToRequest(requestInput, updatedRequest)
        return requestMapper.requestToRequestOutput(
            requestRepository.save(updatedRequest)
        )
    }

    override fun deleteById(id: Long) {
        if (!requestRepository.findById(id).isEmpty) {
            requestRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The request with the id: $id not found!")
        }
    }
}