package com.kasperovich.laelectronics.api.mapping.mappers;

import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionCreateDto;
import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionGetDto;
import com.kasperovich.laelectronics.models.Subscription;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SubscriptionMapper {

    Subscription toEntity(SubscriptionCreateDto subscriptionCreateDto);

    SubscriptionGetDto toDto(Subscription subscription);


}
