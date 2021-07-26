package com.skegge.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static FluentWait wait;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\application.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openApplication() {
		String browserName = prop.getProperty("browserName").toLowerCase();

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			Reporter.log("Invalid Broswer Name provided: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(prop.getProperty("baseURL"));
		Reporter.log("Successfully launched application");
	}

	public static void closeApplication() {
		driver.close();
	}

	public static void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void explicitWaitVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String getTitle() {
		return driver.getTitle();
	}

	public static String takeScreenshotFailedTC(WebDriver driver, String tcName) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY hh-mm-ss");
		Date date = new Date();
		String datetime = dateFormat.format(date);

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tcName + " dated " + datetime
				+ ".png";

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(screenshotPath));
		} catch (IOException e) {
			Reporter.log("Screenshot file not found!!");
			e.printStackTrace();
		}
		Reporter.log("Screenshot taken Successfully!!");

		return screenshotPath;

	}

	public static String getPropertyValue(String text) {
		return prop.getProperty(text);
	}

	public static void waitForWebElement(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.ignoring(TimeoutException.class);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void refreshPage() {
		driver.navigate().refresh();
	}

	public static void back() {
		driver.navigate().back();
	}
}
