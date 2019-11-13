package services;

import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.fabrique.FabriqueAService;
import utils.DBConnector;
import utils.LogsHandler;

@SuppressWarnings("deprecation")
public abstract class ServicesTest {
	protected FabriqueAService fab;
	protected LogsHandler logsHandler = new LogsHandler();
	protected DBConnector db;
	private String testName;

    public void afterTest() throws SQLException {
    	db.close();
    	logsHandler.displayLogs();
    }
    
    public void beforeTest() throws SQLException {
    	System.out.println("\n####################################################################################" );
		System.out.println("######## " + fab.getEntity().table().getName() + " ######## " + testName);
		System.out.println("####################################################################################" );
    }
	
	@Rule
	public TestWatchman watchman = new TestWatchman() {
		public void starting(FrameworkMethod method) {
			testName = method.getName();
		}
	};

	@Test
	public void addUpdateDelete() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getService().add(fab.getEntity());
		fab.getService().update(fab.getEntity());
		fab.getService().delete(fab.getEntity());
	}

	@Test
	public void addDelete() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getService().add(fab.getEntity());
		fab.getService().delete(fab.getEntity());
	}


	// ##########################################################################
	// Delete
	// ##########################################################################

	@Test(expected=ValidatorNotRecpectedException.class)
	public void failDelete_IdZero() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getEntity().setId(fab.getNullID());
		fab.getService().delete(fab.getEntity());
	}

	@Test(expected=NoResultException.class)
	public void failDelete_IdNoExist() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getEntity().setId(fab.getUnknownID());
		fab.getService().delete(fab.getEntity());
	}

	// ##########################################################################
	// Update
	// ##########################################################################

	@Test(expected=NoResultException.class)
	public void failUpdateDestination_IdNoExist() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getEntity().setId(fab.getUnknownID());
		fab.getService().update(fab.getEntity());
	}

	@Test(expected=NoResultException.class)
	public void failUpdateDestination_IdZero() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		fab.getEntity().setId(fab.getNullID());
		fab.getService().update(fab.getEntity());
	}
}
