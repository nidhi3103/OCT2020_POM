package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(DriverFactory.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void doSendKeys(By locator, String values) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(values);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public boolean doIsDisplated(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	/*************************** ActionsSendKeysAndClickMethod ******************/

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
//		action.moveToElement(getElement(locator)).click().build().perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();

	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public String getText(By locator) {
		return getElement(locator).getText();
	}

	/***********************************
	 * DropDown
	 ****************************************************/
	public void doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByIndex(By locator, int value) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(value);
	}

	// select function work only with select html tag
	public void doSelectDropDown(By locator, String type, String value) {
		Select select = new Select(getElement(locator));
		switch (type) {
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "visibleText":
			select.selectByVisibleText(value);
			break;
		default:
			System.out.println("select value case");
			break;
		}
	}

	public void doSelectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> optionlist = getElements(locator);
		System.out.println(optionlist.size());

		for (WebElement e : optionlist) {
			String optiontext = e.getText();
			System.out.println(optiontext);

			if (optiontext.equals(value))
				e.click();
			break;
		}
	}

	public void selectAllOptions(By locator, String value) {
		Select select = new Select(getElement(locator));

		List<WebElement> options_list = select.getOptions();
		System.out.println(options_list.size());

		for (WebElement e : options_list) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals("value")) {
				e.click();
				break;
			}
		}
	}

	public void selectFromSuggestedList(By locator, String value) {
		List<WebElement> resultlist = getElements(locator);
		System.out.println(resultlist.size());

		for (WebElement e : resultlist) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/********************************
	 * DragAndDrop
	 ***********************************/
	public void doDragAndDrop(By source_locator, By taget_locator) {
		WebElement source_ele = getElement(source_locator);
		WebElement traget_ele = getElement(taget_locator);

		Actions action = new Actions(driver);
		action.clickAndHold(source_ele).moveToElement(traget_ele).release(traget_ele).build().perform();
//		action.dragAndDrop(source_ele, traget_ele).perform();
	}

	/********************** Actions ***********************************************/
	public List<String> getRightClickMenuList(By rightClick_locator, By rightClickMenu_locator) {

		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick_locator)).perform();

		List<String> rightClickList = new ArrayList<String>();

		List<WebElement> menuList = getElements(rightClickMenu_locator);
		System.out.println(menuList.size());

		for (WebElement e : menuList) {
			String text = e.getText();
			rightClickList.add(text);
		}
		return rightClickList;
	}

	public void doRightClickOption(By rightClick_locator, By rightClickMenu_locator, String value) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick_locator)).perform();

		List<WebElement> menuList = getElements(rightClickMenu_locator);

		for (WebElement e : menuList) {
			String text = e.getText();

			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/************************** WebDriverWait util ******************************/
	public String waitForPageTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(titleValue));
		return driver.getTitle();
	}

	public String waitForPageTextPresent(By locator, int timeunit) {
		WebDriverWait wait = new WebDriverWait(driver, timeunit);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator).getText();
	}

	public String waitForPageTextVisibility(By locator, int timeunit) {
		WebDriverWait wait = new WebDriverWait(driver, timeunit);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator).getText();
	}

	/*************************** FluentWait *********************************/
	public WebElement doWaitForVisibilityOFElementWithFluentWait(By locator, int TimeOut, int PoolingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/*******
	 * This is a custom method to provide dynamic wait to find WebElement
	 ****/
	public WebElement retyingWebElement(By locator) {
		WebElement element = null;
		int attempt = 0;

		while (attempt < 15) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}

			} catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}
			System.out.println("no of attempts" + (attempt + 1));
			attempt++;
		}
		return element;
	}
}
