package com.clopeza.api.login.service;

import com.clopeza.api.login.dto.UserCreationDTO;
import com.clopeza.api.login.dto.UserDTO;
import com.clopeza.api.login.model.RoleEnum;
import com.clopeza.api.login.model.User;
import com.clopeza.api.login.model.repository.UserRepository;
import com.clopeza.api.login.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
        return user;

    }

    public UserDTO findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
        return userConverter.toDto(user);
    }


    public UserDTO registerUser(UserCreationDTO userCreationDTO) {
        User user = userConverter.toEntity(userCreationDTO);
        user.setPassword(new BCryptPasswordEncoder().encode(userCreationDTO.getPassword()));
        user.grantAuthority(RoleEnum.ROLE_USER);
        return userConverter.toDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
        userRepository.deleteUserById(user.getId());

    }


}
