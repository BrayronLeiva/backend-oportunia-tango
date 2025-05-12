package edu.backend.taskapp.services

import edu.backend.taskapp.CompanyRepository
import edu.backend.taskapp.StudentRepository
import edu.backend.taskapp.dtos.CertificationOutput
import edu.backend.taskapp.dtos.CompanyOutput
import edu.backend.taskapp.dtos.InternshipEvaluateOutput
import edu.backend.taskapp.dtos.InternshipMatchResult
import edu.backend.taskapp.dtos.InternshipOutput
import edu.backend.taskapp.dtos.LocationCompanyInput
import edu.backend.taskapp.dtos.LocationCompanyOutput
import edu.backend.taskapp.dtos.LocationRequestDTO
import edu.backend.taskapp.dtos.OpenAIResponse
import edu.backend.taskapp.dtos.StudentMatchResult
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.entities.Certification
import edu.backend.taskapp.entities.Internship
import edu.backend.taskapp.entities.Student
import edu.backend.taskapp.mappers.CompanyMapper
import edu.backend.taskapp.mappers.StudentMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import java.util.NoSuchElementException
import java.util.Optional

interface AIService {
    suspend fun matchStudentsWithCompany(
        company: CompanyOutput,
        students: List<StudentOutput>
    ): List<StudentMatchResult>

    suspend fun recommendInternshipsForStudent(
        student: StudentOutput,
        nearbyInternships: List<InternshipEvaluateOutput>
    ): List<InternshipMatchResult>
}

@Service
class AbstractAIService(
    @Value("\${openAiApiKey}") private val apiKey: String
) : AIService {

    private val webClient = WebClient.builder()
        .baseUrl("https://api.openai.com/v1/chat/completions")
        .defaultHeader("Authorization", "Bearer $apiKey")
        .defaultHeader("Content-Type", "application/json")
        .build()

    override suspend fun matchStudentsWithCompany(
        company: CompanyOutput,
        students: List<StudentOutput>
    ): List<StudentMatchResult> = coroutineScope {
        students.map { student ->
            async {
                val prompt = generatePrompt(student, company)

                val request = mapOf(
                    "model" to "gpt-4o-mini",
                    "messages" to listOf(
                        mapOf("role" to "system", "content" to "Eres un sistema experto en selección de pasantías."),
                        mapOf("role" to "user", "content" to prompt)
                    ),
                    "temperature" to 0.4
                )

                val response = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(OpenAIResponse::class.java)
                    .awaitSingle()

                parseResponse(response.choices.first().message.content, student)
            }
        }.awaitAll()
    }

    private fun generatePrompt(student: StudentOutput, company: CompanyOutput): String {
        return """
        Eres un sistema experto en selección de pasantes. Evalúa si el siguiente estudiante es adecuado para hacer una pasantía en la empresa descrita.

        Devuelve una puntuación de 0 a 100 y explica brevemente el motivo.

        Estudiante:
        Nombre: ${student.nameStudent}
        Información personal: ${student.personalInfo}
        Experiencia: ${student.experience}
        Rating: ${student.ratingStudent}

        Empresa:
        Nombre: ${company.nameCompany}
        Descripción: ${company.description}
        Misión: ${company.mision}
        Tipo de pasantía: ${company.internshipType}
        Cultura corporativa: ${company.corporateCultur}
        Rating: ${company.ratingCompany}

        Formato de respuesta:
        score: [número del 0 al 100]
        reason: [explicación breve]
    """.trimIndent()
    }



    private fun parseResponse(content: String, student: StudentOutput): StudentMatchResult {
        val scoreRegex = Regex("score:\\s*(\\d+)")
        val reasonRegex = Regex("reason:\\s*(.*)", RegexOption.DOT_MATCHES_ALL)

        val score = scoreRegex.find(content)?.groupValues?.get(1)?.toIntOrNull() ?: 0
        val reason = reasonRegex.find(content)?.groupValues?.get(1)?.trim() ?: "No se pudo obtener explicación"

        return StudentMatchResult(
            idStudent = student.idStudent ?: 0,
            nameStudent = student.nameStudent ?: "Desconocido",
            score = score,
            reason = reason
        )
    }

    override suspend fun recommendInternshipsForStudent(
        student: StudentOutput,
        nearbyInternships: List<InternshipEvaluateOutput>
    ): List<InternshipMatchResult> {


        return coroutineScope {
            nearbyInternships.map { internship ->
                async {
                    val company = internship.company
                    val prompt = generatePrompt(student, company, internship)

                    val request = mapOf(
                        "model" to "gpt-4o-mini",
                        "messages" to listOf(
                            mapOf("role" to "system", "content" to "Eres un sistema experto en orientación de pasantías."),
                            mapOf("role" to "user", "content" to prompt)
                        ),
                        "temperature" to 0.4
                    )

                    val response = webClient.post()
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(OpenAIResponse::class.java)
                        .awaitSingle()

                    parseResponseForInternship(response.choices.first().message.content, internship)

                }
            }.awaitAll()
        }
    }

    private fun generatePrompt(student: StudentOutput, company: CompanyOutput, internship: InternshipEvaluateOutput): String {
        return """
        Eres un sistema experto en orientación de pasantías. Evalúa si la siguiente pasantía es adecuada para el estudiante descrito, basándote en su experiencia, intereses y habilidades.

        Devuelve una puntuación de 0 a 100 y explica por qué esta pasantía sería beneficiosa para el estudiante.(Hablale al estudiante por su nombre de forma natural)

        Estudiante:
        Nombre: ${student.nameStudent}
        Información personal: ${student.personalInfo}
        Experiencia: ${student.experience}
        Rating: ${student.ratingStudent}

        Empresa:
        Nombre: ${company.nameCompany}
        Descripción: ${company.description}
        Cultura corporativa: ${company.corporateCultur}

        Pasantía:
        Título: ${internship.details}

        Formato de respuesta:
        score: [0 al 100]
        reason: [explicación]
    """.trimIndent()
    }

    private fun parseResponseForInternship(content: String, internship: InternshipEvaluateOutput): InternshipMatchResult {
        val scoreRegex = Regex("score:\\s*(\\d+)")
        val reasonRegex = Regex("reason:\\s*(.*)", RegexOption.DOT_MATCHES_ALL)

        val score = scoreRegex.find(content)?.groupValues?.get(1)?.toIntOrNull() ?: 0
        val reason = reasonRegex.find(content)?.groupValues?.get(1)?.trim() ?: "No se pudo obtener explicación"

        return InternshipMatchResult(
            internshipId = internship.idInternship!!,
            internshipTitle = internship.details!!,
            score = score,
            reason = reason
        )
    }




}
