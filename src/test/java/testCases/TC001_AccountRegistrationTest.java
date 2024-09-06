package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	

	
	@Test (groups={"Regression", "Master"})
	
	public void verify_account_registration()
	{
		logger.info("******Starting TC001_AccountRegistrationTest*****");
		
		//accessing the 1st home page 
		try // In case test fails we're getting the errors 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Clicked on my account link");
		hp.clickRegister();
		logger.info("Clicked on Register link ");
		
		AccountRegistrationPage repage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details ");
		
		repage.setFirstName(randomeString().toUpperCase());
		repage.setLasttName(randomeString().toUpperCase());
		repage.setEmail(randomeString()+"@gmail.com"); // Randomly generate email ID everytime
		repage.setTelephone(randomeNumber());
		
		String password= randomeAlphaNumeric();
		
		repage.setPassword(password);
		repage.setConfirmPassword(password);
				
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		logger.info("Validating expected message ");
		String confmsg = repage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!" ))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
				
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}

	logger.info("******Finished TC001_AccountRegistrationTest*****");	
	
}}
