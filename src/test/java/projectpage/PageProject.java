package projectpage;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class PageProject 
{
	WebDriver driver;
	
	By sign = By.xpath("//*[@id=\"lithium-root\"]/header/div/nav/div[3]/a/span/div");
	By mail = By.xpath("//*[@id=\"ssoButtons\"]/button/span[2]");
	By join = By.xpath("//*[@id=\"regSignIn\"]/div[4]/button[2]/span");
	By fname = By.xpath("//*[@id=\"regSignUp.firstName\"]");
	By lname = By.xpath("//*[@id=\"regSignUp.lastName\"]");
	By regmail = By.xpath("//*[@id=\"regSignUp.email\"]");
	By regpass = By.name("password");
	By regsub = By.xpath("//*[@id=\"regSignUp.submit\"]");

	
	
	public PageProject(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void register()
	{
		driver.findElement(sign).click();
		driver.findElement(mail).click();
		driver.findElement(join).click();
		
		driver.findElement(fname).sendKeys("Ab");
		driver.findElement(lname).sendKeys("Cd");
		driver.findElement(regmail).sendKeys("abcd1234@rocketmail.com");
		driver.findElement(regpass).sendKeys("Abcd@12345");
		driver.findElement(regsub).click();
	}
	
	public void login() throws Exception
	{
		driver.findElement(sign).click();
		Thread.sleep(3000);
		driver.findElement(mail).click();
		
		File f=new File("C:\\Users\\Pulickan\\Documents\\Projectsignin.xlsx");
		FileInputStream fi=new FileInputStream(f);
		
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		
		String username=sh.getRow(1).getCell(0).getStringCellValue();
		String password=sh.getRow(1).getCell(1).getStringCellValue();
		
		driver.findElement(By.xpath("//*[@id=\"regSignIn.email\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"regSignIn.password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"regSignIn\"]/div[4]/button[1]")).click();

		wb.close();
	}
	
	public void title()
	{
		String actualtitle=driver.getTitle();
		System.out.println("Title is "+actualtitle);
		String expectedtitle="Tripadvisor - Registration";
		
		if(actualtitle.equals(expectedtitle))
		{
			System.out.println("Pass title");
		}
		else
		{
			System.out.println("Fail title");
		}
	}
	
	public void logo()
	{
		WebElement logo = driver.findElement(By.xpath("//*[@id=\"lithium-root\"]/header/div/nav/div[1]/a/picture[2]/img"));
		boolean b=logo.isDisplayed();
		
		if(b)
		{
			System.out.println("Logo is present");
		}
		else
		{
			System.out.println("Logo is not present");
		}
	}
	
	public void content()
	{
		String content=driver.getPageSource();
		
		if(content.contains("Tripadvisor"))
		{
			System.out.println("Pass content");
		}
		else
		{
			System.out.println("Fail content");
		}
	}
	
	public void validation() throws Exception
	{
		String url = driver.getCurrentUrl();
		@SuppressWarnings("deprecation")
		URL ul=new URL(url);
		HttpURLConnection con=(HttpURLConnection)ul.openConnection();
		con.connect();
		
		if(con.getResponseCode()==200)
		{
			System.out.println("Url is valid : "+url);
		}
		else
		{
			System.out.println("Url is invalid : "+url);
		}		
	}
	
	public void screenshot() throws Exception
	{
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,new File("./Screenshot/name.png"));
	}
	
	public void datepicker() throws Exception
	{
		driver.findElement(By.xpath("//*[@id=\"lithium-root\"]/header/div/nav/div[2]/div/div/div[4]/a/div")).click();
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[1]/div/div[1]/input[2]")).clear();
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[1]/div/div[1]/input[2]")).sendKeys("Kochi");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[1]/div/div[1]/input[2]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[1]/div/div[2]/input[2]")).sendKeys("Bangkok");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[1]/div/div[2]/input[2]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"rt_ui_picker\"]/span[1]/span[2]/span")).click();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/span/div[3]/div/div[2]/div[3]/span[2]/span[1]"));
		
		String month=element.getText();

		while(true)
		{
			if(month.equals("Mar 2024"))
			{
				System.out.println(month);
				break;
			}
			else
			{
				driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/span/div[3]/div/div[2]/div[1]")).click();
			}
		}
		
		List<WebElement> alldate = driver.findElements(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/span/div[3]/div/div[2]/div[3]/span[2]/span"));
		
		for(WebElement date:alldate)
		{
			String d=date.getText();
			if(d.equals("30"))
			{
				System.out.println(d);
				date.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//*[@id=\"taplc_trip_search_home_flights_0\"]/div[2]/div/span/div[1]/div[2]/div[4]/button")).click();
	}
	
	public void fileupload() throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"lithium-root\"]/header/div/nav/div[3]/div/a/div/div/div/picture/img")).click();
		driver.findElement(By.xpath("//*[@id=\"menu-item-0\"]/div/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"component_1\"]/div/div[1]/div/div/span[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[10]/div/div[2]/div/div[1]/div/div/div")).click();
		Thread.sleep(3000);
		
		String p="C:\\Users\\Pulickan\\Pictures\\Image.jpeg";
		StringSelection str= new StringSelection(p);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		Robot robot=new Robot();
		robot.delay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
