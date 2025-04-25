package edu.backend.taskapp

import edu.backend.taskapp.entities.Certification
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
        "DELETE FROM public.users"
    ],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = ["/import-users.sql", "/import-students.sql", "/import-certifications.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class LoadInitData2(
    @Autowired
    val certificationRepository: CertificationRepository,
    @Autowired
    val studentRepository: StudentRepository
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

}