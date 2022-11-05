package com.udacity.jwdnd.c1.review;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.udacity.jwdnd.c1.review.page.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)	// tell the server to run on random port
class LoginTests {

	@LocalServerPort	// inject the server port
	private Integer port;
	private static WebDriver driver;
	private LoginPage loginPage;
	
	/**
	 * In the beforeAll method, we init the driver.
	 */
	@BeforeAll
	public static void beforeAll() {
		
		// Setup chrome Driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	/**
	 * In the afterAll method, we quit the driver.
	 */
	@AfterAll
	public static void afterAll() {
		driver.quit();
	}
	
	@BeforeEach
	public void beforeEach() {
		driver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(driver);
		
	}
	
	@Test
	public void shouldLoginFailed() {
		loginPage.login("user", "pass");
		assertTrue(loginPage.isErrorMessageDisplay());
	}
}
