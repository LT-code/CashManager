package services.fonction;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public class DeleteFonction extends ServiceFonction {
    public DeleteFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "deleting ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("delete " + entityClass.getClass());
        service.methodDelete(entityClass);
    }
}
