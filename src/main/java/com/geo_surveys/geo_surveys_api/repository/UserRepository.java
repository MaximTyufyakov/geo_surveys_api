package com.geo_surveys.geo_surveys_api.repository;

import com.geo_surveys.geo_surveys_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for user.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find user by login.
     *
     * @param login is user login.
     * @return one user.
     */
    User findByLogin(String login);
}