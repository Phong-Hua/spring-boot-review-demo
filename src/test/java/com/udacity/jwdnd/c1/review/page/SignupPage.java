package com.udacity.jwdnd.c1.review.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

	@FindBy(id = "inputFirstname")
	private WebElement inputFirstname;
	
	@FindBy(id = "inputLastname")
	private WebElement inputLastname;
	
	@FindBy(id = "inputUsername")
	private WebElement inputUsername;
	
	@FindBy(id = "inputPassword")
	private WebElement inputPassword;
	
	@FindBy(id = "submit-button")
	private WebElement submitButton;
	
	@FindBy(id = "error-msg")
	private WebElement errorMsg;
	
	@FindBy(id = "success-msg")
	private WebElement successMsg;
	
	public SignupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void signup(String firstName, String lastName, String username, String password) {
		
		// enter firstName, lastName, username and password
		inputFirstname.sendKeys(firstName);
		inputLastname.sendKeys(lastName);
		inputUsername.sendKeys(username);
		inputPassword.sendKeys(password);
		
		// click Submit button
		submitButton.click();
	}

	public boolean isSignupError() {
		return errorMsg.isDisplayed();
	}
	
	public boolean isSignupSuccess() {
		return successMsg.isDisplayed();
	}
}
