package services;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

import entities.Article;
import exception.FailedDBConnection;
import services.fabrique.FabriqueAService;
import utils.DBConnector;

public class ArticleServiceTest extends ServicesTest {
	private static ArticleService articleService;
	private static Article article;
	
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection {
    	db = new DBConnector(logsHandler);
    	article = new Article("122GYHU3342", "Souris logitech", 4.50F);
    	articleService = new ArticleService(db, logsHandler);

        fab = new FabriqueAService(article, articleService, new String(""), new String("FFFFFFFFFFFFFFFFFFFFF"));
        beforeTest();
    }

    @After
    public void tearDown() throws SQLException {
    	afterTest();
    }
}
