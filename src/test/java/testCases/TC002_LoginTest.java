package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	
	@Test(groups= {"sanity", "Master"})
	
	public void verify_login()
	{
		logger.info("Start TC002_LoginTest " );
		try
		{

		//Homepage
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("clicked on my account and login button " );
		
		//Login page 
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("passed email  " );
        lp.setPassword(p.getProperty("password"));
        logger.info("passed  password " );
        
        
		lp.clickLogin();
		
		logger.info("clicked on login " );
		
		//My account page 
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetpage, true,"Login failed");
		Assert.assertTrue(targetpage);
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished TC002_LoginTest " );
	}
	
}
