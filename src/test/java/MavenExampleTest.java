import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class MavenExampleTest 
{
	@Test
	public void testGoogle()
	{
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bespoke-scone-ba3a56.netlify.app/");
		String title = driver.getTitle();
		assertEquals(title, "");
		//System.out.println(title);
		driver.quit();
	}
}