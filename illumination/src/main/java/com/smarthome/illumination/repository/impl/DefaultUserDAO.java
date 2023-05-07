package com.smarthome.illumination.repository.impl;

import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.repository.UserDAO;
import com.smarthome.illumination.repository.model.UserModel;
import io.micrometer.core.instrument.distribution.TimeWindowPercentileHistogram;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class DefaultUserDAO implements UserDAO {

    private EntityManager entityManager;

    public DefaultUserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public boolean saveUser(UserModel userModel) throws UserAlreadyAvailableException {
        if (getUserFromDataBase(userModel.getUsername(), userModel.getEmail()) != null) {
            throw new UserAlreadyAvailableException("User already exists!");
        }
        entityManager.persist(userModel);
        return entityManager.contains(userModel);
    }

    @Override
    public UserModel getUserFromDataBase(String username, String mail) {
        TypedQuery<UserModel> typedQuery = entityManager.createQuery(
                "FROM UserModel WHERE username=:givenUsername OR email= :mail", UserModel.class);
        typedQuery.setParameter("givenUsername", username);
        typedQuery.setParameter("mail", mail);
        List<UserModel> list = typedQuery.getResultList();
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public UserModel getUserForLogin(String mail) {
        TypedQuery<UserModel> typedQuery = entityManager.createQuery(
                "FROM UserModel WHERE email= :mail", UserModel.class);
        typedQuery.setParameter("mail", mail);
        List<UserModel> list = typedQuery.getResultList();
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

}
