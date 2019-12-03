package services.function;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public class DeleteFunction extends ServiceFunction {
    public DeleteFunction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "deleting ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorDelete(entityClass))
            throw new ValidatorNotRecpectedException("delete " + entityClass.getClass());
        service.getDao().delete(entityClass);
    }
}
