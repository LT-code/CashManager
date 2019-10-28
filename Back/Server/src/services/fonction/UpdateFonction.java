package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import entities.EntityClass;
import services.Service;
import services.fonction.ServiceFonction;

public class UpdateFonction extends ServiceFonction {
    public UpdateFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "updating ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        if(!service.validatorUpdate(entityClass))
            throw new ValidatorNotRecpectedException("update " + entityClass.getClass());
        service.methodUpdate(entityClass);
    }
}
