package SecondaryFiles;

import net.bytebuddy.asm.Advice.Local;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleSearch {
	public static String stockGetterGoogle(WebDriver dr, String nameOfCompanyStock) throws IOException{
		WebElement searchBox = dr.findElement(By.xpath("//input[@title='Search']"));
		Actions a = new Actions(dr);
		a.moveToElement(searchBox).click().sendKeys(String.format(nameOfCompanyStock + " %s", "stock")).sendKeys(Keys.ENTER).build().perform();
		String stockValue = dr.findElement(By.xpath("//div[@class='f196ee']//div[1]//div[1]//g-card-section//div[1]//g-card-section//span[1]//span[1]//span[1]")).getText();
		System.out.println("The value of the stock from " + nameOfCompanyStock + " is " + stockValue);
		screenShot(dr);
		dr.navigate().back();
		return stockValue; //a change
	}
	public static String datedStringDir(){
		DateTimeFormatter dtForm = DateTimeFormatter.ofPattern("yyyy/MM/dd hh a");
		LocalDateTime current = LocalDateTime.now();
		String currentDate = "/" + dtForm.format(current);
		String fileDirToSave = String.format("//Users//fida10/Documents//Lynda Hackalicious exercise files//JavaPractice//Stocks//out//screenshots//ScreenshotsSaved%s", currentDate); //Change this path to where you want to save the screenshots
		return fileDirToSave;
	}
	public static void newDirWithDate(){
		String fileDirToSave = datedStringDir();
		boolean mkdirSuccess = new File(fileDirToSave).mkdir();
		System.out.println("Dated directory made successfully? " + mkdirSuccess);
	}
	public static void clearScreenShots() throws IOException{
		File savedDir = new File("//Users//fida10/Documents//Lynda Hackalicious exercise files//JavaPractice//Stocks//out//screenshots//ScreenshotsSaved");
		FileUtils.cleanDirectory(savedDir);
	}
	public static void screenShot(WebDriver dr) throws IOException {
		final String fileDirToSave = datedStringDir(); //this should mean that the first date and time gotten are the last for each instance of the running of the program
		File f = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(f, new File(fileDirToSave));
	}
	public static void searchCompaniesAndStockVals(WebDriver dr) throws IOException{
		String[] companies = {"Samsung", "United Airlines", "Airbus", "Boeing", "X", "Uber", "Lyft", "Netflix"}; //change this to whatever companies you want
		List<String> companiesInList = Arrays.asList(companies);
		List<String> valuesOfStocks = new ArrayList<>();
		for (int i = 0; i < companiesInList.size(); i++) {
			String stockValue = GoogleSearch.stockGetterGoogle(dr, companiesInList.get(i));
			valuesOfStocks.add(stockValue);
		}
		System.out.println("The companies searched for were the following: " + companiesInList);
		System.out.println("The returned stock values are as follows: " + valuesOfStocks);
	}
}
