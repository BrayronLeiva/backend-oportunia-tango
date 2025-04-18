package edu.backend.taskapp

import edu.backend.taskapp.Entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PriorityRepository: JpaRepository<Priority, Long>

@Repository
interface TaskRepository: JpaRepository<Task, Long>

@Repository
interface StudentRepository: JpaRepository<Student, Long>