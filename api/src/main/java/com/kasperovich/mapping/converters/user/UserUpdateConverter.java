package com.kasperovich.mapping.converters.user;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.mapping.mappers.AddressMapper;
import com.kasperovich.models.User;
import com.kasperovich.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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

    public User doConvert(UserCreateDto userCreateDto, Long id) throws EntityNotFoundException {
        User user=new User();
        user.setId(id);
        user.setCredentials(
                Optional.ofNullable(
                        userCreateDto.getCredentials())
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getCredentials()
                )
        );
        user.setFirstName(
                Optional.ofNullable(
                        userCreateDto.getFirstName())
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getFirstName()
                )
        );
        user.setLastName(
                Optional.ofNullable(
                        userCreateDto.getLastName())
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getLastName()
                )
        );
        user.setEmail(
                Optional.ofNullable(
                        userCreateDto.getEmail())
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getEmail()
                )
        );
        user.setMobilePhone(
                Optional.ofNullable(
                        userCreateDto.getMobilePhone())
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getMobilePhone()
                )
        );
        user.setAddress(
                Optional.ofNullable(addressMapper.toEntity(userCreateDto.getAddress()))
                        .orElse(userRepository.findById(id)
                                .orElseThrow(EntityNotFoundException::new).getAddress())
        );
        user.setRole(userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new).getRole());
        return user;
    }
}
