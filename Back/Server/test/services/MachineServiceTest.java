package services;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import utils.DBConnector;
import utils.ErrorsHandler;

public class MachineServiceTest extends ServicesTest {
	private static MachineService machineService;
	private static SettingService settingService;
	private static Setting setting;
	private static Machine machine;
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
    	
        fab = new FabriqueAService(machine, machineService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
    }
	
	@Test
	public void test_SettingFKConstraint() {
		machine.setIdSetting(0);
		assertFalse(machineService.add(machine));
	}

    @After
    public void tearDown() throws SQLException {
    	settingService.delete(setting);
    	db.close();
    }
}
