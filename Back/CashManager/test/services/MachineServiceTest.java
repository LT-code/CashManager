package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.fabrique.FabriqueAService;
import utils.DBConnector;

public class MachineServiceTest extends ServicesTest {
	private static MachineService machineService;
	private static SettingService settingService;
	private static Setting setting;
	private static Machine machine;
	
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection, ValidatorNotRecpectedException, NoResultException {
		db = new DBConnector(logsHandler);
		
		setting = new Setting();
    	settingService = new SettingService(db, logsHandler);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getId(), true, "HUG8E89Fz");
    	machineService = new MachineService(db, logsHandler);
    	
        fab = new FabriqueAService(machine, machineService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
        beforeTest();
    }
	
	@Test(expected=SQLException.class)
	public void test_SettingFKConstraint() throws ValidatorNotRecpectedException, NoResultException, SQLException {
		machine.setIdSetting(0);
		machineService.add(machine);
	}

    @After
    public void tearDown() throws SQLException, ValidatorNotRecpectedException, NoResultException {
    	settingService.delete(setting);
    	afterTest();
    }
}
