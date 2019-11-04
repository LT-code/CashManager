package fabrique;

import entities.EntityClass;
import services.Service;

public class FabriqueAService {
    private Service service;
    private EntityClass entityClass;
    private Object nullID;
    private Object unkownID;

    public FabriqueAService(EntityClass entityClass, Service service, Object nullID, Object unkownID) {
        this.service = service;
        this.entityClass = entityClass;
        this.nullID = nullID;
        this.unkownID = unkownID;
    }

    public Service getService() {
        return service;
    }
    public EntityClass getEntity() {
        return entityClass;
    }
    
    public Object getNullID() {
    	return nullID;
    }
    
    public Object getUnknownID() {
    	return unkownID;
    }
}
