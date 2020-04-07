package SecondaryFiles;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
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
	} public static void screenShot(WebDriver dr) throws IOException {
		File f = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(f, new File("/Users/fida10/Documents/Lynda Hackalicious exercise files/JavaPractice/Stocks/out/screenshots/ScreenshotsSaved")); //Change this path to where you want to save the screenshots
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
