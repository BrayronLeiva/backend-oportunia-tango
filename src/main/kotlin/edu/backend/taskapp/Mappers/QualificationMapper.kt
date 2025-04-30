package edu.backend.taskapp.Mappers

import edu.backend.taskapp.DTOs.QualificationDto
import edu.backend.taskapp.entities.Qualification
import org.mapstruct.BeanMapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

interface QualificationMapper {
    fun qualificationToQualificationDto(
        qualification: Qualification
    ) : QualificationDto

    fun qualificationListToQualificationDtoList(
        qualificationList: List<Qualification>
    ) : List<QualificationDto>


    fun qualificationDtoToQualification (
        qualificationDto: QualificationDto
    ) : Qualification

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun qualificationDtoToQualification(dto: QualificationDto, @MappingTarget qualification: Qualification)
}