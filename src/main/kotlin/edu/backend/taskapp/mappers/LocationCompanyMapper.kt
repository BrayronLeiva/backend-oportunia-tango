package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.LocationCompanyInput
import edu.backend.taskapp.dtos.LocationCompanyOutput
import edu.backend.taskapp.entities.LocationCompany
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LocationCompanyMapper {

    fun locationCompanyToLocationCompanyOutput(
        entity: LocationCompany
    ): LocationCompanyOutput

    fun locationCompanyListToLocationCompanyOutputList(
        entities: List<LocationCompany>
    ): List<LocationCompanyOutput>

    fun locationCompanyInputToLocationCompany(
        input: LocationCompanyInput
    ): LocationCompany

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun locationCompanyInputToLocationCompany(
        input: LocationCompanyInput,
        @MappingTarget entity: LocationCompany
    )
}