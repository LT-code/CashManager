package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

import entities.Article;
import entities.Cart;
import entities.CartArticles;
import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.fabrique.FabriqueAService;
import utils.bdd.DBConnector;

public class CartArticlesServiceTest extends ServicesTest {
	private static ArticleService articleService;
	private static Article article;
	private static CartArticlesService cartArticlesService;
	private static CartService cartService;
	private static MachineService machineService;
	private static SettingService settingService;
	private static Cart cart;
	private static Machine machine;
	private static Setting setting;
	private static CartArticles cartArticles;
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection, ValidatorNotRecpectedException, NoResultException {
		db = new DBConnector(logsHandler);
		
		setting = new Setting();
    	settingService = new SettingService(db, logsHandler);
    	settingService.add(setting);
    	
    	machine = new Machine("knb7T8U56787Hknkl", (Long) setting.getId(), true, "HUG8E89Fz");
    	machineService = new MachineService(db, logsHandler);
		machineService.add(machine);
		
    	article = new Article("122GYHU3342", "Souris logitech", 4.50F);
    	articleService = new ArticleService(db, logsHandler);
    	articleService.add(article);
    	
    	cart = new Cart((String) machine.getId());
    	cartService = new CartService(db, logsHandler);
    	cartService.add(cart);
    	
    	cartArticles = new CartArticles((long) cart.getId(), (String) article.getId());
    	cartArticlesService = new CartArticlesService(db, logsHandler);

        fab = new FabriqueAService(cartArticles, cartArticlesService, new Long("0"), new Long("64578364"));
        beforeTest();
    }

    @After
    public void tearDown() throws SQLException, ValidatorNotRecpectedException, NoResultException {
    	settingService.delete(setting);
    	machineService.delete(machine);
    	articleService.delete(article);
    	cartService.delete(cart);
    	afterTest();
    }
}
