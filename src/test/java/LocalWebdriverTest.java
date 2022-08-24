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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

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