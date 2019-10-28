package fabrique;

import entities.EntityClass;
import services.Service;

public class FabriqueAService {
    private Service service;
    private EntityClass entityClass;

    public FabriqueAService(EntityClass entityClass, Service service) {
        this.service = service;
        this.entityClass = entityClass;
    }

    public Service getService() {
        return service;
    }
    public EntityClass getEntity() {
        return entityClass;
    }
}
