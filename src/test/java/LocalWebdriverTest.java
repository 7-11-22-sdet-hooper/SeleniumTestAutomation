import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

class LocalWebdriverTest 
{
	WebDriver driver;
	
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
	
	//Unit Tests
	@Test
	public void testWebPageTitle()
	{
		String title = driver.getTitle();
		
		//the title of flash card web page is blank for now so this should be true....
		assertEquals("", title);
	}

	@Test
	public void firstCardNameDemoTest()
	{
		// Explicitly wait until card element becomes clickable - timeout after 10 seconds
		  WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(10))
		          .until(ExpectedConditions.elementToBeClickable(By.id("OOP1")));
		     
		String cardTitle = element1.getText();
		assertEquals("inheritance", cardTitle);
	}
}