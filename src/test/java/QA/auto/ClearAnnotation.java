package QA.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("The server is Opened sucessfully");
		WebDriverWait wait = new WebDriverWait(driver, 50);
		// WebElement viewerSectionLink = wait
		// 		.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Viewer']")));
		// viewerSectionLink.click();
		// System.out.println("--------------------------*****************-----------------------");
		// System.out.println("The Viewer Icon is clicked");
		String parentWindow = driver.getWindowHandle();
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement login = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));
		login.click();
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("The login Button is clicked");
		Thread.sleep(4000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		Thread.sleep(4000);
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		WebElement emailInput = wait2
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
		emailInput.sendKeys("teamsoftware457@gmail.com");
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("Mail I'd is entered");
		WebDriverWait wait3 = new WebDriverWait(driver, 20);
		WebElement Next = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
		Next.click();
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("The Next Button is clicked");
		WebDriverWait wait4 = new WebDriverWait(driver, 20);
		System.out.println("--------------------------*****************-----------------------");
		WebElement PasswordInput = wait4
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
		PasswordInput.sendKeys("Health#123");
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("Password is entered");
		WebDriverWait wait5 = new WebDriverWait(driver, 20);
		WebElement Next2 = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
		Next2.click();
		System.out.println("--------------------------*****************-----------------------");
		System.out.println("The Next Button is clicked");
		Thread.sleep(5000);
		driver.switchTo().window(parentWindow);
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void table() throws InterruptedException {
		String parentWindow = driver.getWindowHandle();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement table1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search tags']")));
			table1.sendKeys("TestM\n");
			Thread.sleep(3000);
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("The number Entered Successfully");
		} catch (Exception e) {
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("The number is not Entered successfully");
		}
		try {
			WebDriverWait wait7 = new WebDriverWait(driver, 30);
			WebElement table2 = wait7.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-icon[@nbtooltip='Atlas Editor']")));
			table2.click();
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("The Altas Editor is clicked");
		} catch (Exception e) {
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("The Atlas Editor is not clicked");
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
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement editt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/oldmenu.svg']")));
			editt.click();
			Thread.sleep(3000);
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("Editmenu selected Successfully");
		} catch (Exception e) {
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("Editmenu   is not selected");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement contri = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-accordion-item-header[text()='Contributors']")));
			contri.click();
			Thread.sleep(3000);
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("contributor selected Successfully");
		} catch (Exception e) {
			System.out.println("--------------------------*****************-----------------------");
			System.out.println("Contributor   is not selected");
		}}
		@Test(priority = 4)
		public void select_contributor() {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='radio'])[3]")));
				radio.click();
				Thread.sleep(3000);
				System.out.println("--------------------------*****************-----------------------");
				System.out.println("The software Team contributor is selected Successfully");
			} catch (Exception e) {
				System.out.println("--------------------------*****************-----------------------");
				System.out.println("The software Team contributor is  not selected");
			}
		
			  try {
					WebDriverWait wait = new WebDriverWait(driver, 30);

			        WebElement edit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/viewer/assets/images/colorsvg/paintbrush.svg']")));
			        edit.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The Draw Menu is clicked");
			    } catch (Exception e) {
			        System.out.println("The Draw Menu is not clicked");
			    }

			    
			    try {
					WebDriverWait wait = new WebDriverWait(driver, 30);
					WebElement unlock = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Unlock']")));
					unlock.click();
					System.out.println("--------------------------*****************-----------------------");
					System.out.println("The Unlock button is clicked");
				} catch (Exception e) {
					System.out.println("--------------------------*****************-----------------------");
					System.out.println("The Unlock button is not clicked");
				}
			}

			    @Test (priority=5)
			    public void atlas() throws InterruptedException {
			    	try {
				        WebDriverWait wait = new WebDriverWait(driver, 50);
				        WebElement workarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nb-accordion-item-header[text()=' Work Area ']")));
				       workarea.click();
				        Thread.sleep(3000);
				        System.out.println("-------------------------------------------------");
				        System.out.println("The work area is selected");
				    } catch (Exception e) {
				        System.out.println("The work area is not selectedd");
				    }
//			   
//			    try {
//			        WebDriverWait wait = new WebDriverWait(driver, 50);
//			        Thread.sleep(3000);
//			        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()=' 286 ']")));
//			       section.click();
//			        Thread.sleep(3000);
//			        System.out.println("-------------------------------------------------");
//			        System.out.println("The section is selected");
//			    } catch (Exception e) {
//			        System.out.println("The section is not selectedd");
//			    }
//			    try {
//			        WebDriverWait wait = new WebDriverWait(driver, 50);
//			        WebElement contributor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()=' Base Atlas ']")));
//			       contributor.click();
//			        Thread.sleep(3000);
//			        System.out.println("-------------------------------------------------");
//			        System.out.println("The contributor is selected");
//			    } catch (Exception e) {
//			        System.out.println("The contributor is not selectedd");
//			    }
//			    try {
//			        WebDriverWait wait = new WebDriverWait(driver, 50);
//			        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='Append']")));
//			       option.click();
//			        Thread.sleep(3000);
//			        System.out.println("-------------------------------------------------");
//			        System.out.println("The option is selected");
//			    } catch (Exception e) {
//			        System.out.println("The option is not selectedd");
//			    }
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, 50);
			        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Copy GeoJson']")));
			        button.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The copy button is clicked");
			    } catch (Exception e) {
			        System.out.println("The copy button is not clicked");
			    }
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, 50);
			        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/viewer/assets/images/colorsvg/saved.svg']")));
			        save.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The save button is clicked");
			    } catch (Exception e) {
			        System.out.println("The save button is not clicked");
			    }
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, 50);
			        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/viewer/assets/images/colorsvg/clear_annotation.svg']")));
			        save.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The Clear Annoation button is clicked");
			    } catch (Exception e) {
			        System.out.println("The Clear Annoation is not clicked");
			    }
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, 50);
			        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
			        button.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The Delete button is clicked");
			    } catch (Exception e) {
			        System.out.println("The Delete button is not clicked");
			    }
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, 50);
			        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/viewer/assets/images/colorsvg/saved.svg']")));
			        save.click();
			        Thread.sleep(3000);
			        System.out.println("-------------------------------------------------");
			        System.out.println("The save button is clicked");
			    } catch (Exception e) {
			        System.out.println("The save button is not clicked");
			    }
			       	}
			    @AfterTest
				public void tearDown() {
					if (driver != null) {
						driver.quit();
					}}
			}