package fr.philippedeoliveira.services;

import java.util.List;

import fr.philippedeoliveira.beans.User;

/**
 * Services available for User
 * 
 * @author waddle
 *
 */
public interface IUserService {

	/**
	 * Get a user by its id
	 * 
	 * @param id
	 * @return
	 */
    User getById(String id);

    /**
     * Save a user
     * 
     * @param user
     */
    void saveUser(User user);

    /**
     * Return all the application users
     * 
     * @return
     */
    List<User> getAllUsers();

    void deleteUserBets(Integer userId);
}
