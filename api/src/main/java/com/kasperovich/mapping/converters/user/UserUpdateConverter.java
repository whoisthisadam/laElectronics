package com.kasperovich.mapping.converters.user;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.mapping.mappers.AddressMapper;
import com.kasperovich.models.User;
import com.kasperovich.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter implements Converter<UserCreateDto, User> {

    private final UserRepository userRepository;

    private final AddressMapper addressMapper;


    @Override
    public User convert(UserCreateDto userCreateDto) {
        return null;
    }

    public User doConvert(UserCreateDto userCreateDto, Long id){
        User user=new User();
        user.setId(id);
        user.setCredentials(
                Optional.ofNullable(userCreateDto.getCredentials()).orElse(userRepository.findById(id).get().getCredentials())
        );
        user.setFirstName(
                Optional.ofNullable(userCreateDto.getFirstName()).orElse(userRepository.findById(id).get().getFirstName())
        );
        user.setLastName(
                Optional.ofNullable(userCreateDto.getLastName()).orElse(userRepository.findById(id).get().getLastName())
        );
        user.setEmail(
                Optional.ofNullable(userCreateDto.getEmail()).orElse(userRepository.findById(id).get().getEmail())
        );
        user.setMobilePhone(
                Optional.ofNullable(userCreateDto.getMobilePhone()).orElse(userRepository.findById(id).get().getMobilePhone())
        );
        user.setAddress(
                Optional.ofNullable(addressMapper.toEntity(userCreateDto.getAddress())).orElse(userRepository.findById(id).get().getAddress())
        );
        user.setRole(userRepository.findById(id).get().getRole());
        return user;
    }
}
