package com.tutorialninja.testcases;

import java.awt.RenderingHints.Key;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.pages.AccountPage;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.RegisterPage;
import com.tutorialninja.utilities.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initiliseBrowserAndOpenApplication(properties.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.clickOnRegister();
		
	}
	
	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	
	@Test
	public void verifyRegisteringAccWithMandFields() {
//		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(testdata.getProperty("firstName"));
		registerPage.lastNameField(testdata.getProperty("lastName"));
		registerPage.emailField(Utilities.generateEmail()+"@gmail.com");
		registerPage.telephoneField(testdata.getProperty("phone"));
		registerPage.passwordField(testdata.getProperty("password"));
		registerPage.confirmPasswordField(testdata.getProperty("password"));
		registerPage.checkboxField();
		registerPage.clickOnSubmitButtom();
		
//		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=\"Your Account Has Been Created!\"]")).isDisplayed());
//		String actualAccountRegisterSuccessHeading =  driver.findElement(By.xpath("")).getText();
//		Assert.assertEquals(actualAccountRegisterSuccessHeading, );
		Assert.assertTrue(registerPage.registerAccountSuccessfullMessage(testdata.getProperty("expectedAccountRegisterSuccessHeading")));
		
	}
	@Test
	public void verifyRegisteringAccWithAllFields() {
//		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(testdata.getProperty("firstName"));
		registerPage.lastNameField(testdata.getProperty("lastName"));
		registerPage.emailField(Utilities.generateEmail()+"@gmail.com");
		registerPage.telephoneField(testdata.getProperty("phone"));
		registerPage.passwordField(testdata.getProperty("password"));
		registerPage.confirmPasswordField(testdata.getProperty("password"));
		registerPage.clickOnSubscribeButton();
		registerPage.checkboxField();
		registerPage.clickOnSubmitButtom();
		Assert.assertTrue(registerPage.registerAccountSuccessfullMessage(testdata.getProperty("expectedAccountRegisterSuccessHeading")));

//		String actualAccountRegisterSuccessHeading =  driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
//		Assert.assertEquals(actualAccountRegisterSuccessHeading, testdata.getProperty("expectedAccountRegisterSuccessHeading"));
		
	}
	@Test
	public void verifyRegisteringAccWithExistingEmail() {
//		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameField(testdata.getProperty("firstName"));
		registerPage.lastNameField(testdata.getProperty("lastName"));
		registerPage.emailField(properties.getProperty("validEmail"));
		registerPage.telephoneField(testdata.getProperty("phone"));
		registerPage.passwordField(testdata.getProperty("password"));
		registerPage.confirmPasswordField(testdata.getProperty("password"));
		registerPage.clickOnSubscribeButton();
		registerPage.checkboxField();
		registerPage.clickOnSubmitButtom();
		
		Assert.assertTrue(registerPage.existingEmailWarningMessage(testdata.getProperty("expectedExistingEmailMessage")));
		
	}
	
	

}
