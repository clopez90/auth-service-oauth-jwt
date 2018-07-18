package com.clopeza.api.login;

import com.clopeza.api.login.dto.UserCreationDTO;
import com.clopeza.api.login.dto.UserDTO;
import com.clopeza.api.login.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserService();

		}
	}

	@Autowired
	private UserService userService;

	@Test
	public void findByUsername() {
		UserDTO userDTO = userService.findUserByUsername("agoraUser");
		assertNotNull(userDTO);
	}
	@Test
	public void registerUser() {

		UserCreationDTO userCreationDTO = new UserCreationDTO();
		userCreationDTO.setAccountNonExpired(true);
		userCreationDTO.setAccountNonLocked(true);
		userCreationDTO.setCredentialsNonExpired(true);
		userCreationDTO.setEnabled(true);
		userCreationDTO.setPassword("123456");
		userCreationDTO.setUsername("clopeza");
		userCreationDTO.setFirstName("carlos");
		userCreationDTO.setLastName("lopez");
		userCreationDTO.setEmail("test@test.com");


		UserDTO userDTO = userService.registerUser(userCreationDTO);

		assertNotNull(userDTO);
		assertNotNull(userDTO.getId());
		assertEquals(userDTO.getUsername(),"clopeza");
	}

	@Test(expected = UsernameNotFoundException.class)
	public void deleteUser() {
		userService.deleteUserByUsername("agoraUser");
		UserDTO userDTO = userService.findUserByUsername("agoraUser");
	}

}
