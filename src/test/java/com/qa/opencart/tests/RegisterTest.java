package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {

	private RegisterPage registerPage;

	@BeforeClass
	public void setupAccountsPage() {
		registerPage = loginPage.clickRegisterLink();
	}

	@DataProvider
	public Object[][] getRegistertData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;

	}

	@Test(dataProvider = "getRegistertData")
	public void userRegistrationTest(String firstname, String lastname, String email, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(
				(registerPage.accountRegistration(firstname, lastname, email, telephone, password, subscribe)));
	}

}
