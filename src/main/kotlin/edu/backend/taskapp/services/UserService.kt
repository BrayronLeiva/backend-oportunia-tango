package edu.backend.taskapp.services

import edu.backend.taskapp.dtos.UserInput
import edu.backend.taskapp.dtos.UserOutput
import edu.backend.taskapp.mappers.UserMapper
import edu.backend.taskapp.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface UserService {
    fun findAll(): List<UserOutput>?
    fun findById(id: Long): UserOutput?
    fun create(userInput: UserInput): UserOutput?
    fun update(userInput: UserInput): UserOutput?
    fun deleteById(id: Long)
}

@Service
class AbstractUserService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val userMapper: UserMapper
) : UserService {

    override fun findAll(): List<UserOutput>? {
        return userMapper.userListToUserOutputList(
            userRepository.findAll()
        )
    }

    override fun findById(id: Long): UserOutput? {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            throw NoSuchElementException("The user with the id: $id not found!")
        }
        return userMapper.userToUserOutput(user.get())
    }

    override fun create(userInput: UserInput): UserOutput? {
        val user = userMapper.userInputToUser(userInput)
        return userMapper.userToUserOutput(
            userRepository.save(user)
        )
    }

    override fun update(userInput: UserInput): UserOutput? {
        val user = userRepository.findById(userInput.id!!)
        if (user.isEmpty) {
            throw NoSuchElementException("The user with the id: ${userInput.id} not found!")
        }
        val updatedUser = user.get()
        userMapper.userInputToUser(userInput, updatedUser)
        return userMapper.userToUserOutput(
            userRepository.save(updatedUser)
        )
    }

    override fun deleteById(id: Long) {
        if (!userRepository.findById(id).isEmpty) {
            userRepository.deleteById(id)
        } else {
            throw NoSuchElementException("The user with the id: $id not found!")
        }
    }
}