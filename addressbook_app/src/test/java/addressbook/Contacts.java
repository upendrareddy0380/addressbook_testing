package addressbook;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Contacts 
{
	  FirefoxDriver driver;
	  String inputdata[]= {"krishan1","konijeti1","8788789879","krishana1@gmail.com","7/20/81"};
	  @Test(priority=1)
	  public void new_contact() throws Exception
	  {
		 driver = new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.get("http://54.172.28.188:7070/addressbook/");
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 new Actions(driver).moveToElement(driver.findElement(By.xpath("//span[text()='New contact']"))).click().perform();
		 
		 driver.findElement(By.id("gwt-uid-5")).sendKeys(inputdata[0]);
		 driver.findElement(By.id("gwt-uid-7")).sendKeys(inputdata[1]);
		 driver.findElement(By.id("gwt-uid-9")).sendKeys(inputdata[2]);
		 driver.findElement(By.id("gwt-uid-11")).sendKeys(inputdata[3]);
		 driver.findElement(By.id("gwt-uid-13")).sendKeys(inputdata[4]);
		 new Actions(driver).moveToElement(driver.findElement(By.xpath("//span[text()='Save']"))).click().perform();
		 Thread.sleep(3000);
	  }
	  @Test(priority=2)
	  public void validate_record()
	  {
		  
		  WebElement table=driver.findElement(By.xpath("//div[@class='v-grid-tablewrapper']"));
		  List<WebElement> cols=table.findElements(By.xpath("table/tbody/tr[1]/td"));
		  for(int c=1;c<=cols.size();c++)
		  {
			 if(inputdata[c-1].matches(table.findElement(By.xpath("table/tbody/tr[1]/td["+c+"]")).getText()))
			 {
				 Reporter.log(inputdata[c]+"<font color='green'><b> is matching</b></font>");
			 }
			 else
			 {
				 Reporter.log(inputdata[c]+"<font color='red'><b> NOT matching</b></font>");
			 }
		  }
		  
		  
	  }
}
