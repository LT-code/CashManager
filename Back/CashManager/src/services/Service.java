package services;

import java.sql.SQLException;
import java.util.Map;

import dao.Dao;
import entities.EntityClass;
import exception.InvalidNumberReslut;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.function.AddFunction;
import services.function.DeleteFunction;
import services.function.ServiceFunction;
import services.function.UpdateFunction;
import utils.servlet.HttpStatus;


public abstract class Service {
	private Dao dao;
	
	public Service(Dao dao) {
		this.dao = dao;
	}    

    public Dao getDao() {
    	return dao;
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
    
    public Map<String, Object> get(Object id) throws SQLException, InvalidNumberReslut {
    	Map<String, Object> map = this.getDao().get(id);
    	dao.getLogsHandler().addInfo("Success getting " + this.getDao().getTable().getName() + " id=" + id + ".");
    	return map;
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
        dao.getLogsHandler().addInfo("Success of " + serviceFonction.getMessage() + this.getDao().getTable().entityNameClass() + ".", (serviceFonction instanceof AddFunction ? HttpStatus.CREATED : HttpStatus.SUCCESS));
	}
}
