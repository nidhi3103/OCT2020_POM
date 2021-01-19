package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By header = By.linkText("Your Store");
	private By topNavBarList = By.xpath("//ul[@class='nav navbar-nav']/li");
	private By searchText = By.cssSelector("div#search input[name=search]");
	private By searchButton = By.cssSelector("div#search button[type=button]");
	private By searchResultItems = By.cssSelector("div.product-layout div.product-thumb");
	private By resultItems = By.cssSelector("div.product-thumb h4 a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}

	public String getAccountsPageHeader() {
		return elementUtil.waitForPageTextPresent(header, 5);
	}

	public int getTopNavListCount() {
		return elementUtil.getElements(topNavBarList).size();
	}

	public List<String> getTopNavListText() {
		List<String> topNavListText = new ArrayList<String>();
		List<WebElement> topNavList = elementUtil.getElements(topNavBarList);
		for (WebElement e : topNavList) {
			String ListText = e.getText();
			topNavListText.add(ListText);
		}
		return topNavListText;
	}

	// Search feature Page action
	public boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if (elementUtil.getElements(searchResultItems).size() > 0) {
			return true;
		}
		return false;
	}

	@Step("selectProductFromResults...")
	public void selectProductFromResults(String productName) {
//		List<String> productlistText = new ArrayList<String>();
		List<WebElement> productlist = elementUtil.getElements(resultItems);
		System.out.println("total number of items displayed: " + productlist.size());
		for (WebElement e : productlist) {
			String text = e.getText();
//			productlistText.add(text);
			if (text.equals(productName)) {
				e.click();
				break;
			}
		}
//		return productlist;
	}
}
