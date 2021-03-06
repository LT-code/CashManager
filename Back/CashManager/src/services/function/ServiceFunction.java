package services.function;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public abstract class ServiceFunction {
    protected Service service;
    protected Object returnObject;

    public Object getReturnObject() {
        return returnObject;
    }

    public ServiceFunction(Service service) {
        this.service = service;
    }

    public abstract String getMessage();

    public abstract void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException;
}
