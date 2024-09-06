package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid - login success - test pass - logout 
 * Date is valid - login failed - test fail 
 * 
 * Data is invalid - login success - test fail - logout 
 * Data is invalid - login failed - test pass
 */
@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups="datadriven") // getting data provider from different class

public class TC003_LoginDDT extends BaseClass {
	
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("***Started TC_003_LoginDDT***");	
         	//Homepage
	    try {
	    
	   
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			logger.info("clicked on my account and login button " );
			
			//Login page 
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			logger.info("passed email  " );
	        lp.setPassword(pwd);
	        logger.info("passed  password " ); 
	        lp.clickLogin();
			
			logger.info("clicked on login " );
			
			//MyAccount
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExists();
			
			/* Data is valid - login success - test pass - logout 
			 *               - login failed - test fail 
			 * 
			 * Data is invalid - login success - test fail - logout 
			 *                - login failed - test pass
			 */
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true)
				{
					Assert.assertTrue(true);
					macc.clickLogout();
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetpage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
	    } 
	    catch(Exception e )
	    {
	    	Assert.fail();
	    }
		logger.info("***Finished TC_003_LoginDDT***");	
	}
	
	}
