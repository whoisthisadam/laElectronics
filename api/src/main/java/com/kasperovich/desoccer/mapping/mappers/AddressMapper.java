package com.kasperovich.desoccer.mapping.mappers;

import com.kasperovich.desoccer.dto.address.AddressCreateDto;
import com.kasperovich.desoccer.models.Address;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddressMapper {

    Address toEntity(AddressCreateDto dto);

    AddressCreateDto toDto(Address address);

}
