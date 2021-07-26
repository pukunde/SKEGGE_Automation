package com.skegge.tests;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.skegge.pages.LoginPage;

public class LoginPageTest extends LoginPage {
	LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		openApplication();
		loginPage = new LoginPage();
	}

	@Test(description = "Verify Login page title")
	public void tc01_verifyLoginPageTitle() {
		try {
			loginPage.verifyTitleOfSigninPage();
			Reporter.log("Successfully verified signin page title");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Fail to verify signin page title");
		}

	}
	@Test(description = "Verify login page contents")
	public void tc02_verifyLoginPageContents() {
		try {
			loginPage.verifyLoginPageContents();
			Reporter.log("Successfully verified login page contents");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Fail to verify login page contents");
		}

	}

	@Test(description = "Verify user is bale to successfully login with valid credentials")
	public void tc03_verifySuccessfulLogin() {
		try {
			loginPage.verifySuccessfulLogin("admin", "test@123");
			Reporter.log("Successfully signin to the application");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Fail to signin to the application");
			throw e;
		}

	}

	@DataProvider(name = "login_Error Message")
	public Object[][] dpMethod() {
		return new Object[][] { { "invalid", "test@123" }, { "admin", "invalid" }, { "invalid", "invalid" } };
	}

	@Test(description = "Verify error message for invalid login credentials ",dataProvider = "login_Error Message")
	public void tc04_verifyErrorMessageForLoginWithInvalidCredentials(String email, String pass) {
		try {
			loginPage.login(email,pass);
			loginPage.verifyErrorMessageForInvalidLogin();
			Reporter.log("Successfully verified error message for invalid login credentials");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Fail to verify error message for invalid login credentials");
			throw e;
		}
	}
}