import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps 
{
	static URL url;
	static ChromeOptions options;
	WebDriver driver;

	@Given("^Open Chrome and navigate to the Review site$")
	public void open_chrome_and_navigate_to_the_review_site() throws MalformedURLException
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
		driver = new RemoteWebDriver(url, options);
		driver.get("https://staging.javalearninglab.com/");

		System.out.println("This Step opens Chrome and navigates to the Review site");
	}

	@When("^Clicking a button from the card catagory navegation bar$")
	public void clicking_a_button_on_the_card_category_navegation_bar() 
	{
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.id("navbar-Java"))).click();
		System.out.println("This will click on a category navigation bar button.");
	}

	@Then("^Only cards from that category should be displayed$")
	public void only_cards_from_correct_category_displayed_Test() 
	{
		
		boolean allIDsMatches = true;
		
		List<WebElement> cards = driver.findElements(By.className("card"));
		String id;
		for(int i = 0; i < cards.size(); i++)
		{
			id = cards.get(i).getAttribute("id");
			id = id.substring(0, 4);
			if (!id.equals("Java"))
			{
				allIDsMatches = false;
			}
		}
		
		assertTrue(allIDsMatches);
		System.out.println("This step performs the test.");
		driver.close();
	}
}