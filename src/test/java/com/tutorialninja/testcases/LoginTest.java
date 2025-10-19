package com.tutorialninja.testcases;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.pages.AccountPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.LoginPage;
import com.tutorialninja.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends Base{
	public WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initiliseBrowserAndOpenApplication(properties.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.clickOnLogin();
		
	}
	
	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	
	
	@Test(priority = 1,dataProvider = "DataSupplier")
	public void loginWithValidCredentials(String email, String pass) {
		
//		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(pass);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		
//		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditAccInfo());
	}
	
	@DataProvider(name = "DataSupplier")
	public Object[][] dataSupplier() {
		Object data[][] = Utilities.getTextDataFromExcel("Login");
		return data;
	}
	
	
	@Test(priority = 2)
	public void loginWithInvalidCredentials() {
//		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmail()+"@gmail.com");
		loginPage.enterPassword(testdata.getProperty("invalidPassword"));
		
		loginPage.clickOnLoginButton();
			
		Assert.assertTrue(loginPage.getEmailPasswordNotMatchingWarning());
		
	}
	
	@Test(priority = 3)
	public void verifyWithValidEmailAndInvalidPassword() {
		
		
//		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(properties.getProperty("validEmail"));
		loginPage.enterPassword(testdata.getProperty("invalidPassword"));
		
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.getEmailPasswordNotMatchingWarning());		
		
		
	}
	@Test(priority = 4)
	public void verifyWithInalidEmailAndValidPassword() {
//		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmail()+"@gmail.com");
		loginPage.enterPassword(properties.getProperty("validPassword"));
		
		loginPage.clickOnLoginButton();

		
		Assert.assertTrue(loginPage.getEmailPasswordNotMatchingWarning());

		
		
	}
	
	


}
