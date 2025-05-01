package edu.backend.taskapp.Mappers


import edu.backend.taskapp.DTOs.QualificationInput
import edu.backend.taskapp.DTOs.QualificationOutput
import edu.backend.taskapp.entities.Qualification
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface QualificationMapper {
    fun qualificationToQualificationOutput(
        qualification: Qualification
    ) : QualificationOutput

    fun qualificationListToQualificationOutputList(
        qualificationList: List<Qualification>
    ) : List<QualificationOutput>


    fun qualificationInputToQualification (
        qualificationInput: QualificationInput
    ) : Qualification

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun qualificationInputToQualification(dto: QualificationInput, @MappingTarget qualification: Qualification)
}