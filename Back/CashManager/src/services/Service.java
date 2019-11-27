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
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.servlet.HttpStatus;
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

    //public abstract Map<String, Object> get();
    
    public void add(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        serviceMethod(new AddFunction(this), entityClass);
    }
    
    public void update(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        serviceMethod(new UpdateFunction(this), entityClass);
    }
    
    public void delete(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        serviceMethod(new DeleteFunction(this), entityClass);
    }
    
    /*public void methodGet(Object id) {
    	this.getDao().get(id);
    }*/
    
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
        if(entityClass.getId() instanceof Long)
        	return (long) entityClass.getId() > 0;
    	if(entityClass.getId() instanceof String)
        	return !entityClass.getId().equals("") ;
        return true;
    }
    
    protected void serviceMethod(ServiceFunction serviceFonction, EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        serviceFonction.execute(entityClass);
        logsHandler.addInfo("Success of " + serviceFonction.getMessage() + this.getDao().getTable().entityNameClass() + ".", (serviceFonction instanceof AddFunction ? HttpStatus.CREATED : HttpStatus.SUCCESS));
	}
}
