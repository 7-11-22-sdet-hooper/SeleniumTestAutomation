import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

//IMPORTANT: Set your local computers environmental variables SAUCE_USERNAME and SAUCE_ACCESS_KEY before running!
//See readme.md for more details.
class RemoteWebdriverTest 
{
	WebDriver driver;
	static URL url;
	static ChromeOptions options;
	
	//This method will run only once (performs pre-setup of our remoteWebDriver)
	@BeforeAll public static void beforeAllTest() throws MalformedURLException
	{
		options = new ChromeOptions();
		options.setPlatformName("Windows 11");
		options.setBrowserVersion("latest");

		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
		sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
		options.setCapability("sauce:options", sauceOptions);
			
		//If environmental variables SAUCE_USERNAME or SAUCE_ACCESS_KEY have not yet been set by user
		if (System.getenv("SAUCE_USERNAME") == null || System.getenv("SAUCE_ACCESS_KEY") == null)
		{
			throw new MalformedURLException("ERROR: Please set correct user enviroment variables (view Readme.md)");
		}
		else//If environmental variables have been set by user
		{
			//build URL string using environmental variables (which contain our user name and access key)
			String sauceLabURLString = "https://" + System.getenv("SAUCE_USERNAME") + ":" + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
			url = new URL(sauceLabURLString);
		}
	}
	
	//These statements will be run before each test.
	@BeforeEach public void beforeEachTest()
	{
			driver = new RemoteWebDriver(url, options);
			driver.get("https://test.javalearninglab.com/");
	}
	
	//This statement will run after each test.
	@AfterEach public void afterEachTest()
	{
		driver.quit();
	}

//Tests Cases

	//General site test cases
	@Test
	public void testWebPageTitle()
	{
		String title = driver.getTitle();
		
		//the title of flash card web page is blank for now so this should be true....
		assertEquals("", title);
	}

	//Cards page test cases
	@Test
	public void firstCardIsClickableTest()
	{
		boolean isClickable = true;
		try
		{
		// Explicitly wait until element becomes clickable - timeout after 10 seconds
		  new WebDriverWait(driver, Duration.ofSeconds(10))
		          .until(ExpectedConditions.elementToBeClickable(By.id("OOP1")));
		}
		catch (TimeoutException e)
		{	
			//if times out (never becomes clickable)
			isClickable = false;
		}
		finally
		{	//runs whether exception is thrown or not
			assertTrue(isClickable);
		}
	}
	
	//Quiz page test cases
	@Test
	public void firstQuizQuestionAppearsTest()
	{
		try 
		{
		// Explicitly wait until quiz side-bar button becomes clickable - timeout after 10 seconds
		WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(10))
		          .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/quiz']")));
		
		//click quiz side-bar button
		element1.click();
		
		// Explicitly wait until first quiz question becomes visable - timeout after 10 seconds
		WebElement questionElement = new WebDriverWait(driver, Duration.ofSeconds(10))
		          .until(ExpectedConditions.visibilityOfElementLocated(By.className("quiz-question")));
		// Copy text from the first question element;
		String questionText = questionElement.getText();
		
		// passes test if our question element has text.
		assertTrue(questionText != null);
		}
		catch(TimeoutException e) //wraps our whole test case in try-catch so it also fails our test if any timeouts happen(if any element are not found)
		{
			System.out.println("Driver timeout: Element not found!\n");
			e.printStackTrace();
			assertTrue(false);
		}
	}
}