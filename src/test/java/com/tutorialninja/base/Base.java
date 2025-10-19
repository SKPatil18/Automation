package com.tutorialninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tutorialninja.utilities.Utilities;

public class Base {
	WebDriver driver;
	public Properties properties;
	public Properties testdata;
	
	public Base() {
		
		properties = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\config\\config.properties");
		
		testdata = new Properties();
		File filetestDataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\testdata\\testdata.properties");

		
		try {
			FileInputStream testdatafis = new FileInputStream(filetestDataFile);
			testdata.load(testdatafis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
		try {
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public WebDriver initiliseBrowserAndOpenApplication(String browserName) throws MalformedURLException  {
		
//		if(browserName.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//		}else if(browserName.equalsIgnoreCase("Edge")) {
//			driver = new EdgeDriver();
//		}
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browserName);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
		driver.get("https://tutorialsninja.com/demo/");

		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));

		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		
		return driver;
		
	}

}
