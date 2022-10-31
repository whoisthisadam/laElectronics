package com.kasperovich.mapper;

import com.kasperovich.dto.address.AddressCreateDto;
import com.kasperovich.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    public Address toEntity(AddressCreateDto dto);

}
