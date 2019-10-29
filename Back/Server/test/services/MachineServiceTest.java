package services;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import entities.Machine;
import entities.Setting;
import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import service.utils.InitServer;
import utils.DBConnector;

public class MachineServiceTest extends ServicesTest {
	private static MachineService machineService;
	private static SettingService settingService;
	private static Setting setting;
	private static Machine machine;
	
	@BeforeClass
	public static void setUpClass() {
		setting = new Setting();
    	settingService = new SettingService();
    	settingService.add(setting);
	}
	
    @Before
    public void setUp() {
    	
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getEntityID(), true, "HUG8E89Fz");
    	machineService = new MachineService();
    	
        fab = new FabriqueAService(machine, machineService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
    }

    @After
    public void tearDown() {
    }

}
