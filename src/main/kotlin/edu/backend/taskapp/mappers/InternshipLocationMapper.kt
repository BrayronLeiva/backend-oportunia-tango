package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.InternshipLocationInput
import edu.backend.taskapp.dtos.InternshipLocationOutput
import edu.backend.taskapp.entities.InternshipLocation
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface InternshipLocationMapper {

    fun internshipLocationToInternshipLocationOutput(
        internshipLocation: InternshipLocation
    ): InternshipLocationOutput

    fun internshipLocationListToInternshipLocationOutputList(
        internshipLocations: List<InternshipLocation>
    ): List<InternshipLocationOutput>

    fun internshipLocationInputToInternshipLocation(
        input: InternshipLocationInput
    ): InternshipLocation

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun internshipLocationInputToInternshipLocation(
        input: InternshipLocationInput,
        @MappingTarget entity: InternshipLocation
    )
}