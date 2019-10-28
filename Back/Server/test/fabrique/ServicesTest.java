package fabrique;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServicesTest {
    protected FabriqueAService fab;

    @Test
    public void addUpdateDelete() {
    	System.out.println("=========================== test addUpdateDelete " + fab.getEntity().getTable().getName());
        assertTrue(fab.getService().add(fab.getEntity()));
        assertTrue(fab.getService().update(fab.getEntity()));
        assertTrue(fab.getService().delete(fab.getEntity()));
    }

    @Test
    public void addDelete() {
    	System.out.println("=========================== test addDelete " + fab.getEntity().getTable().getName());
        assertTrue(fab.getService().add(fab.getEntity()));
        assertTrue(fab.getService().delete(fab.getEntity()));
    }

    //##########################################################################
    //  Delete
    //##########################################################################

    @Test
    public void failDelete_IdZero() {
    	System.out.println("=========================== test failDelete_IdZero " + fab.getEntity().getTable().getName());
        fab.getEntity().setEntityID(new Long(0));
        assertFalse(fab.getService().delete(fab.getEntity()));
    }

    @Test
    public void failDelete_IdNoExist() {
    	System.out.println("=========================== test failDelete_IdNoExist " + fab.getEntity().getTable().getName());
        fab.getEntity().setEntityID(new Long("84984165417115"));
        assertFalse(fab.getService().delete(fab.getEntity()));
    }


    //##########################################################################
    //  Update
    //##########################################################################

    @Test
    public void failUpdateDestination_IdNoExist() {
    	System.out.println("=========================== test failUpdateDestination_IdNoExist " + fab.getEntity().getTable().getName());
        fab.getEntity().setEntityID(new Long("84984165417115"));
        assertFalse(fab.getService().update(fab.getEntity()));
    }

    @Test
    public void failUpdateDestination_IdZero() {
    	System.out.println("=========================== test failUpdateDestination_IdZero " + fab.getEntity().getTable().getName());
        fab.getEntity().setEntityID(new Long(0));
        assertFalse(fab.getService().update(fab.getEntity()));
    }
    
    //##########################################################################
    //  function to override
    //##########################################################################
    
    
}