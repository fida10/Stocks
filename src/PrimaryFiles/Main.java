package PrimaryFiles;

import SecondaryFiles.GoogleSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver","//Users//fida10//selenium//drivers//chrome//chromedriver"); //change this to your chromedriver
		WebDriver dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		dr.manage().window().maximize();

		dr.get("http://google.com");
		GoogleSearch.searchCompaniesAndStockVals(dr);
		dr.quit();
	}
}
