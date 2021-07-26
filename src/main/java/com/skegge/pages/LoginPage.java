package com.skegge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.skegge.base.TestBase;
import com.skegge.common.CommonUtils;

public class LoginPage extends TestBase {

	// Initializing page objects
	public LoginPage() {
		super();
		PageFactory.initElements(driver, this);
	}

	// Page Repository OR Page objects
	@FindBy(xpath = "//input[@name=\"userid\"]")
	@CacheLookup
	private WebElement emailTextBox;

	@FindBy(xpath = "//input[@name=\"password\"]")
	@CacheLookup
	private WebElement passwordTextBox;

	@FindBy(xpath = "//button[text()=\"Log In\"]")
	@CacheLookup
	private WebElement loginButton;

	@FindBy(xpath = "//li[@class=\"xn-logo\"]")
	@CacheLookup
	private WebElement logo;

	@FindBy(xpath = "//div[@class=\"login-title home_title login-logo\"]")
	@CacheLookup
	private WebElement loginLogo;

	@FindBy(xpath = "//div[@class=\"login-title\"]")
	@CacheLookup
	private WebElement loginPageTitle;

	@FindBy(xpath = "//a[@class=\"btn btn-link btn-block\"]")
	@CacheLookup
	private WebElement forgotPasswordHyperlink;

	@FindBy(xpath = "//strong[@class=\"login_message_padding\"]")
	@CacheLookup
	private WebElement invalidLoginErrorMessage;

	// Actions
	public void verifyTitleOfSigninPage() {
			pause(5000);
			String title = getTitle();
			Assert.assertTrue(title.equals("SKYGGE"));
	}

	public void verifySuccessfulLogin(String email, String pass) {
			login(email, pass);
			waitForWebElement(logo);
			Assert.assertTrue(logo.isDisplayed());
	}

	public void verifyLoginPageContents() {
			if (loginLogo.isDisplayed()) {
				Reporter.log("Login Logo is present");
			}
			if (loginPageTitle.isDisplayed()) {
				Assert.assertTrue(loginPageTitle.getText().equals(getPropertyValue("login.WelcomeMessage")));
				Reporter.log("Login Page Title is present : " + loginPageTitle.getText());
			}
			if (emailTextBox.isDisplayed()) {
				Reporter.log("Email Textbox is present");
			}
			if (passwordTextBox.isDisplayed()) {
				Reporter.log("Password Textbox is present");
			}
			if (loginButton.isDisplayed()) {
				Reporter.log("Login Button is present");
			}
			if (forgotPasswordHyperlink.isDisplayed()) {
				Assert.assertTrue(
						forgotPasswordHyperlink.getText().equals(getPropertyValue("forgotPassword.Hyperlink")));
				Reporter.log("Forgot Password Hyperlink is present: " + forgotPasswordHyperlink.getText());
			}
	}

	public void login(String email, String pass) {
			pause(5000);
			waitForWebElement(emailTextBox);
			CommonUtils.enter(emailTextBox, email);
			CommonUtils.enter(passwordTextBox, pass);
			CommonUtils.click(loginButton, 2);
			Reporter.log("Successfully entered email{ "+email+ " }and password{ "+pass+" }and clicked login button");
	}

	public void verifyErrorMessageForInvalidLogin() {
			waitForWebElement(invalidLoginErrorMessage);
			if (invalidLoginErrorMessage.isDisplayed()) {
				Reporter.log("Error Message : "+invalidLoginErrorMessage.getText());
			}				
	}

}
