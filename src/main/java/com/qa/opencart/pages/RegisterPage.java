package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

//	1. Declare Locators/Page Object
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	private By agreeCheckBox = By.cssSelector("input[type=checkbox]");
	private By continueButton = By.cssSelector("input[value=Continue]");
	private By accountSuccessMessage = By.cssSelector("#content h1");
	private By logoutButton = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

//	2. constructor of page class
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

//	3. Page Actions/ Methods	
	public boolean accountRegistration(String firstname, String lastname, String email, String telephone, String password,
			String subscribe) {
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPwd, password);

		if (subscribe.equals("Yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);

		String text = elementUtil.getText(accountSuccessMessage);
		if (text.contains(Constants.ACCOUNT_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logoutButton);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
