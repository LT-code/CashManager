package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import tables.Machine;
import tables.Setting;
import utils.DBConnector;
import utils.InitServer;

public class MachineServiceTest extends ServicesTest {
	private static MachineService machineService;
	private static SettingService settingService;
	private static Setting setting;
	private static Machine machine;
	private static DBConnector db;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		db = new DBConnector();
		
		setting = new Setting();
    	settingService = new SettingService(db);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getEntityID(), true, "HUG8E89Fz");
    	machineService = new MachineService(db);
    	
        fab = new FabriqueAService(machine, machineService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
    }

    @After
    public void tearDown() {
    	settingService.delete(setting);
    	db.close();
    }
}
