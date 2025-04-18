package edu.backend.taskapp

import edu.backend.taskapp.Entities.Certification
import edu.backend.taskapp.Entities.Qualification
import edu.backend.taskapp.Entities.Question
import edu.backend.taskapp.Entities.Recommendation
import edu.backend.taskapp.Entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PriorityRepository: JpaRepository<Priority, Long>

@Repository
interface TaskRepository: JpaRepository<Task, Long>

@Repository
interface StudentRepository: JpaRepository<Student, Long>

@Repository
interface QuestionRepository: JpaRepository<Question, Long>

@Repository
interface RecommendationRepository: JpaRepository<Recommendation, Long>

@Repository
interface CertificationRepository: JpaRepository<Certification, Long>

@Repository
interface QualificationRepository: JpaRepository<Qualification, Long>