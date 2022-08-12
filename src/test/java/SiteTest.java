import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

class SiteTest 
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
	public void testBooleanCardName()
	{
        String cardName = "";  
		cardName = driver.findElement(By.xpath("//*[@id=\"1\"]")).getText();
		assertEquals(cardName, "boolean");
	}

	@Test
	public void testStringCardName()
	{
        String cardName = "";
		cardName = driver.findElement(By.xpath("//*[@id=\"2\"]")).getText();		
		assertEquals(cardName, "string");
	}

	@Test
	public void testIntegerCardName()
	{
        String cardName = "";
		cardName = driver.findElement(By.xpath("//*[@id=\"3\"]")).getText();		
		assertEquals(cardName, "integer");
	}

	@Test
	public void testValidCardTitleLength()
	{
		final int MAXCHARS = 12;	//max number of letters that will fit on a card without hanging off
        String cardName = "";
        boolean tooManyChar;
		cardName = driver.findElement(By.xpath("//*[@id=\"3\"]")).getText();
		
		if (cardName.length() > MAXCHARS)
			tooManyChar = true;
		else
			tooManyChar = false;
		
		assertFalse(tooManyChar);
	}
	
	@Test
	public void testIntegerCardDefinition()
	{
        String cardDef = "";
	    driver.findElement(By.id("3")).click();	
	     
		cardDef = driver.findElement(By.xpath("//*[@id=\"3\"]/div/p")).getText();		
		assertEquals(cardDef, "numbers without a fractional component, and don't support decimal points");
	}
	
	@Test
	public void testStringCardDefinition()
	{
        String cardDef = "";
	    driver.findElement(By.id("2")).click();	

		cardDef = driver.findElement(By.xpath("//*[@id=\"2\"]/div/p")).getText();
		assertEquals(cardDef, "series of characters that are interpreted literally by a script");
	}
	
	@Test
	public void testBooleanCardDefinition()
	{
        String cardDef = "";
	    driver.findElement(By.id("1")).click();

		cardDef = driver.findElement(By.xpath("//*[@id=\"1\"]/div/p")).getText();
		assertEquals(cardDef, "logical data type that can have only the values true or false");
	}
}