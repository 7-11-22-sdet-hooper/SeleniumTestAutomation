import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

class LocalWebdriverTest 
{
	WebDriver driver;
	// TODO Add method(s) to test cards for a blank or empty titles.
	// TODO Modify CardDefinition methods to test card title against definition from Database.
	// TODO Add test methods that use WebElement Class to confirm that elements are properly displayed.
	
	//These statements will be run before each unit test.
	@BeforeEach public void beforeTest()
	{
		  System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromeDriver\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.get("https://bespoke-scone-ba3a56.netlify.app/");
	}
	
	//This statement will run after each unit test.
	@AfterEach public void afterTest()
	{
		driver.quit();
	}
	
	//Unit Tests
	@Test
	public void testWebPageTitle()
	{
		String title = driver.getTitle();
		
		//the title of flash card web page is blank for now so this should be true....
		assertEquals(title, "");
	}

	@Test
	public void firstCardNameDemoTest()
	{
		WebElement element1 = driver.findElement(By.id("OOP1"));
		String cardTitle = element1.getText();
		assertEquals("inheritance", cardTitle);
	}
}