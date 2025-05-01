package edu.backend.taskapp.Mappers

import edu.backend.taskapp.DTOs.QuestionDto
import edu.backend.taskapp.entities.Question
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface QuestionMapper {

    fun questionToQuestionDto(
        question: Question
    ): QuestionDto

    fun questionListToQuestionDtoList(
        questionList: List<Question>
    ): List<QuestionDto>

    fun questionDtoToQuestion(
        questionDto: QuestionDto
    ): Question

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun questionDtoToQuestion(dto: QuestionDto, @MappingTarget question: Question)
}
