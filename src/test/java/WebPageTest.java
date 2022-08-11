import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

class WebPageTest 
{
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
		//the title of flash card web page is blank for now so this should be true....
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
		final int MAXCHARS = 12;
        String cardName = "";
        boolean tooManyChar;
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		cardName = driver.findElement(By.xpath("//*[@id=\"3\"]")).getText();
		
		if (cardName.length() <= MAXCHARS)
			tooManyChar = false;
		else
			tooManyChar = true;
		
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
		
		System.out.println("The defenet is:" + cardDef);
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
		
		System.out.println("The defenet is:" + cardDef);
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
		
		System.out.println("The defenet is:" + cardDef);
		driver.quit();
	}
}