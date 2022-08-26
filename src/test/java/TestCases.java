import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases 
{
	protected WebDriver driver; //protected WebDriver variable will be be accessed by subclasses
	
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
		  new WebDriverWait(driver, Duration.ofSeconds(5))
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
	
	@Test
	public void onlyOneCardCanBeClickedAtATimeTest()
	{
		boolean fail = false;
		try
		{
		
			// wait until first card to becomes clickable before we begin! 
			new WebDriverWait(driver, Duration.ofSeconds(5))
			        .until(ExpectedConditions.elementToBeClickable(By.id("OOP1")));
			
			//confirm that no cards are currently clicked when the page is first loaded
			List<WebElement> allClickedCards = driver.findElements(By.className("active-circle-con"));
			if (!allClickedCards.isEmpty())//if a card is already clicked
			{
				fail = true;
			}
			
			//list will hold all card elements
			List<WebElement> allCards = driver.findElements(By.className("card"));
			
			WebElement currentCard;
			
			//for each card
			for (int i = 0; i < allCards.size(); i++)
			{
				currentCard = allCards.get(i); //point to current card
				currentCard.click();
				allClickedCards = driver.findElements(By.className("active-circle-con")); //get list of all clicked cards
				
				if (allClickedCards.size() != 1)//if no cards or multiple cards clicked then fail
				{
					fail = true;
					
					if(!currentCard.equals(allClickedCards.get(0)) && fail == false)//if the card currently clicked is not the card that was clicked on
					{																//and the there is only one card (otherwise fail would be true by now)
						fail = true;
					}
				}
				
				//wait until current card becomes clickable again
				new WebDriverWait(driver, Duration.ofSeconds(5))
		        .until(ExpectedConditions.elementToBeClickable(currentCard));
				
				currentCard.click();
				
				//generate updated list of all currently clicked cards
				allClickedCards = driver.findElements(By.className("active-circle-con")); 
				
				//if any cards are clicked then fail
				if (allClickedCards.size() != 0)
				{
					fail = true;
				}
			}
		
		}
		catch(TimeoutException e)
		{
			System.out.println("Error: Webelement search timeout");
			fail = true;
		}
		finally
		{	//assert "didn't fail"
			assertTrue(!fail);
		}
	}
	
	//Quiz page test cases
	@Test
	public void firstQuizQuestionAppearsTest()
	{
		try 
		{
			// Explicitly wait until quiz side-bar button becomes clickable - timeout after 10 seconds
			WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(5))
			          .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/quiz']")));
			
			//click quiz side-bar button
			element1.click();
			
			// Explicitly wait until first quiz question becomes visible - timeout after 10 seconds
			WebElement questionElement = new WebDriverWait(driver, Duration.ofSeconds(5))
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
	
	@Test
	public void allQuizQuestionsAccessableTest()
	{
		boolean accessible = false; // this field will only become true is we are able to navigate threw the full quiz
		
		try
		{
			// Explicitly wait until quiz side-bar button becomes clickable - timeout after 10 seconds
			WebElement element1 = new WebDriverWait(driver, Duration.ofSeconds(5))
			          .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/quiz']")));
			
			//click quiz side-bar button
			element1.click();	
			
			//for each quiz question
			for (int i = 0; i < 24; i++)
			{
				//wait for quiz question to become visible - timeout after 10 secs
				WebElement questionElement = new WebDriverWait(driver, Duration.ofSeconds(5))
				          .until(ExpectedConditions.visibilityOfElementLocated(By.className("quiz-answer")));
				questionElement.click();
				
				// If questionElement has a true or false valued
				if (questionElement.getText().equals("TRUE"))
				{
					new WebDriverWait(driver, Duration.ofSeconds(5))
					          .until(ExpectedConditions.visibilityOfElementLocated(By.id("quiz-selectionTRUE")));
					driver.findElement(By.id("quiz-selectionTRUE")).click();
					
					new WebDriverWait(driver, Duration.ofSeconds(5))
					          .until(ExpectedConditions.visibilityOfElementLocated(By.id("quiz-selectionFALSE")));
					driver.findElement(By.id("quiz-selectionFALSE")).click();
				}
				else	//for a regular, 4 answer question
				{
					questionElement = new WebDriverWait(driver, Duration.ofSeconds(5))
					          .until(ExpectedConditions.visibilityOfElementLocated(By.className("quiz-answer")));
					questionElement.click();
				}
				
				//make sure 'next' button is loaded and click it
				WebElement nextPageElement = new WebDriverWait(driver, Duration.ofSeconds(5))
				          .until(ExpectedConditions.visibilityOfElementLocated(By.id("quiz-next-button")));
				nextPageElement.click();
			}
			
			//wait for last quiz question to become visible then click submit
			WebElement questionElement = new WebDriverWait(driver, Duration.ofSeconds(5))
			          .until(ExpectedConditions.visibilityOfElementLocated(By.className("quiz-answer")));
			questionElement.click();
			driver.findElement(By.id("quiz-submit-button")).click();//submit quiz
			
			//wait for try again button to appear and then click
			WebElement questionElement1 = new WebDriverWait(driver, Duration.ofSeconds(5))
			          .until(ExpectedConditions.visibilityOfElementLocated(By.id("quiz-tryagain-button")));
			questionElement1.click();
			
			// Explicitly wait until first quiz question becomes visible, confirm title is not blank
			WebElement firstQuestionElement = new WebDriverWait(driver, Duration.ofSeconds(5))
			          .until(ExpectedConditions.visibilityOfElementLocated(By.className("quiz-question")));
			// Copy text from the first question element;
			String questionText = firstQuestionElement.getText();
			// passes test if our question element has text.
			
			//if driver was able to complete the quiz and make back to the first question, the test passes (accessible = true)
			if(questionText != null)
			{
				accessible = true;
			}
		}
		catch(TimeoutException e) //if quiz times out, this will be triggered
		{
			System.out.println("Error: Webelement search timeout");
			accessible = false;
		}
		finally //whether the quiz passed or failed or any exception thrown, this will trigger at the end no matter what
		{
			assertTrue(accessible);
		}
	}
}