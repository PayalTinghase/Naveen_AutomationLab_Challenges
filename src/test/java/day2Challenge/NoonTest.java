package day2Challenge;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoonTest {

	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException 
	{
	
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\ChromeDriver\\89\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.noon.com/uae-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		Thread.sleep(5000);
		printItems();	//Function call to print all the items section wise	
	}
	
	
	
	public static void printItems() throws InterruptedException
	{
		int count=1;
		//Find all possible section headings
		String headings="//div[@class='sc-gsTCUz bhdLno']/h3";
		List<WebElement> HeadingCount= driver.findElements(By.xpath(headings));
		List<WebElement> items;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		//for loop to iterate all possible sections
		for(int i=0;i<HeadingCount.size();i++)
		{
			//Scroll down to section heading and print section
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", HeadingCount.get(i));
			System.out.println("----------------------------------------------------------");
			System.out.println("Section "+(i+1)+":"+HeadingCount.get(i).getText());
			System.out.println("----------------------------------------------------------");
			
			//find out all items in section
			items= driver.findElements(By.xpath("(//div[@class='sc-gsTCUz bhdLno']/h3)["+(i+1)+"]/ancestor::div[@class='sc-fvhGYg kHEClt']/following-sibling::div//div[@class='e3js0d-7 jULUCI']"));
			System.out.println("Number of Iteams : "+items.size());
			
			//find swiper for a section
			WebElement swiper = driver.findElement(By.xpath("(//div[@class='sc-GTWni GkeGT']/div[2])["+(i+1)+"]"));
			
			//for loop to iterate all items in section
			for(int j=0;j<items.size();j++)
			{
			
			
				//Check if item is not displaed then only click on swiper button
				if(!items.get(j).isDisplayed() && swiper.isEnabled())
				{

					swiper.click();
					Thread.sleep(2000);
		
				}
			
				// wait to visible the product iteam and print
				System.out.print("Product :"+count+" : ");
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOf(items.get(j)));
				System.out.println(items.get(j).getText());
		
				count++;
			
			}
		
			count=1;
		
			//Scroll down as elements on a page getting loaded as you scroll the page
			executor.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(3000);
			
			//Again fetch the updated headers
			HeadingCount= driver.findElements(By.xpath(headings));
			System.out.println("===========================================================");
		
		}
		
		
		
	
	}


}
