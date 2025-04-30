package edu.backend.taskapp.Mappers
import edu.backend.taskapp.DTOs.CertificationDto
import edu.backend.taskapp.entities.Certification
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CertificationMapper {
    fun certificationToCertificationDto(
        certification: Certification
    ) : CertificationDto

    fun certificationListToCertificationDtoList(
        certificationList: List<Certification>
    ) : List<CertificationDto>


    fun taskInputToTask (
        certificationDto: CertificationDto
    ) : Certification

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun certificationDtoToCertification(dto: CertificationDto, @MappingTarget certification: Certification)
}
