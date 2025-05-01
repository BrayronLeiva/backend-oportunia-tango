package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.RatingCompanyStudentInput
import edu.backend.taskapp.dtos.RatingCompanyStudentOutput
import edu.backend.taskapp.entities.RatingCompanyStudent
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RatingCompanyStudentMapper {

    fun ratingCompanyStudentToRatingCompanyStudentOutput(
        entity: RatingCompanyStudent
    ): RatingCompanyStudentOutput

    fun ratingCompanyStudentListToRatingCompanyStudentOutputList(
        entities: List<RatingCompanyStudent>
    ): List<RatingCompanyStudentOutput>

    fun ratingCompanyStudentInputToRatingCompanyStudent(
        input: RatingCompanyStudentInput
    ): RatingCompanyStudent

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun ratingCompanyStudentInputToRatingCompanyStudent(
        input: RatingCompanyStudentInput,
        @MappingTarget entity: RatingCompanyStudent
    )
}