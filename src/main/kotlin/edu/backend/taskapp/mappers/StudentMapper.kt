package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.StudentInput
import edu.backend.taskapp.dtos.StudentOutput
import edu.backend.taskapp.entities.Student
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StudentMapper {

    fun studentToStudentOutput(
        student: Student
    ): StudentOutput

    fun studentListToStudentOutputList(
        studentList: List<Student>
    ): List<StudentOutput>

    fun studentInputToStudent(
        studentInput: StudentInput
    ): Student

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun studentInputToStudent(dto: StudentInput, @MappingTarget student: Student)
}
