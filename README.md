# SeleniumTestAutomation 
Maven Java project - Uses Selenium ChromeDriver and RemoteWebdriver to run JUnit test cases against our interview prep Web App.  

We have three run options:
 <br />__To run tests from local machine using ChromeDriver:__

Navigate to project directory from command line and enter:

    mvn -Dtest=LocalWebdriverTest test
    
 <br />__To run tests from remote machine using RemoteWebDriver:__

Navigate to project directory from command line and enter:

    mvn -Dtest=RemoteWebdriverTest test
    
 <br />__To run our Cucumber/Gherkin tests (from remote machine):__

Navigate to project directory from command line and enter:

    mvn -Dtest=CucumberTestRun test

  
  -------------------------------------------------------------------------------------------------------------------------------------
  
  __IMPORTANT INITIAL SETUP:__ (for Windows) (If you don't plan on using RemoteWebDriver capibilities then please disregard)
1. Create an account or 'sign in with github' at (Sauce Labs)[https://app.saucelabs.com/getting-started/guides/selenium]<br /><br />
2. Locate your Sauce Labs username and access key: 

<p align="center"> <img width="675" height="250" src="https://github.com/7-11-22-sdet-hooper/SeleniumTestAutomation/blob/main/media/media1.png?raw=true"> </p>

3. Go to start menu -> search for 'edit environment variables'-> click 'Environmental Variables...' button -> and locate the 'User variables for {your name}' section<br /><br />
4. Create a SAUCE_USERNAME user variable and set it's value to the username you recieved from the Sauce Labs website above. (oauth-....)<br /><br />
5. Create a SAUCE_ACCESS_KEY user variable and set it's value to the access key you recieved from the Sauce Labs website above: 

<p align="center"> <img width="600" height="400" src="https://github.com/7-11-22-sdet-hooper/SeleniumTestAutomation/blob/main/media/media2.png?raw=true"> </p>

<br /><br />You can confirm that these variables are set correctly by opening a powershell terminal and entering in:

    $env:SAUCE_USERNAME
<br /><p align="center"> <img width="750" height="200" src="https://github.com/7-11-22-sdet-hooper/SeleniumTestAutomation/blob/main/media/media3.png?raw=true"> </p>
