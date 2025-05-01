package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.CertificationInput
import edu.backend.taskapp.dtos.CertificationOutput
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
