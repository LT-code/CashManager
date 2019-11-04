package services;

import java.sql.SQLException;

import dao.Dao;
import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.fonction.AddFonction;
import services.fonction.DeleteFonction;
import services.fonction.ServiceFonction;
import services.fonction.UpdateFonction;
import utils.DBConnector;
import utils.ErrorsHandler;


public abstract class Service {
	DBConnector db;
	protected ErrorsHandler errHandler;
	
	public Service(DBConnector db, ErrorsHandler errHandler) {
		this.db = db;
		this.errHandler = errHandler;
	}    

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

    
    public void methodAdd(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        this.getDao().add(entityClass);
    }
    
    public void methodUpdate(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        this.getDao().update(entityClass);
    }
    
    public void methodDelete(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
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
        if(entityClass.getId() instanceof Long)
        	res = res && (long) entityClass.getId() <= 0;
        return res;
    }
    
    protected boolean serviceMethod(ServiceFonction serviceFonction, EntityClass entityClass) {
        try {
            serviceFonction.execute(entityClass);
            errHandler.setInfo("Success of " + serviceFonction.getMessage() + entityClass.table().entityNameClass() + ".", entityClass.toString());
        }
        catch (Exception e) {
            if(e instanceof ValidatorNotRecpectedException)
                return false;
            errHandler.setError("failed " + serviceFonction.getMessage() + entityClass.table().entityNameClass() + ".", ErrorsHandler.getMessageError(e) + " | " +  entityClass.toString());
        }

        return errHandler.isValid();
    }
}
