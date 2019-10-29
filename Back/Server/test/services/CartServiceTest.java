package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import tables.Cart;
import tables.Machine;
import tables.Setting;
import utils.DBConnector;
import utils.InitServer;

public class CartServiceTest extends ServicesTest {
	private static CartService cartService;
	private static MachineService machineService;
	private static SettingService settingService;
	private static Cart cart;
	private static Machine machine;
	private static Setting setting;
	private static DBConnector db;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		db = new DBConnector();
		
		setting = new Setting();
    	settingService = new SettingService(db);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getEntityID(), true, "HUG8E89Fz");
    	machineService = new MachineService(db);
		machineService.add(machine);
    	
    	cart = new Cart((String) machine.getEntityID());
    	cartService = new CartService(db);
    	
        fab = new FabriqueAService(cart, cartService, new Long(0), new Long("84984165417115"));
    }

    @After
    public void tearDown() {
    	machineService.delete(machine);
    	settingService.delete(setting);
    	db.close();
    }
}
