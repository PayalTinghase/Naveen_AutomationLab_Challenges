package day1Challenge;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorldoMeters {

	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException 
	{
		
		
		String currWorldpop="//div[@class='maincounter-number']/span[@class='rts-counter']";
		String today_thisYear_pop="//div[text()='Today' or text()='This year' ]/parent::div//span[@class=\"rts-counter\"]";
		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\ChromeDriver\\89\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.worldometers.info/world-population/");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		int count=0;
		while(count<=20)
		{
			System.out.println(count+" sec Data");
			System.out.println("=========Current population========");
			printPopulation(currWorldpop);
			System.out.println("=========Today and This year population========");
			printPopulation(today_thisYear_pop);
			Thread.sleep(1000);
			count++;
		}
	
		
	}
	
	public static void printPopulation(String locator)
	{
		List<WebElement> currWorldpop= driver.findElements(By.xpath(locator));
		
		for(WebElement e: currWorldpop)
		{
			System.out.println(e.getText());
		}
		
		//using Stream 
		
//		driver.findElements(By.xpath(locator))
//			.stream()
//					.forEach(e->System.out.println(e.getText()));
		
	}

}
