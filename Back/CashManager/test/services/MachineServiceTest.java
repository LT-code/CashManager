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

public class MachineServiceTest extends ServicesTest {
	private static MachineService machineService;
	private static SettingService settingService;
	private static Setting setting;
	private static Machine machine;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection {
		db = new DBConnector(logsHandler);
		
		setting = new Setting();
    	settingService = new SettingService(db, logsHandler);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getId(), true, "HUG8E89Fz");
    	machineService = new MachineService(db, logsHandler);
    	
        fab = new FabriqueAService(machine, machineService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
        beforeTest();
    }
	
	@Test
	public void test_SettingFKConstraint() {
		machine.setIdSetting(0);
		assertFalse(machineService.add(machine));
	}

    @After
    public void tearDown() throws SQLException {
    	settingService.delete(setting);
    	afterTest();
    }
}
