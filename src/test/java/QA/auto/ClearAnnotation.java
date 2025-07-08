package QA.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ClearAnnotation {
	private RemoteWebDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		URL url = new URL("http://172.20.23.92:4443/wd/hub");
		driver = new RemoteWebDriver(url, dc);
	}

	@Test(priority = 1)
	public void Login() throws InterruptedException {
		driver.get("http://apollo2.humanbrain.in/");
		driver.manage().window().maximize();
		System.out.println("Server opened successfully");

		String parentWindow = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		try {
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));
			loginButton.click();
		} catch (Exception e) {
			Assert.fail("Login button not found or clickable");
		}

		Thread.sleep(4000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		Thread.sleep(4000);

		try {
			WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
			emailInput.sendKeys("teamsoftware457@gmail.com");
		} catch (Exception e) {
			Assert.fail("Email input field not found");
		}

		try {
			WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
			nextButton.click();
		} catch (Exception e) {
			Assert.fail("Next button after email not found");
		}

		try {
			WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
			passwordInput.sendKeys("Health#123");
		} catch (Exception e) {
			Assert.fail("Password input field not found");
		}

		try {
			WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
			nextButton2.click();
		} catch (Exception e) {
			Assert.fail("Next button after password not found");
		}

		Thread.sleep(5000);
		driver.switchTo().window(parentWindow);
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void table() throws InterruptedException {
		String parentWindow = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			WebElement searchTagInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search tags']")));
			searchTagInput.sendKeys("TestM\n");
		} catch (Exception e) {
			Assert.fail("Search tags input not found");
		}

		Thread.sleep(3000);

		try {
			WebElement atlasEditorIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-icon[@nbtooltip='Atlas Editor']")));
			atlasEditorIcon.click();
		} catch (Exception e) {
			Assert.fail("Atlas Editor icon not found or not clickable");
		}

		Thread.sleep(4000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	@Test(priority = 3)
	public void Direct_Draw_page() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			WebElement editMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/oldmenu.svg']")));
			editMenu.click();
		} catch (Exception e) {
			Assert.fail("Edit menu not clickable");
		}

		Thread.sleep(3000);

		try {
			WebElement contributors = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-accordion-item-header[text()='Contributors']")));
			contributors.click();
		} catch (Exception e) {
			Assert.fail("Contributors option not found or clickable");
		}

		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void select_contributor() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);

		try {
			WebElement contributorRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='radio'])[3]")));
			contributorRadio.click();
		} catch (Exception e) {
			Assert.fail("Contributor radio button not selectable");
		}

		Thread.sleep(3000);

		try {
			WebElement drawMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/paintbrush.svg']")));
			drawMenu.click();
		} catch (Exception e) {
			Assert.fail("Draw menu button not clickable");
		}

		Thread.sleep(3000);

		try {
			WebElement unlockButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Unlock']")));
			unlockButton.click();
		} catch (Exception e) {
			Assert.fail("Unlock button not clickable");
		}
	}

	@Test(priority = 5)
	public void atlas() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 50);

		try {
			WebElement workArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-accordion-item-header[text()=' Work Area ']")));
			workArea.click();
		} catch (Exception e) {
			Assert.fail("Work Area section not clickable");
		}

		Thread.sleep(3000);

		try {
			WebElement copyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Copy GeoJson']")));
			copyButton.click();
		} catch (Exception e) {
			Assert.fail("Copy GeoJson button not clickable");
		}

		Thread.sleep(3000);

		try {
			WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/saved.svg']")));
			saveButton.click();
		} catch (Exception e) {
			Assert.fail("Save button not clickable");
		}

		Thread.sleep(3000);

		try {
			WebElement clearAnnotationButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/clear_annotation.svg']")));
			clearAnnotationButton.click();
		} catch (Exception e) {
			Assert.fail("Clear Annotation button not clickable");
		}

		Thread.sleep(3000);
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
