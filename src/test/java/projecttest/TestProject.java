package projecttest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import projectpage.PageProject;

public class TestProject 
{
	WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void url()
	{
		driver.get("https://www.tripadvisor.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@Test
	public void test() throws Exception
	{
		PageProject ob=new PageProject(driver);
		ob.register();
		ob.login();
		ob.title();
		ob.logo();
		ob.content();
		ob.validation();
		ob.screenshot();
		ob.datepicker();
		ob.fileupload();
	}
}
