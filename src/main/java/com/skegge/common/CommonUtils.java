package com.skegge.common;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.skegge.base.TestBase;



public class CommonUtils extends TestBase {

	public static void SelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void clickActionThenSendkeys(WebElement element, String value) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(value);
		actions.build().perform();
	}

	public static void clear(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value ='';", element);
	}
	
	public static void enter(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public static void clickOnElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.build().perform();
	}

	public static void scrollDown(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, " + pixel + ");");
	}

	public static void scrollUp(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -" + pixel + ");");
	}

	public static void moveToElement(WebElement element, String value) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();
	}

	public static void click(WebElement element, int clicktype) {
		if (clicktype == 1) {
			element.click();
		} else if (clicktype == 2) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} else if (clicktype == 3) {
//				element.waitForVisible(40);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
//				Reporter.logWithScreenShot("Action click is succesfully performed",	
//						MessageTypes.Pass);
		} else {
//				Reporter.logWithScreenShot(
//						"Parameter provided is not correct: please provide correct method parameter",
//						MessageTypes.Fail);
		}
	}

	public static void hoverElement(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static void switchtoWindow() {

		String MainWindow = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> windowiterator = allwindows.iterator();
		while (windowiterator.hasNext()) {
			String ChildWindow = windowiterator.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
			}
		}
	}

	public static void pressCTRLtab() {

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
	}

	public static void openNewtab() {
		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
	}

	public static void ClickActionDropdownThenSendkeys(WebDriver driver, WebElement element, String value) {
		Actions act = new Actions(driver);
		act.moveToElement(element).click().build().perform();
		act.sendKeys(value).click().build().perform();
		;
	}

	public static void scrolIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void clearWebField(WebElement element) {
		while (!element.getAttribute("value").equals("")) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}
	

}
