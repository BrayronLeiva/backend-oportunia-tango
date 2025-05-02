package edu.backend.taskapp.mappers


import edu.backend.taskapp.dtos.RecommendationCreate
import edu.backend.taskapp.dtos.RecommendationInput
import edu.backend.taskapp.dtos.RecommendationOutput
import edu.backend.taskapp.dtos.RecommendationUpdate
import edu.backend.taskapp.entities.Recommendation
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RecommendationMapper {

    fun recommendationToRecommendationOutput(
        recommendation: Recommendation
    ): RecommendationOutput

    fun recommendationListToRecommendationOutputList(
        recommendationList: List<Recommendation>
    ): List<RecommendationOutput>

    fun recommendationInputToRecommendation(
        recommendationInput: RecommendationInput
    ): Recommendation

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun recommendationInputToRecommendation(dto: RecommendationInput, @MappingTarget recommendation: Recommendation)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun recommendationCreateToRecommendation(
        recommendationCreate: RecommendationCreate
    ): Recommendation

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun recommendationUptadeToRecommendation(dto: RecommendationUpdate, @MappingTarget recommendation: Recommendation)

}
