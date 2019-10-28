package services;

import utils.ErrorsHandler;
import services.fonction.AddFonction;
import services.fonction.DeleteFonction;
import services.fonction.ServiceFonction;
import services.fonction.UpdateFonction;
import dao.Dao;
import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;


public abstract class Service {
    protected ErrorsHandler errHandler = new ErrorsHandler();

    public abstract Dao getDao();
    
    public boolean isTransactionValid() {
        return errHandler.isValid();
    }
    public String getTransactionMessage() {
        return errHandler.getMessage();
    }
    
    public ErrorsHandler getErrorsHandler() {
        return errHandler;
    }

    
    public boolean add(EntityClass entityClass) {
        return serviceMethod(new AddFonction(this), entityClass);
    }
    
    public boolean update(EntityClass entityClass) {
        return serviceMethod(new UpdateFonction(this), entityClass);
    }
    
    public boolean delete(EntityClass entityClass) {
        return serviceMethod(new DeleteFonction(this), entityClass);
    }

    
    public void methodAdd(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        this.getDao().add(entityClass);
    }
    
    public void methodUpdate(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        this.getDao().update(entityClass);
    }
    
    public void methodDelete(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        this.getDao().delete(entityClass);
    }

    
    public abstract boolean validator(EntityClass entityClass);
    
    public boolean validatorAdd(EntityClass entityClass) {
        return validator(entityClass);
    }
    
    public boolean validatorUpdate(EntityClass entityClass) {
        return validator(entityClass);
    }

    public boolean validatorDelete(EntityClass entityClass) {
        boolean res = validator(entityClass);
        if(entityClass.getEntityID() instanceof Long)
        	res = res && (long) entityClass.getEntityID() <= 0;
        return res;
    }
    
    protected boolean serviceMethod(ServiceFonction serviceFonction, EntityClass entityClass) {
        try {
            serviceFonction.execute(entityClass);
            errHandler.setInfo("Success of " + serviceFonction.getMessage() + entityClass.entityNameClass() + ".", entityClass.toString());
        }
        catch (Exception e) {
            if(e instanceof ValidatorNotRecpectedException)
                return false;
            errHandler.setError("failed " + serviceFonction.getMessage() + entityClass.entityNameClass() + ".", ErrorsHandler.getMessageError(e) + " | " +  entityClass.toString());
        }

        return errHandler.isValid();
    }
}
