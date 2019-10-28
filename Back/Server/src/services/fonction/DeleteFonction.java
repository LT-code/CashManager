package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import entities.EntityClass;
import services.Service;
import services.fonction.ServiceFonction;

public class DeleteFonction extends ServiceFonction {
    public DeleteFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "deleting ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("delete " + entityClass.getClass());
        service.methodDelete(entityClass);
    }
}
