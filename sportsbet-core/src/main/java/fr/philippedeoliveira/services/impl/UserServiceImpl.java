package fr.philippedeoliveira.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.dao.IUserDAO;
import fr.philippedeoliveira.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Override
    @Transactional
    public void deleteUserBets(Integer userId) {
        // TO BE IMPLEMENTED
    }

    @Override
    @Transactional
    public User getById(String id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.makePersistent(user);
    }
    
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
