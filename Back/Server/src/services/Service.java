package services;

import utils.DBConnector;
import utils.ErrorsHandler;
import services.fonction.AddFonction;
import services.fonction.DeleteFonction;
import services.fonction.ServiceFonction;
import services.fonction.UpdateFonction;
import tables.TableClass;

import java.sql.SQLException;

import dao.Dao;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;


public abstract class Service {
	DBConnector db;
	protected ErrorsHandler errHandler = new ErrorsHandler();
	
	public Service(DBConnector db) {
		this.db = db;
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

    
    public boolean add(TableClass entityClass) {
        return serviceMethod(new AddFonction(this), entityClass);
    }
    
    public boolean update(TableClass entityClass) {
        return serviceMethod(new UpdateFonction(this), entityClass);
    }
    
    public boolean delete(TableClass entityClass) {
        return serviceMethod(new DeleteFonction(this), entityClass);
    }

    
    public void methodAdd(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        this.getDao().add(entityClass);
    }
    
    public void methodUpdate(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        this.getDao().update(entityClass);
    }
    
    public void methodDelete(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        this.getDao().delete(entityClass);
    }

    
    public abstract boolean validator(TableClass entityClass);
    
    public boolean validatorAdd(TableClass entityClass) {
        return validator(entityClass);
    }
    
    public boolean validatorUpdate(TableClass entityClass) {
        return validator(entityClass);
    }

    public boolean validatorDelete(TableClass entityClass) {
        boolean res = validator(entityClass);
        if(entityClass.getEntityID() instanceof Long)
        	res = res && (long) entityClass.getEntityID() <= 0;
        return res;
    }
    
    protected boolean serviceMethod(ServiceFonction serviceFonction, TableClass entityClass) {
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
