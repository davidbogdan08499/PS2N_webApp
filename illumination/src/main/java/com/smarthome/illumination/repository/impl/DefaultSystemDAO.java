package com.smarthome.illumination.repository.impl;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.exception.SystemAlreadyRegisteredException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.repository.SystemsDAO;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Repository;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class DefaultSystemDAO implements SystemsDAO {

    private EntityManager entityManager;

    public DefaultSystemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public boolean addSystemToDataBase(int systemID, UserModel userModel) throws SystemAlreadyRegisteredException {
        if (verifySystemExistance(systemID) == true) {
            throw new SystemAlreadyRegisteredException("System already registered!");
        } else {
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime localDateTime=LocalDateTime.now();
            SystemsModel systemsModel = new SystemsModel();
            systemsModel.setId(systemID);
            systemsModel.setSystemStatus(ConstantsVariables.SYSTEMON);
            systemsModel.setStartDate(dateTimeFormatter.format(localDateTime));
            systemsModel.setUser(userModel);
            userModel.getSystemsModelList().add(systemsModel);
            entityManager.persist(systemsModel);
            entityManager.flush();
            return entityManager.contains(systemsModel);
        }
    }

    @Override
    public SystemsModel getSystemFromDataBase(int id) {
        TypedQuery<SystemsModel> typedQuery=entityManager.createQuery(
                "FROM SystemsModel WHERE  id= :id",SystemsModel.class);
        typedQuery.setParameter("id",id);
        List<SystemsModel>list=typedQuery.getResultList();
        return list.get(0);
    }

    private boolean verifySystemExistance(int systemId) throws SystemAlreadyRegisteredException {
        TypedQuery<SystemsModel> typedQuery = entityManager.createQuery(
                "FROM SystemsModel WHERE id= :systemID", SystemsModel.class);
        typedQuery.setParameter("systemID", systemId);
        List<SystemsModel> list = typedQuery.getResultList();
        if (list.size() != 0) return true;
        return false;
    }
}
