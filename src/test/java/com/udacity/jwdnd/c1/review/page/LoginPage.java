package com.udacity.jwdnd.c1.review.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(id = "inputUsername")
	private WebElement inputUsername;
	
	@FindBy(id = "inputPassword")
	private WebElement inputPassword;
	
	@FindBy(id = "submit-button")
	private WebElement submitButton;
	
	@FindBy(id = "error-msg")
	private WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		// those object variables with @FindBy will be initialize here
		PageFactory.initElements(driver, this);
	}
	
	public void login(String username, String password) {
		
		inputUsername.sendKeys(username);
		
		inputPassword.sendKeys(password);
		
		submitButton.click();
	}
	
	public boolean isErrorMessageDisplay() {
		return errorMessage.isDisplayed();
	}
	
	
}
