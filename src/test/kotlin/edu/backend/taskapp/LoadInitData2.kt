package edu.backend.taskapp

import edu.backend.taskapp.entities.Certification
import edu.backend.taskapp.entities.Qualification
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
        "DELETE FROM public.students",
        "DELETE FROM public.users",
        "DELETE FROM public.qualifications"
    ],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = ["/import-users.sql", "/import-students.sql", "/import-certifications.sql", "/import-qualifications.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class LoadInitData2(
    @Autowired
    val certificationRepository: CertificationRepository,
    @Autowired
    val studentRepository: StudentRepository,
    @Autowired
    val qualificationRepository: QualificationRepository
) {

    @Test
    fun `findAll devuelve las certificaciones esperadas`() {
        val certs = certificationRepository.findAll()
        Assertions.assertEquals(3, certs.size)
    }

    @Test
    fun `findById devuelve la certificacion correcta`() {
        val cert = certificationRepository.findById(1).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(1, cert.id) },
            { Assertions.assertEquals("Java SE 11 Developer", cert.name) },
            { Assertions.assertEquals("Oracle", cert.provider) },
            { Assertions.assertEquals(1, cert.student.id) }
        )
    }

    @Test
    fun `guardar nueva certificacion incrementa el total`() {

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
    fun `eliminar certificacion reduce el total`() {
        certificationRepository.deleteById(3)
        Assertions.assertFalse(certificationRepository.existsById(3))
        Assertions.assertEquals(2, certificationRepository.count())
    }

    //TEST PARA QUALIFICATIONS
    @Test
    fun `findAll devuelve las habilidades esperadas`() {
        val skills = qualificationRepository.findAll()
        Assertions.assertEquals(4, skills.size)
    }

    @Test
    fun `findById devuelve la habilidad correcta`() {
        val skill = qualificationRepository.findById(1).orElseThrow()
        Assertions.assertAll(
            { Assertions.assertEquals(1, skill.id) },
            { Assertions.assertEquals("English", skill.name) }
        )
    }

    @Test
    fun `guardar nueva habilidad incrementa el total`() {


        val nueva = Qualification(
            5,
            name = "Spanish"
        )

        qualificationRepository.save<Qualification>(nueva)

        val total = qualificationRepository.count()
        Assertions.assertEquals(5, total)
    }

    @Test
    fun `eliminar habilidad reduce el total`() {
        qualificationRepository.deleteById(3)
        Assertions.assertFalse(qualificationRepository.existsById(3))
        Assertions.assertEquals(3, certificationRepository.count())
    }

}