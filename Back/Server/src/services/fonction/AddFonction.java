package services.fonction;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import entities.EntityClass;
import services.Service;
import services.fonction.ServiceFonction;

public class AddFonction extends ServiceFonction {
    public AddFonction(Service service) {
        super(service);
    }

    @Override
    public String getMessage() {
        return "creating ";
    }

    @Override
    public void execute(EntityClass entityClass) throws ValidatorNotRecpectedException, NoResultException {
        if(!service.validatorAdd(entityClass))
            throw new ValidatorNotRecpectedException("add " + entityClass.getClass());
        service.methodAdd(entityClass);
    }
}
