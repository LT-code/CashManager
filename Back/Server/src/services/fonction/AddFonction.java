package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;

import java.sql.SQLException;

import services.Service;
import services.fonction.ServiceFonction;
import tables.TableClass;

public class AddFonction extends ServiceFonction {
    public AddFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "creating ";
    }

    @Override
    public void execute(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("add " + entityClass.getClass());
        service.methodAdd(entityClass);
    }
}
