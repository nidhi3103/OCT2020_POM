package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

//	1. Declare Locators/Page Object

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[value=Login]");
	private By forgetPasswordLink = By.xpath("//div[@class='form-group']/a");
	private By AddressBook = By.xpath("//a[text()='Address Book']");
	private By cart = By.id("cart");
	private By registerlink = By.xpath("//div[@class='list-group']/a[text()='Register']");

//	2. constructor of page class

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

//	3. Page Actions/ Methods

	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}

	public boolean isForgetPwdLinkExist() {
		return elementUtil.doIsDisplated(forgetPasswordLink);

	}

	public void doClickAddressBook() {
		elementUtil.doClick(AddressBook);
	}

	public boolean isCartExist() {
		return elementUtil.doIsDisplated(cart);
	}

	public AccountsPage doLoginPage(String un, String pwd) {

		System.out.println("Login with: " + un + " " + pwd);
//		driver.findElement(username).sendKeys("un");
//		driver.findElement(password).sendKeys("pwd");
//		driver.findElement(loginButton).click();
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}

	public RegisterPage clickRegisterLink() {
		elementUtil.doClick(registerlink);
		return new RegisterPage(driver);

	}
}
