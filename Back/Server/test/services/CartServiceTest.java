package services;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Cart;
import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import utils.DBConnector;
import utils.ErrorsHandler;

public class CartServiceTest extends ServicesTest {
	private static CartService cartService;
	private static MachineService machineService;
	private static SettingService settingService;
	private static Cart cart;
	private static Machine machine;
	private static Setting setting;
	private static DBConnector db;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection {
		ErrorsHandler errHandler = new ErrorsHandler();
		db = new DBConnector(errHandler);
		
		setting = new Setting();
    	settingService = new SettingService(db, errHandler);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getId(), true, "HUG8E89Fz");
    	machineService = new MachineService(db, errHandler);
		machineService.add(machine);
    	
    	cart = new Cart((String) machine.getId());
    	cartService = new CartService(db, errHandler);
    	
        fab = new FabriqueAService(cart, cartService, new Long(0), new Long("84984165417115"));
    }
	
	@Test
	public void test_MachineFKConstraint() {
		cart.setIdMachine("FFFFFFFFFFFFFFFFFF");
		assertFalse(cartService.add(cart));
	}

    @After
    public void tearDown() throws SQLException {
    	machineService.delete(machine);
    	settingService.delete(setting);
    	db.close();
    }
}
