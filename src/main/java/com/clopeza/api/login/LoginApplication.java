package com.clopeza.api.login;

import com.clopeza.api.login.dto.UserCreationDTO;
import com.clopeza.api.login.dto.UserDTO;
import com.clopeza.api.login.model.RoleEnum;
import com.clopeza.api.login.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LoginApplication {


    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            UserService userService
    ) {
        return (evt) -> Arrays.asList(
                "agoraUser,agoraAdmin".split(",")).forEach(
                username -> {
                    UserCreationDTO user = new UserCreationDTO();
                    user.setUsername(username);
                    user.setPassword("password");
                    user.setFirstName(username);
                    user.setLastName("LastName");
                    user.setEnabled(true);
                    user.setAccountNonLocked(true);
                    user.setAccountNonExpired(true);
                    user.setCredentialsNonExpired(true);
                    if (username.equals("agoraAdmin")){
                        List<RoleEnum> adminRole = new ArrayList<>();
                        adminRole.add(RoleEnum.ROLE_ADMIN);
                        user.setRoles(adminRole);
                    }else{
                        List<RoleEnum> roleUser = new ArrayList<>();
                        roleUser.add(RoleEnum.ROLE_USER);
                        user.setRoles(roleUser);
                    }
                    userService.registerUser(user);
                }
        );
    }

}
