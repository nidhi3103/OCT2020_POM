package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void setupAccountsPage() {
		accountsPage = loginPage.doLoginPage(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Account page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts page title is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Description("Account page header test....")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true)
	public void accountsPageHeaderTest() {
		String header = accountsPage.getAccountsPageHeader();
		System.out.println("Accounts page header is: " + header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Description("Verify top navigation list on Accounts Page...")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true)
	public void accountsPageTopNavListTest() {
		List<String> topNavList = accountsPage.getTopNavListText();
		System.out.println(topNavList);
		Assert.assertEquals(topNavList, Constants.getAccTopNavList(), "Account topNav list is mismacthed....");
	}

	@Description("Verify counts of top navigation list on Accounts Page")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = true)
	public void accountsPageTopNavListCount() {
		Assert.assertTrue(accountsPage.getTopNavListCount() == Constants.ACCOUNTS_PAGE_TOP_NAV_COUNT);
	}

	/**
	 * @DataProvider public Object[][] getSheetData() { Object data[][] =
	 *               ExcelUtil.getTestData(Constants.SEARCH_SHEET_NAME); return
	 *               data;
	 * 
	 *               }
	 * 
	 *               @Description("Product Search Test...")
	 * @Severity(SeverityLevel.MINOR) @Test(dataProvider = "getSheetData") public
	 *                                void searchTest(String productName) {
	 *                                Assert.assertTrue(accountsPage.doSearch(productName));
	 *                                }
	 **/

	@DataProvider
	public Object[][] getProductSearchTest() {
		Object[][] data = new Object[][] { { "iMac" }, { "iPhone" } };
		return data;
	}

	@Description("Product Search Test...")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "getProductSearchTest")
	public void productSearchTest(String productname) {
		Assert.assertTrue(accountsPage.doSearch(productname));
	}

	@Description("Verify search of Product results.....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void verifyProductResultTest() {
		accountsPage.doSearch("MacBook");
		accountsPage.selectProductFromResults("MacBook");

	}
}
