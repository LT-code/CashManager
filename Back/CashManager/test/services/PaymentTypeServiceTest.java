package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

import entities.PaymentType;
import exception.FailedDBConnection;
import services.fabrique.FabriqueAService;
import utils.bdd.DBConnector;

public class PaymentTypeServiceTest extends ServicesTest {
	private static PaymentTypeService service;
	private static PaymentType paymentType;
	
	
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection {
    	db = new DBConnector(logsHandler);
    	paymentType = new PaymentType("toto");
    	service = new PaymentTypeService(db, logsHandler);

        fab = new FabriqueAService(paymentType, service, new Long(0), new Long("84984165417115"));
        beforeTest();
    }

    @After
    public void tearDown() throws SQLException {
    	afterTest();
    }
}
