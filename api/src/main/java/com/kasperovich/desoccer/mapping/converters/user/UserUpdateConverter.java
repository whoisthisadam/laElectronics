package com.kasperovich.desoccer.mapping.converters.user;

import com.kasperovich.desoccer.dto.users.UserCreateDto;
import com.kasperovich.desoccer.exception.BadPasswordException;
import com.kasperovich.desoccer.mapping.mappers.AddressMapper;
import com.kasperovich.desoccer.models.User;
import com.kasperovich.desoccer.repository.UserRepository;
import com.kasperovich.desoccer.util.ValidCheck;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter implements Converter<UserCreateDto, User> {

    private final UserRepository userRepository;

    private final AddressMapper addressMapper;

    private final ValidCheck validCheck;


    @Override
    public User convert(UserCreateDto userCreateDto) {
        return null;
    }

    public User doConvert(UserCreateDto userCreateDto, Long id) throws EntityNotFoundException, BadPasswordException {
        if(!userCreateDto.getCredentials().getPassword().isEmpty()&&
                !validCheck.isPasswordValid(userCreateDto.getCredentials().getPassword())){
            throw new BadPasswordException("Password must include at least one capital, or number, or symbol");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with this ID does not exist!"));
        user.setId(id);
        user.setCredentials(
                Optional.ofNullable(userCreateDto.getCredentials()).orElse(user.getCredentials())
        );
        user.setFirstName(
                Optional.ofNullable(userCreateDto.getFirstName()).orElse(user.getFirstName())
        );
        user.setLastName(
                Optional.ofNullable(userCreateDto.getLastName()).orElse(user.getLastName())
        );
        user.setEmail(
                Optional.ofNullable(userCreateDto.getEmail()).orElse(user.getEmail())
        );
        user.setMobilePhone(
                Optional.ofNullable(userCreateDto.getMobilePhone()).orElse(user.getMobilePhone())
        );
        user.setAddress(
                Optional.ofNullable(addressMapper.toEntity(userCreateDto.getAddress())).orElse(user.getAddress())
        );
        return user;
    }
}
