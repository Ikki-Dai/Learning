/**
 * 
 */
package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

/**
 * @author Ikki
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public static List<User> findByUsernameAndPasscode(String username, String passcode) {
		return null;
	}
}
