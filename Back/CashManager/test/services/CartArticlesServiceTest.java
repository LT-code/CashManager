package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Article;
import entities.Cart;
import entities.CartArticles;
import entities.Machine;
import entities.Setting;
import exception.FailedDBConnection;
import exception.InvalidNumberReslut;
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
	
	@Test
    public void test_listArticles() throws SQLException, InvalidNumberReslut, ValidatorNotRecpectedException, NoResultException, JsonGenerationException, JsonMappingException, IOException {
		Article article2 = new Article("ONGYHU3ODZI342", "PC MacBook", 999.99F);
		CartArticles cartArticles2 = new CartArticles((long) cart.getId(), (String) article2.getId());
		
		articleService.add(article2);
		cartArticlesService.add(cartArticles);
		cartArticlesService.add(cartArticles);
		cartArticlesService.add(cartArticles2);
		
		Map<String, Object> list = cartArticlesService.listArticles((long) cart.getId());
		
		System.out.println(new ObjectMapper().writeValueAsString(list.toString()));
		
		cartArticlesService.delete(cartArticles);
		cartArticlesService.delete(cartArticles2);
		articleService.delete(article2);
	}

    @After
    public void tearDown() throws SQLException, ValidatorNotRecpectedException, NoResultException {
    	cartService.delete(cart);
    	machineService.delete(machine);
    	articleService.delete(article);
    	settingService.delete(setting);
    	afterTest();
    }
}
