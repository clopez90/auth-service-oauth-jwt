package com.clopeza.api.login.service.converter;

import com.clopeza.api.login.dto.UserCreationDTO;
import com.clopeza.api.login.dto.UserDTO;
import com.clopeza.api.login.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    /**
     * Transforms Entity Object to DTO
     *
     * @param user
     * @return
     */
    public UserDTO toDto(User user) {

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * Transforms Create DTO to Entity object
     *
     * @param userCreationDTO
     * @return
     */
    public User toEntity(UserCreationDTO userCreationDTO) {
        User user = new User();
        BeanUtils.copyProperties(userCreationDTO, user);
        return user;
    }


    /**
     * Transforms DTO to Entity object
     *
     * @param userDTO
     * @return
     */
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }


}
