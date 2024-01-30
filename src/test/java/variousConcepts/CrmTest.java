package variousConcepts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrmTest {

	WebDriver driver;

	String browser;
	String url;
	String userName;
	String password;
	String dashboardValidationText = "Dashboard";
	String userAlertValidationText = "Please enter your user name";
	String passwordAlertValidationText = "Please enter your password";
	String addCustomerValidationText = "New Customer";
	String fullName= "Selenium";
	String company= "Techfios";
//	String email
//	String phone=
	
	
	
	
	// ELEMENT LIST-By type
	By USER_NAME_FIELD = By.xpath("//input[@id='user_name']");
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
	By SIGNIN_BUTTON_FIELD = By.xpath("//*[@id=\"login_submit\"]");
	By DASHBOARD_VALIDATION_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");
	By CUSTOMER_MENU_FIELD = By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span");
	By ADD_CUSTOMER_MENU_FIELD = By.xpath("//*[@id=\"customers\"]/li[2]/a/span");
	By ADD_CUSTOMER_VALIDATION_FIELD = By
			.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/div[1]/div/div/header/div/strong");
	By FULL_NAME_VALIDATION_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[1]plabel");
	By COMPANY_DROPDOWN_FIELD = By.xpath("//select[@name='company_name']");

	public void readConfig() {
		// InputStream//BufferReader//FileReader//Scanner

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used:" + browser);
			url = prop.getProperty("url");
			userName = prop.getProperty(userName);
			password = prop.getProperty(password);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@BeforeMethod

	public void init() {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
	}

	{
		System.out.println("Please define a valid browser");

		driver.manage().deleteAllCookies();

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
	public void testLogin() {

		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_VALIDATION_FIELD).getText(), dashboardValidationText,
				":Dashboard page is not available");

	}

	@Test
	public void testAlert() {
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		;
		Assert.assertEquals(driver.switchTo().alert().getText(), userAlertValidationText, "Alert is not available");
		driver.switchTo().alert().accept();

		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		Assert.assertEquals(driver.switchTo().alert().getText(), passwordAlertValidationText, "Alert is not available");
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		driver.switchTo().alert().accept();

	}

	public void testAddCustomer() {
		testLogin();
		driver.findElement(CUSTOMER_MENU_FIELD).click();
		driver.findElement(ADD_CUSTOMER_MENU_FIELD).click();
//		Asser.assert
//		

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
