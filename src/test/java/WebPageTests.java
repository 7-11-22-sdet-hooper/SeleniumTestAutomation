import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class WebPageTests 
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
		
		//System.out.println(title);
		driver.quit();
	}
}