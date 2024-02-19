package com.kasperovich.laelectronics.api.mapping.converters.subscription;

import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionCreateDto;
import com.kasperovich.laelectronics.models.Subscription;
import com.kasperovich.laelectronics.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionUpdateConverter implements Converter<SubscriptionCreateDto, Subscription> {

    SubscriptionRepository subscriptionRepository;


    @Override
    public Subscription convert(SubscriptionCreateDto subscriptionCreateDto) {
        return null;
    }

    public Subscription doConvert(SubscriptionCreateDto subscriptionCreateDto, Long id) throws EntityNotFoundException {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with this id does not exist"));
        subscription.setId(id);

        subscription.setName(
                Optional.ofNullable(subscriptionCreateDto.getName()).orElse(subscription.getName())
        );
        subscription.setPrice(
                Optional.ofNullable(subscriptionCreateDto.getPrice()).orElse(subscription.getPrice())
        );
        subscription.setIsAvailable(
                Optional.ofNullable(
                                subscriptionCreateDto.getIsAvailable())
                        .orElse(subscription.getIsAvailable())
        );
        return subscription;
    }
}
