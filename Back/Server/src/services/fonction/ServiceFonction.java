package services.fonction;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.NotFoundException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public abstract class ServiceFonction {
    protected Service service;
    protected Object returnObject;

    public Object getReturnObject() {
        return returnObject;
    }

    public ServiceFonction(Service service) {
        this.service = service;
    }

    public abstract String getMessage();

    public abstract void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NotFoundException, NoResultException, SQLException;
}
