package fabrique;

import services.Service;
import tables.TableClass;

public class FabriqueAService {
    private Service service;
    private TableClass entityClass;
    private Object nullID;
    private Object unkownID;

    public FabriqueAService(TableClass entityClass, Service service, Object nullID, Object unkownID) {
        this.service = service;
        this.entityClass = entityClass;
        this.nullID = nullID;
        this.unkownID = unkownID;
    }

    public Service getService() {
        return service;
    }
    public TableClass getEntity() {
        return entityClass;
    }
    
    public Object getNullID() {
    	return nullID;
    }
    
    public Object getUnknownID() {
    	return unkownID;
    }
}
