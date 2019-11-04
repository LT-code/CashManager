package fabrique;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import utils.ErrorsHandler;

@SuppressWarnings("deprecation")
public class ServicesTest {
	protected FabriqueAService fab;
	protected ErrorsHandler errHandler = new ErrorsHandler();

	
	@Rule
	public TestWatchman watchman = new TestWatchman() {
		public void starting(FrameworkMethod method) {
			System.out.println("\n####################################################################################" );
			System.out.println("######## " + method.getName());
			System.out.println("####################################################################################" );
		}
	};

	@Test
	public void addUpdateDelete() {
		assertTrue(fab.getService().add(fab.getEntity()));
		assertTrue(fab.getService().update(fab.getEntity()));
		assertTrue(fab.getService().delete(fab.getEntity()));
	}

	@Test
	public void addDelete() {
		assertTrue(fab.getService().add(fab.getEntity()));
		assertTrue(fab.getService().delete(fab.getEntity()));
	}

	// ##########################################################################
	// Insert
	// ##########################################################################

	@Test
	public void failDouble_insert() {
		assertTrue(fab.getService().add(fab.getEntity()));
		assertFalse(fab.getService().add(fab.getEntity()));
		assertTrue(fab.getService().delete(fab.getEntity()));
	}

	// ##########################################################################
	// Delete
	// ##########################################################################

	@Test
	public void failDelete_IdZero() {
		fab.getEntity().setId(fab.getNullID());
		assertFalse(fab.getService().delete(fab.getEntity()));
	}

	@Test
	public void failDelete_IdNoExist() {
		fab.getEntity().setId(fab.getUnknownID());
		assertFalse(fab.getService().delete(fab.getEntity()));
	}

	// ##########################################################################
	// Update
	// ##########################################################################

	@Test
	public void failUpdateDestination_IdNoExist() {
		fab.getEntity().setId(fab.getUnknownID());
		assertFalse(fab.getService().update(fab.getEntity()));
	}

	@Test
	public void failUpdateDestination_IdZero() {
		fab.getEntity().setId(fab.getNullID());
		assertFalse(fab.getService().update(fab.getEntity()));
	}

	// ##########################################################################
	// function to override
	// ##########################################################################
}
