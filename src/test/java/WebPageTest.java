import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

class WebPageTest 
{
	// TODO Add method(s) to test cards for a blank or empty titles.
	// TODO Modify CardDefinition methods to test card title against definition from Database.
	@Test
	public void testWebPageTitle()
	{
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		String title = driver.getTitle();
		
		//the title of flash card web page is blank for now so this should be true....
		assertEquals(title, "");
		driver.quit();
	}

	@Test
	public void testBooleanCardName()
	{
        String cardName = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		cardName = driver.findElement(By.xpath("//*[@id=\"1\"]")).getText();
		assertEquals(cardName, "boolean");
		driver.quit();
	}

	@Test
	public void testStringCardName()
	{
        String cardName = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		cardName = driver.findElement(By.xpath("//*[@id=\"2\"]")).getText();		
		assertEquals(cardName, "string");
		driver.quit();
	}

	@Test
	public void testIntegerCardName()
	{
        String cardName = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		cardName = driver.findElement(By.xpath("//*[@id=\"3\"]")).getText();		
		assertEquals(cardName, "integer");
		driver.quit();
	}

	@Test
	public void testValaidCardTitleLength()
	{
		final int MAXCHARS = 12;	//max number of letters that will fit on a card without hanging off
        String cardName = "";
        boolean tooManyChar;
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		cardName = driver.findElement(By.xpath("//*[@id=\"3\"]")).getText();
		
		if (cardName.length() > MAXCHARS)
			tooManyChar = true;
		else
			tooManyChar = false;
		
		assertFalse(tooManyChar);
		driver.quit();
	}
	
	@Test
	public void testIntegerCardDefinition()
	{
        String cardDef = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");

	    driver.findElement(By.id("3")).click();	
	     
		cardDef = driver.findElement(By.xpath("//*[@id=\"3\"]/div/p")).getText();		
		assertEquals(cardDef, "numbers without a fractional component, and don't support decimal points");
		driver.quit();
	}
	
	@Test
	public void testStringCardDefinition()
	{
        String cardDef = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");

	    driver.findElement(By.id("2")).click();	

		cardDef = driver.findElement(By.xpath("//*[@id=\"2\"]/div/p")).getText();		
		
		assertEquals(cardDef, "series of characters that are interpreted literally by a script");
		driver.quit();
	}
	
	@Test
	public void testBooleanCardDefinition()
	{
        String cardDef = "";
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		
	    driver.findElement(By.id("1")).click();

		cardDef = driver.findElement(By.xpath("//*[@id=\"1\"]/div/p")).getText();		
		
		assertEquals(cardDef, "logical data type that can have only the values true or false");
		driver.quit();
	}
}