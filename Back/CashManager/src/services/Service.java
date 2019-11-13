package services;

import java.sql.SQLException;

import dao.Dao;
import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.function.AddFunction;
import services.function.DeleteFunction;
import services.function.ServiceFunction;
import services.function.UpdateFunction;
import utils.DBConnector;
import utils.HttpStatus;
import utils.LogsHandler;


public abstract class Service {
	DBConnector db;
	protected LogsHandler logsHandler;
	
	public Service(DBConnector db, LogsHandler errHandler) {
		this.db = db;
		this.logsHandler = errHandler;
	}    

    public abstract Dao getDao();
    
    public LogsHandler getLogsHandler() {
        return logsHandler;
    }

    
    public boolean add(EntityClass entityClass) {
        return serviceMethod(new AddFunction(this), entityClass);
    }
    
    public boolean update(EntityClass entityClass) {
        return serviceMethod(new UpdateFunction(this), entityClass);
    }
    
    public boolean delete(EntityClass entityClass) {
        return serviceMethod(new DeleteFunction(this), entityClass);
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
    	System.out.println(entityClass.getId());
        if(entityClass.getId() instanceof Long)
        	return(long) entityClass.getId() > 0;
        return true;
    }
    
    protected boolean serviceMethod(ServiceFunction serviceFonction, EntityClass entityClass) {
        try {
            serviceFonction.execute(entityClass);
            logsHandler.addInfo("Success of " + serviceFonction.getMessage() + entityClass.table().entityNameClass() + ".", (serviceFonction instanceof AddFunction ? HttpStatus.CREATED : HttpStatus.SUCCESS));
        }
        catch (Exception e) {
        	logsHandler.addError(e, HttpStatus.BAD_REQUEST); 
        }

        return logsHandler.isValid();
    }
}