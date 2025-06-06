package edu.backend.taskapp.services

import edu.backend.taskapp.InternshipLocationRepository
import edu.backend.taskapp.dtos.RequestInput
import edu.backend.taskapp.dtos.RequestOutput
import edu.backend.taskapp.mappers.RequestMapper
import edu.backend.taskapp.RequestRepository
import edu.backend.taskapp.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface RequestService {
    fun findAll(): List<RequestOutput>?
    fun findById(id: Long): RequestOutput?
    fun create(requestInput: RequestInput): RequestOutput?
    fun update(requestInput: RequestInput): RequestOutput?
    fun deleteById(id: Long)
    fun findByStudentId(id: Long): List<RequestOutput>?
}

@Service
class AbstractRequestService(
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val internshipLocationRepository: InternshipLocationRepository,
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
        val student = studentRepository.findById(requestInput.studentId!!)
            .orElseThrow { NoSuchElementException("Student with ID ${requestInput.studentId} not found") }

        val internshipLocation = internshipLocationRepository.findById(requestInput.internshipLocationId!!)
            .orElseThrow { NoSuchElementException("InternshipLocation with ID ${requestInput.internshipLocationId} not found") }

        val request = requestMapper.requestInputToRequest(requestInput, student, internshipLocation)
        request.student = student
        request.internshipLocation = internshipLocation

        return requestMapper.requestToRequestOutput(
            requestRepository.save(request)
        )
    }

    override fun update(requestInput: RequestInput): RequestOutput? {
        val request = requestRepository.findById(requestInput.idRequest!!)
        if (request.isEmpty) {
            throw NoSuchElementException("The request with the id: ${requestInput.idRequest} not found!")
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


    override fun findByStudentId(id: Long): List<RequestOutput>? {
        val requests = requestRepository.findByStudent_IdStudent(id)
        if (requests.isEmpty()) {
            throw NoSuchElementException("The request with the student id: $id not found!")
        }
        return requestMapper.requestListToRequestOutputList(requests)
    }


}