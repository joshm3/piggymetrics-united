package com.example;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;

import static org.junit.jupiter.api.Assertions.*;

public class PiggyFrontTest {

    private String USERNAME = "piggyuser"; //change username here for subsequent testing

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void SetupDriver() {
        driver = new HtmlUnitDriver(BrowserVersion.CHROME, true) {
            @Override
            protected WebClient modifyWebClient(WebClient client) {
                final WebClient webClient = super.modifyWebClient(client);
                webClient.getOptions().setCssEnabled(false);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setJavaScriptEnabled(true);
                webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
                webClient.getOptions().setRedirectEnabled(true);

                return webClient;
            }
        };

        driver.get("http://localhost:80");

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Test
    public void SeleniumPiggyFrontTest() throws java.lang.InterruptedException{
        // click "create new account"
        WebElement createAccountBtn = driver.findElement(By.xpath("/html/body/section[3]/div/div[1]/div[7]/a"));
        createAccountBtn.click();

        // enter username
        WebElement usernameInput = driver.findElement(By.xpath("/html/body/section[3]/div/div[2]/div[2]/div[4]/form/input[1]"));
        usernameInput.sendKeys(USERNAME); //CHANGE NAME HERE

        // enter password
        WebElement passwordInput = driver.findElement(By.xpath("/html/body/section[3]/div/div[2]/div[2]/div[4]/form/input[2]"));
        passwordInput.sendKeys("piggypassword");

        // click "register"
        WebElement registerBtn = driver.findElement(By.xpath("/html/body/section[3]/div/div[2]/div[2]/div[4]/form/button"));
        registerBtn.click();

        Thread.sleep(1000);

        // click "skip this step"
        WebElement skipBtn = driver.findElement(By.xpath("/html/body/section[3]/div/div[2]/div[2]/div[5]/div/a"));
        skipBtn.click();

        Thread.sleep(1000);
        //now in greeting page, ensure it is welcoming USERNAME
        WebElement welcomeElement = driver.findElement(By.xpath("/html/body/section[1]/div[3]"));
        String welcomeString = welcomeElement.getText();
        assertEquals(USERNAME + " metrics", welcomeString);

        // click "get in" bubble
        WebElement getInBubble = driver.findElement(By.xpath("/html/body/section[1]/div[5]/div/div[1]/div[1]"));
        getInBubble.click();

        Thread.sleep(1000);
        //now in account details page, ensure it is welcoming USERNAME
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]"));
        // Check if the logo is clickable
        boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
        assertTrue(isClickable);

        

        // click "add expense"
        WebElement addExpenseBtn = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[3]/div[1]"));
        addExpenseBtn.click();

        // enter expense number
        WebElement expenseInput = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/input[1]"));
        expenseInput.sendKeys("1501");

        // enter title name for expense
        WebElement expenseTitleInput = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/input[2]"));
        expenseTitleInput.sendKeys("Rent");

        // click "save changes" for expense
        WebElement saveExpenseBtn = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/div[5]"));
        saveExpenseBtn.click();
        Thread.sleep(1000);


        // click "add income"
        WebElement addIncomeBtn = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[2]/div[1]"));
        addIncomeBtn.click();

        // enter income number
        WebElement incomeInput = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/input[1]"));
        incomeInput.sendKeys("5001");

        // enter title name for income
        WebElement titleInput = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/input[2]"));
        titleInput.sendKeys("Salary");

        // click "save changes" for income
        WebElement saveIncomeBtn = driver.findElement(By.xpath("/html/body/section[1]/div[9]/div/div[5]"));
        saveIncomeBtn.click();
        Thread.sleep(1000);




        // click "spare money"
        WebElement spareMoneyInput = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[4]/div[4]/input"));
        spareMoneyInput.sendKeys("500");

        // select text bubble
        WebElement textBubble = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]/div"));
        textBubble.click();

        // select text area and enter notes
        WebElement notesTextArea = driver.findElement(By.xpath("/html/body/section[1]/div[8]/div/textarea"));
        notesTextArea.sendKeys("Some important notes about the savings.");

        // click "save text"
        WebElement saveTextBtn = driver.findElement(By.xpath("/html/body/section[1]/div[8]/div/div[3]/div[2]"));
        saveTextBtn.click();
        Thread.sleep(1000);

        // switch "accumulation account"
        WebElement accumulationAccountSwitch = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[4]/div[5]/label[1]"));
        accumulationAccountSwitch.click();

        // select "interest"
        WebElement interestInput = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[4]/div[5]/input[2]"));
        interestInput.sendKeys("3.5");

        // switch "monthly accumulation"
        WebElement monthlyAccumulationSwitch = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[4]/div[5]/label[3]"));
        monthlyAccumulationSwitch.click();
        Thread.sleep(1000);

        // click "save changes" at the bottom
        WebElement finalSaveBtn = driver.findElement(By.xpath("/html/body/section[1]/div[6]/div[5]"));
        finalSaveBtn.click();
        Thread.sleep(4000);

        // verify the "in a year" value

        WebElement afterSavingsValueElement = driver.findElement(By.id("after-savings-value"));

        // Get the text of the after-savings-value element (this will give you the "42" part)
        String mainValue = afterSavingsValueElement.getText().split(" ")[0];

        // Find the span element inside "after-savings-value" and get its text (this will give you the "500" part)
        WebElement spanElement = afterSavingsValueElement.findElement(By.tagName("span"));
        String spanValue = spanElement.getText();

        // Print or combine both values
        System.out.println("Main Value: " + mainValue);
        System.out.println("Span Value: " + spanValue);

        // You can also concatenate them if needed
        String combinedValue = mainValue + spanValue;
        System.out.println("Combined Value: " + combinedValue);

        // Example assertion: You can compare it with an expected value
        String expectedValue = "43190"; // Replace with the actual expected value
        assertEquals(expectedValue, combinedValue, "The combined after-savings-value does not match the expected value.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
