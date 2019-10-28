package services.fonction;

import exception.NoResultException;
import exception.NotFoundException;
import exception.ValidatorNotRecpectedException;
import entities.EntityClass;
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

    public abstract void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NotFoundException, NoResultException;
}
