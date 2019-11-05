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
import utils.LogsHandler;


public abstract class Service {
	DBConnector db;
	protected LogsHandler errHandler;
	
	public Service(DBConnector db, LogsHandler errHandler) {
		this.db = db;
		this.errHandler = errHandler;
	}    

    public abstract Dao getDao();
    
    public LogsHandler getErrorsHandler() {
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
            errHandler.addInfo("Success of " + serviceFonction.getMessage() + entityClass.table().entityNameClass() + ".");
        }
        catch (Exception e) {
            if(e instanceof ValidatorNotRecpectedException)
                return false;
            errHandler.addError(e);
        }

        return errHandler.isValid();
    }
}
