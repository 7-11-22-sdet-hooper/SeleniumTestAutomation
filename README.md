# SeleniumTestAutomation
Maven Java project; Runs JUnit unit tests that implement Selenium RemoteWebdriver;

__IMPORTANT INITIAL SETUP:__ (on Windows)
1. Create an account or 'sign in with github' at (Sauce Labs)[https://app.saucelabs.com/getting-started/guides/selenium]
2. Locate your Sauce Labs username and access key
3. Go to start menu -> search for 'edit environment variables'-> click 'Environmental Variables...' button -> and locate the 'User variables for PC' section
4. Create a SAUCE_USERNAME user variable and set it's value to the username you recieved from the Sauce Labs website above. (oauth-....)
5. Create a SAUCE_ACCESS_KEY user variable and set it's value to the access key you recieved from the Sauce Labs website above. 

You can confirm that these variables are set correctly by opening a powershell terminal and entering in:

    $env:SAUCE_USERNAME

-------------------------------------------------------------------------------------------------------------------------------------
__To run from command line:__

Navigate to project directory and enter:

    $ mvn clean test
    
    
__To run from Eclipse:__

  Right click project in the Package Explorer window->  Run As -> Maven test
