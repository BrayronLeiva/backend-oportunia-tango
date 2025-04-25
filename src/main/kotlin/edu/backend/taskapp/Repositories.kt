package edu.backend.taskapp

import edu.backend.taskapp.entities.Certification
import edu.backend.taskapp.entities.Company
import edu.backend.taskapp.entities.Internship
import edu.backend.taskapp.entities.InternshipLocation
import edu.backend.taskapp.entities.LocationCompany
import edu.backend.taskapp.entities.Qualification
import edu.backend.taskapp.entities.Question
import edu.backend.taskapp.entities.RatingCompanyStudent
import edu.backend.taskapp.entities.Recommendation
import edu.backend.taskapp.entities.Student
import edu.backend.taskapp.entities.Request
import edu.backend.taskapp.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, Long> {}
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

@Repository
interface InternshipRepository: JpaRepository<Internship, Long>

@Repository
interface CompanyRepository: JpaRepository<Company, Long>

@Repository
interface LocationCompanyRepository: JpaRepository<LocationCompany, Long>

@Repository
interface InternshipLocationRepository: JpaRepository<InternshipLocation, Long>

@Repository
interface RatingCompanyStudentRepository: JpaRepository<RatingCompanyStudent, Long>

@Repository
interface RequestRepository: JpaRepository<Request, Long>
