package edu.backend.taskapp

import edu.backend.taskapp.entities.Certification
import edu.backend.taskapp.entities.Qualification
import edu.backend.taskapp.entities.Question
import edu.backend.taskapp.entities.Recommendation
import edu.backend.taskapp.entities.Student
import edu.backend.taskapp.entities.User
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(
    statements = [
        "DELETE FROM public.certifications",
        "DELETE FROM public.recommendations",
        "DELETE FROM public.qualifications",
        "DELETE FROM public.questions",
        "DELETE FROM public.students",
        "DELETE FROM public.companies",
        "DELETE FROM public.users"

    ],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = ["/import-users.sql", "/import-students.sql", "/import-certifications.sql", "/import-qualifications.sql", "/import-companies.sql"
              ,"/import-questions.sql", "/import-recommendations.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class LoadInitData2(
    @Autowired
    val certificationRepository: CertificationRepository,
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val qualificationRepository: QualificationRepository,
    @Autowired
    val questionRepository: QuestionRepository,
    @Autowired
    val companyRepository: CompanyRepository,
    @Autowired
    val recommendationRepository: RecommendationRepository,
    @Autowired
    val userRepository: UserRepository,

) {

    //TEST DE STUDENTS

    @Test
    fun `findAll returns the expected students`() {
        val students = studentRepository.findAll()
        Assertions.assertEquals(3, students.size)
    }

    @Test
    fun `findById returns the correct student`() {
        val student = studentRepository.findById(3).orElseThrow()
        val user5 = userRepository.findById(5).orElseThrow()

        Assertions.assertAll(
            { Assertions.assertEquals(3, student.id) },
            { Assertions.assertEquals("Andres",student.name) },
            { Assertions.assertEquals("3333", student.identification) },
            { Assertions.assertEquals("None",student.personalInfo)},
            { Assertions.assertEquals("None",student.experience)},
            { Assertions.assertEquals(0.0,student.rating)},
            { Assertions.assertEquals(user5,student.user)}
        )
    }

    @Test
    fun `saving new student increments the total`() {

        val company1 = companyRepository.findById(1).orElseThrow()
        val student1 = studentRepository.findById(1).orElseThrow()
        val user1 = userRepository.findById(6).orElseThrow()
        val nuevo = Student(
            4, "Pepe","4444","None",
            "None", 0.0, mutableSetOf(),user1
        )

        studentRepository.save<Student>(nuevo)

        val total = studentRepository.count()
        Assertions.assertEquals(4, total)
    }

    @Test
    fun `deleting student decreases the total`() {
        recommendationRepository.deleteById(3)
        studentRepository.deleteById(3)

        Assertions.assertFalse(studentRepository.existsById(3))
        Assertions.assertEquals(2, studentRepository.count())
    }

    //TEST DE CERTIFICATIONS

    @Test
    fun `findAll returns the expected certifications`() {
        val certs = certificationRepository.findAll()
        Assertions.assertEquals(3, certs.size)
    }

    @Test
    fun `findById returns the correct certification`() {
        val cert = certificationRepository.findById(1).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(1, cert.id) },
            { Assertions.assertEquals("Java SE 11 Developer", cert.name) },
            { Assertions.assertEquals("Oracle", cert.provider) },
            { Assertions.assertEquals(1, cert.student.id) }
        )
    }

    @Test
    fun `saving new certification increments the total`() {
        val student1 = studentRepository.findById(1).orElseThrow()
        val nueva = Certification(
            4,
            name = "Spring Professional",
            provider = "VMware",
            file_path = "/files/springpro.pdf",
            student = student1)

        certificationRepository.save<Certification>(nueva)

        val total = certificationRepository.count()
        Assertions.assertEquals(4, total)
    }

    @Test
    fun `deleting certification decreases the total`() {
        certificationRepository.deleteById(3)
        Assertions.assertFalse(certificationRepository.existsById(3))
        Assertions.assertEquals(2, certificationRepository.count())
    }


    //TESTS PARA QUALIFICATIONS

    @Test
    fun `findAll returns the expected skills`() {
        val skills = qualificationRepository.findAll()
        Assertions.assertEquals(4, skills.size)
    }

    @Test
    fun `findById returns the correct skill`() {
        val skill = qualificationRepository.findById(1).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(1, skill.id) },
            { Assertions.assertEquals("English", skill.name) }
        )
    }

    @Test
    fun `saving new skill increments the total`() {
        val nueva = Qualification(
            5,
            name = "Spanish"
        )

        qualificationRepository.save<Qualification>(nueva)

        val total = qualificationRepository.count()
        Assertions.assertEquals(5, total)
    }

    @Test
    fun `deleting skill decreases the total`() {
        qualificationRepository.deleteById(3)
        Assertions.assertFalse(qualificationRepository.existsById(3))
        Assertions.assertEquals(3, qualificationRepository.count())
    }


    //TEST PARA QUESTIONS

    @Test
    fun `findAll returns the expected questions`() {
        val skills = questionRepository.findAll()
        Assertions.assertEquals(4, skills.size)
    }

    @Test
    fun `findById returns the correct question`() {
        val skill = questionRepository.findById(1).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(1, skill.id) },
            { Assertions.assertEquals("Do you offer night shifts?", skill.question) },
            { Assertions.assertEquals("Yes, just choose the timezone in preferences.", skill.answer) }
        )
    }

    @Test
    fun `saving new question increments the total`() {

        val company1 = companyRepository.findById(1).orElseThrow()
        val nueva = Question(
            5, "English is required?","No",company1
        )

        questionRepository.save<Question>(nueva)

        val total = questionRepository.count()
        Assertions.assertEquals(5, total)
    }

    @Test
    fun `deleting question decreases the total`() {
        questionRepository.deleteById(3)
        Assertions.assertFalse(questionRepository.existsById(3))
        Assertions.assertEquals(3, questionRepository.count())
    }

    // TEST DE RECOMMENDATION

    @Test
    fun `findAll returns the expected recommendations`() {
        val recommds = recommendationRepository.findAll()
        Assertions.assertEquals(3, recommds.size)
    }

    @Test
    fun `findById returns the correct recommendation`() {
        val recommendation = recommendationRepository.findById(3).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(3, recommendation.id) },
            { Assertions.assertEquals("Excelente actitud, proactividad y habilidades técnicas destacables.",
                recommendation.details) },
            { Assertions.assertEquals(3, recommendation.student.id) },
            { Assertions.assertEquals(2,recommendation.company.id)}
        )
    }

    @Test
    fun `saving new recommendation increments the total`() {

        val company1 = companyRepository.findById(1).orElseThrow()
        val student1 = studentRepository.findById(1).orElseThrow()
        val nueva = Recommendation(
            4, "He was so responsable",student1,company1
        )

        recommendationRepository.save<Recommendation>(nueva)

        val total = recommendationRepository.count()
        Assertions.assertEquals(4, total)
    }

    @Test
    fun `deleting recommendation decreases the total`() {
        recommendationRepository.deleteById(3)
        Assertions.assertFalse(recommendationRepository.existsById(3))
        Assertions.assertEquals(2, recommendationRepository.count())
    }


}