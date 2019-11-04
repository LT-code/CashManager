package services.fonction;

import java.sql.SQLException;

import entities.EntityClass;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.Service;

public class UpdateFonction extends ServiceFonction {
    public UpdateFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "updating ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorUpdate(entityClass))
            throw new ValidatorNotRecpectedException("update " + entityClass.getClass());
        service.methodUpdate(entityClass);
    }
}
