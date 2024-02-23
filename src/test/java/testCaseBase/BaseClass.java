package testCaseBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
    Test base class for test cases classes, its methods are commonly required for every test cases
 */
public class BaseClass {

    static public WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"sanity","regression","master"})
    @Parameters({"os","browser"})
    public void setUp(String os, String br) throws InterruptedException, IOException {

        //loading properties file
        FileReader fileInputStream = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(fileInputStream);

        //loading log4j2 file
        logger = LogManager.getLogger(this.getClass());

        if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
            //launching operating system and browser based on condition  -remotely
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }else {
                System.out.println("No matching operating systems!");
                return;
            }

            //browser
            switch (br.toLowerCase()){
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser...");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }else if (properties.getProperty("execution_env").equalsIgnoreCase("local")){
            //launching browser based on condition  -locally we do not care about os in local
            switch(br.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("No matching browser...");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(500);

        driver.get(properties.getProperty("appURL"));
        //driver.get("http://localhost/opencart/upload/index.php");
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"sanity","regression","master"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString() {
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber() {
        String generatedString=RandomStringUtils.randomNumeric(10);
        return generatedString;
    }

    public String randomAlphaNumeric() {
        String str=RandomStringUtils.randomAlphabetic(3);
        String num=RandomStringUtils.randomNumeric(3);
        return (str+"@"+num);
    }

    public String captureScreen(String testMethodName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + testMethodName + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
