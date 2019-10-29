package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;

import java.sql.SQLException;

import services.Service;
import services.fonction.ServiceFonction;
import tables.TableClass;

public class UpdateFonction extends ServiceFonction {
    public UpdateFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "updating ";
    }

    @Override
    public void execute(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorUpdate(entityClass))
            throw new ValidatorNotRecpectedException("update " + entityClass.getClass());
        service.methodUpdate(entityClass);
    }
}
