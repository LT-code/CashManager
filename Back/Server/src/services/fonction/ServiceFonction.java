package services.fonction;

import exception.NoResultException;
import exception.NotFoundException;
import exception.ValidatorNotRecpectedException;

import java.sql.SQLException;

import services.Service;
import tables.TableClass;

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

    public abstract void execute(TableClass entityClass) throws ValidatorNotRecpectedException, NotFoundException, NoResultException, SQLException;
}
