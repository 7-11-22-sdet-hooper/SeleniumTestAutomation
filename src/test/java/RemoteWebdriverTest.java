import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//IMPORTANT: Set your local computers environmental variables SAUCE_USERNAME and SAUCE_ACCESS_KEY before running!
//See readme.md for more details.
class RemoteWebdriverTest extends TestCases
{
	
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
}