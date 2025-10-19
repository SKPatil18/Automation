package com.tutorialninja.testcases;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.pages.HomePage;
import com.tutorialninja.pages.SearchPage;

public class SearchTest extends Base{
	public WebDriver driver;
	SearchPage searchPage;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initiliseBrowserAndOpenApplication(properties.getProperty("browserName"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void verifyWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductInSearchField(testdata.getProperty("validProduct"));

		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.checkProductName(testdata.getProperty("expectedValidProductName")));
	}
	
	
	@Test
	public void VerifyWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductInSearchField(testdata.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
//		Assert.assertTrue(searchPage.verifyInvalidProductMessage("ahash"));

		
//		Assert.assertTrue(searchPage.checkProductName());	
//		driver.findElement(By.xpath("//input[@name = \"search\"]")).sendKeys(testdata.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("//div[@id=\"search\"]/span/button")).click();
		
		String actualText = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
		
		Assert.assertEquals(actualText,testdata.getProperty("expectedInvalidProductName"));		
	}
	@Test()
	public void verifyWithoutProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductInSearchField("");
		homePage.clickOnSearchButton();
		SearchPage searchPage = new SearchPage(driver);		
		Assert.assertTrue(searchPage.verifyInvalidProductMessage(testdata.getProperty("expectedInvalidProductName")));

		
//		driver.findElement(By.xpath("//input[@name = \"search\"]")).sendKeys("");
//		driver.findElement(By.xpath("//div[@id=\"search\"]/span/button")).click();
//		
//		String actualText = driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
//		
//		Assert.assertEquals(actualText,testdata.getProperty("expectedInvalidProductName"));
//		
	}
	
	

}
