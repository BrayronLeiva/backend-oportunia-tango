package edu.backend.taskapp.Mappers

import edu.backend.taskapp.DTOs.RecommendationDto
import edu.backend.taskapp.entities.Recommendation
import org.mapstruct.BeanMapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

interface RecommendationMapper {

    fun recommendationToRecommendationDto(
        recommendation: Recommendation
    ): RecommendationDto

    fun recommendationListToRecommendationDtoList(
        recommendationList: List<Recommendation>
    ): List<RecommendationDto>

    fun recommendationDtoToRecommendation(
        recommendationDto: RecommendationDto
    ): Recommendation

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun recommendationDtoToRecommendation(dto: RecommendationDto, @MappingTarget recommendation: Recommendation)
}
