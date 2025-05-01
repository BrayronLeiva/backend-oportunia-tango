package edu.backend.taskapp.Mappers

import edu.backend.taskapp.DTOs.CertificationInput
import edu.backend.taskapp.DTOs.CertificationOutput
import edu.backend.taskapp.entities.Certification
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CertificationMapper {
    fun certificationToCertificationOutput(
        certification: Certification
    ) : CertificationOutput

    fun certificationListToCertificationOutputList(
        certificationList: List<Certification>
    ) : List<CertificationOutput>


    fun certificationInputToCertification (
        certificationInput: CertificationInput
    ) : Certification

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun certificationInputToCertification(
        dto: CertificationInput,
        @MappingTarget certification: Certification
    )
}
