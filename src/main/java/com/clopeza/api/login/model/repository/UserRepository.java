package com.clopeza.api.login.model.repository;

import com.clopeza.api.login.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    /**
     * find user by Username
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * Creates or Updates User
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * Deletes user by its Id
     *
     * @param id
     */
    void deleteUserById(Long id);

}
