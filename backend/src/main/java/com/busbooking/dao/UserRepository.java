package com.busbooking.dao;

import com.busbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity
 * Provides database operations for User management
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
