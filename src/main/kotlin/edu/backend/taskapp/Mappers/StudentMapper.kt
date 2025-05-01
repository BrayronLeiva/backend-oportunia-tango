package edu.backend.taskapp.Mappers

import edu.backend.taskapp.DTOs.StudentDto
import edu.backend.taskapp.entities.Student
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StudentMapper {

    fun studentToStudentDto(
        student: Student
    ): StudentDto

    fun studentListToStudentDtoList(
        studentList: List<Student>
    ): List<StudentDto>

    fun studentDtoToStudent(
        studentDto: StudentDto
    ): Student

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun studentDtoToStudent(dto: StudentDto, @MappingTarget student: Student)
}
