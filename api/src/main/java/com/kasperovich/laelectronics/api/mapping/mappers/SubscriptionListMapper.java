package com.kasperovich.laelectronics.api.mapping.mappers;

import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionGetDto;
import com.kasperovich.laelectronics.models.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = SubscriptionMapper.class)
public interface SubscriptionListMapper {

    List<SubscriptionGetDto> toDto(List<Subscription> subscriptions);

}
