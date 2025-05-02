package edu.backend.taskapp.mappers


import edu.backend.taskapp.dtos.QuestionCreate
import edu.backend.taskapp.dtos.QuestionInput
import edu.backend.taskapp.dtos.QuestionOutput
import edu.backend.taskapp.dtos.QuestionUpdate
import edu.backend.taskapp.entities.Question
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface QuestionMapper {

    fun questionToQuestionOutput(
        question: Question
    ): QuestionOutput

    fun questionListToQuestionOutputList(
        questionList: List<Question>
    ): List<QuestionOutput>

    fun questionInputToQuestion(
        questionInput: QuestionInput
    ): Question

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun questionInputToQuestion(dto: QuestionInput, @MappingTarget question: Question)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun questionCreateToQuestion(
        questionCreate: QuestionCreate
    ): Question

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun questionUpdateToQuestion(dto: QuestionUpdate, @MappingTarget question: Question)

}
