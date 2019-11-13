package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Cart;
import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.fabrique.FabriqueAService;
import utils.DBConnector;

public class CartServiceTest extends ServicesTest {
	private static CartService cartService;
	private static MachineService machineService;
	private static SettingService settingService;
	private static Cart cart;
	private static Machine machine;
	private static Setting setting;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection, ValidatorNotRecpectedException, NoResultException {
		db = new DBConnector(logsHandler);
		
		setting = new Setting();
    	settingService = new SettingService(db, logsHandler);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getId(), true, "HUG8E89Fz");
    	machineService = new MachineService(db, logsHandler);
		machineService.add(machine);
    	
    	cart = new Cart((String) machine.getId());
    	cartService = new CartService(db, logsHandler);
    	
        fab = new FabriqueAService(cart, cartService, new Long(0), new Long("84984165417115"));
        beforeTest();
    }
	
	@Test(expected=SQLException.class)
	public void test_MachineFKConstraint() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		cart.setIdMachine("FFFFFFFFFFFFFFFFFF");
		cartService.add(cart);
	}

    @After
    public void tearDown() throws SQLException, ValidatorNotRecpectedException, NoResultException {
    	machineService.delete(machine);
    	settingService.delete(setting);
    	afterTest();
    }
}
