package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Description("Login page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Verify Forget password link test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = true)
	public void forgetPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}

	@Description("Login test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = true)
	public void loginTest() {
		loginPage.doLoginPage(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Cart exist on login page test....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void cartTest() {
		Assert.assertTrue(loginPage.isCartExist());

	}
}
