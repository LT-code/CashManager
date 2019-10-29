package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;

import java.sql.SQLException;

import services.Service;
import services.fonction.ServiceFonction;
import tables.TableClass;

public class DeleteFonction extends ServiceFonction {
    public DeleteFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "deleting ";
    }

    @Override
    public void execute(TableClass entityClass) throws ValidatorNotRecpectedException, NoResultException, SQLException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("delete " + entityClass.getClass());
        service.methodDelete(entityClass);
    }
}
