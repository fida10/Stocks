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
		//GoogleSearch.clearScreenShots();
		GoogleSearch.newDirWithDate();
		GoogleSearch.searchCompaniesAndStockVals(dr);
		dr.quit();
		//this can be improved; it should be able to get the exact time and seconds and store it all in the same folders. More work is needed. It looks as though the process of making a directory is laced into the loop, thereby creating a new directory with every iteration
	}
}
