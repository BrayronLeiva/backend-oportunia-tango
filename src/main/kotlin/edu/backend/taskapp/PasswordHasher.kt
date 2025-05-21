package edu.backend.taskapp

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

// Al insertar un nuevo usuario primero hashear la contraseña
fun main() {
    val encoder = BCryptPasswordEncoder()
    val rawPassword = "sofia123"
    val hashedPassword = encoder.encode(rawPassword)
    println(hashedPassword)
}