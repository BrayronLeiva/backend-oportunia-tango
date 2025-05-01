package edu.backend.taskapp.mappers

import edu.backend.taskapp.dtos.RequestInput
import edu.backend.taskapp.dtos.RequestOutput
import edu.backend.taskapp.entities.Request
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RequestMapper {

    fun requestToRequestOutput(
        entity: Request
    ): RequestOutput

    fun requestListToRequestOutputList(
        entities: List<Request>
    ): List<RequestOutput>

    fun requestInputToRequest(
        input: RequestInput
    ): Request

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun requestInputToRequest(
        input: RequestInput,
        @MappingTarget entity: Request
    )
}