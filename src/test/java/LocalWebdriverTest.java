import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

class LocalWebdriverTest extends TestCases
{	
	//These statements will be run before each unit test.
	@BeforeEach public void beforeTest()
	{
		  System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromeDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.get("https://test.javalearninglab.com/");
	}
	
	//This statement will run after each unit test.
	@AfterEach public void afterTest()
	{
		driver.quit();
	}
}