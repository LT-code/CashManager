package services.function;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public class AddFunction extends ServiceFunction {
    public AddFunction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "creating ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("add " + entityClass.getClass());
        service.getDao().add(entityClass);
    }
}
