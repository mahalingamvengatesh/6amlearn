package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Actions a;
	public static Robot r;
	public static TakesScreenshot ts;
	public static JavascriptExecutor js;
	public static Alert al;
	public static Select s;

	public static void browserLauch(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);

	}

	public static void fill(WebElement webElement, String text) {
		webElement.sendKeys(text);
	}

	public static void btnClick(WebElement loginButton) {
		loginButton.click();

	}

	public static void pageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
	}

	public static void pageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

	public static void windowsMaximize() {
		driver.manage().window().maximize();
	}

	public static void applyWaitToAllElement() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void takeSnap(String Filename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"C:\\Users\\LENOVO\\eclipse-workspace\\Windows\\screenshot.png" + Filename + ".png");
		FileUtils.copyFile(source, destination);
	}

	public static void findsyDateTime() {
		Date d = new Date(0);
	}

	public static void endsyDateTime() {
		Date dd = new Date(0);
	}

	public static void closeBrowser() {
		driver.quit();
	}

	public static void scroll(WebElement down, WebElement up) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
		js.executeScript("arguments[0].scrollIntoView(false)", up);
	}

	public static void printGetText(WebElement webElement) {
		String text = webElement.getText();
		System.out.println(text);
	}

	public static void performMoveToElement(WebElement webElement) {
		Actions a = new Actions(driver);
		a.moveToElement(webElement).perform();

	}

	public static void DraganDrop(WebElement source, WebElement destination) {
		a.dragAndDrop(source, destination).perform();
	}

	public static void performDoubleClick(WebElement webElement) {
		a.doubleClick(webElement).perform();
	}

	public static void rightClick(WebElement webElement) {
		a.contextClick(webElement).perform();
	}

	public static void toPerformEnter(WebElement webElement) throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void toAcceptAlert() {
		Alert al = driver.switchTo().alert();
		String string = al.toString();
		System.out.println(string);
		al.accept();
	}

	public static void DismissAlert() {
		al = driver.switchTo().alert();
		String string = al.toString();
		System.out.println(string);
		al.dismiss();
	}

	public static void togetAllOptionsDropAndDown(WebElement webElement) {
		Select s = new Select(webElement);
		List<WebElement> options = s.getOptions();
		for (WebElement webElement2 : options) {
			System.out.println(webElement2.getText());
		}
	}

	public static void getAllSelectedOptions(WebElement webElement) {
		s = new Select(webElement);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
		for (WebElement webElement2 : allSelectedOptions) {
			System.out.println(webElement2.getText());

		}
	}

	public static void selectByIndex(WebElement webElement, int index) {
		s = new Select(webElement);
		s.selectByIndex(index);
	}

	public static void selectByVisible(WebElement webElement, String text) {
		s = new Select(webElement);
		s.selectByVisibleText(text);
	}

	public static void selectByValue(WebElement webElement, String text) {
		s = new Select(webElement);
		s.selectByValue(text);
	}

	public static void switchwindow() {
		String windowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String eachwindow : windowHandles) {
			if (!eachwindow.equals(windowHandle)) {
				driver.switchTo().window(eachwindow);

			}
		}
	}

	public static void GetAttributed(WebElement webElement) {
		String attribute = webElement.getAttribute("value");
		System.out.println(attribute);
	}

	public  String excelRead(String filename,String sheet,int row,int cell,String elements ) throws IOException {
        File f = new File("");
        FileInputStream fin = new FileInputStream(f);
        Workbook w = new XSSFWorkbook(fin);
        Sheet s = w.getSheet(sheet);
        Row r = s.getRow(row);
        Cell c = r.getCell(cell);
        int type = c.getCellType();
        String value="";
        if (type==1) {
			 value = c.getStringCellValue();
		}else if (DateUtil.isCellDateFormatted(c)) {
			Date d = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/mm/yyyy");
			value = sim.format(d);
		}else {
			double num = c.getNumericCellValue();
			long l = (long)num;
			 value = String.valueOf(l);
			 //system.out.println(data);
		}
        return value;
        
        
        
        
        
        
        
	}
}
