package com.nopcommerce.automation.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Site {

    private static final long DEFAULT_WAIT_TIMEOUT = 60;

    // -------------------- Driver --------------------
    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    public static WebDriverWait webDriverWait(long timeoutInSeconds) {
        return new WebDriverWait(driver(), Duration.ofSeconds(timeoutInSeconds));
    }

    public static WebDriverWait webDriverWait() {
        return webDriverWait(DEFAULT_WAIT_TIMEOUT);
    }

    // -------------------- Navigation --------------------
    public static void goToURL(String url) {
        driver().get(url);
    }

    public static void navigateBack() {
        driver().navigate().back();
    }

    public static void refreshPage() {
        driver().navigate().refresh();
    }

    public static void maximizeWindow() {
        driver().manage().window().maximize();
    }

    // -------------------- Delay --------------------
    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // -------------------- Element Helpers --------------------
    public static WebElement findElement(By locator) {
        return driver().findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return driver().findElements(locator);
    }

    public static boolean isElementPresent(By locator) {
        return !findElements(locator).isEmpty();
    }

    public static boolean isElementVisible(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // -------------------- Click Helpers --------------------
    public static void click(By locator) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        for (int i = 0; i < 10; i++) {
            try {
                ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", findElement(locator));
            } catch (StaleElementReferenceException e) {
                continue;
            }
            break;
        }
    }

    public static void click(WebElement element) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", element);
    }

    public static void clickwithoutJS(By locator) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        for (int i = 0; i < 10; i++) {
            try {
                findElement(locator).click();
            } catch (StaleElementReferenceException e) {
                continue;
            }
            break;
        }
    }

    public static void clickWithoutJS(WebElement element) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void clickRandomLink(By locator) {
        WebElement container = findElement(locator);
        List<WebElement> links = container.findElements(By.tagName("a"));
        if (!links.isEmpty()) click(links.get((int) (Math.random() * links.size())));
        else throw new NotFoundException("No link in container: " + locator);
    }

    public static WebElement selectFirstItemForClickAction(By locator) {
        List<WebElement> list = findElements(locator);
        if (!list.isEmpty()) {
            WebElement element = list.get(0);
            click(element);
            return element;
        }
        return null;
    }

    public static void selectFirstItemForClickActionWithoutJS(By locator) {
        List<WebElement> list = findElements(locator);
        if (!list.isEmpty()) list.get(0).click();
    }

    // -------------------- Textbox Helpers --------------------
    public static void enterTextBoxDetails(By locator, String text) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        findElement(locator).sendKeys(text);
    }

    public static void appendTextBoxDetails(By locator, String text) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        findElement(locator).sendKeys(text);
    }

    public static void clearandEnterTextBoxDetails(By locator, String text) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        clearTextBoxDetails(locator);
        findElement(locator).sendKeys(text);
    }

    public static void clearTextBoxDetails(By locator) {
        webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        findElement(locator).clear();
    }

    // -------------------- Text & Attributes --------------------
    public static String textValue(By locator) {
        return findElement(locator).getText();
    }

    public static String textValueInnerHTML(By locator) {
        return findElement(locator).getAttribute("innerHTML");
    }

    public static String textValueInnerHTML(WebElement element) {
        return element.getAttribute("innerHTML");
    }

    public static String getAttributeValue(By locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    // -------------------- Dropdown Helpers --------------------
    public static void selectElementFromDropDown(By locator, String item) {
        webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(findElement(locator));
        for (WebElement option : select.getOptions()) {
            if (option.getText().equalsIgnoreCase(item)) {
                select.selectByVisibleText(option.getText());
                break;
            }
        }
    }

    public static void selectElementFromDropDownByIndex(By locator, int index) {
        webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(findElement(locator)).selectByIndex(index);
    }

    public static String getSelectedValuefromDropdown(By locator) {
        return new Select(findElement(locator)).getFirstSelectedOption().getText();
    }

    public static List<String> getAllDropdownNameOptions(By locator) {
        webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        List<String> names = new ArrayList<>();
        for (WebElement elem : new Select(findElement(locator)).getOptions()) {
            names.add(elem.getText().trim());
        }
        return names;
    }

    public static List<WebElement> getAllDropdownOptions(By locator) {
        webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        return new Select(findElement(locator)).getOptions();
    }

    // -------------------- Wait Helpers --------------------
    public static void waitForUrlToChange(String url) {
        webDriverWait().until(d -> !d.getCurrentUrl().equals(url));
    }

    public static boolean getLoader(By locator) {
        return new FluentWait<>(driver()).withTimeout(Duration.ofMillis(2000)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class, TimeoutException.class).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // -------------------- Scroll Helpers --------------------
    public static void scrollPage(int x, int y) {
        ((JavascriptExecutor) driver()).executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    public static void scrollIntoViewport(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // -------------------- Action Helpers --------------------
    public static void clickElementWithAction(By locator) {
        WebElement elem = findElement(locator);
        new Actions(driver()).click(elem).build().perform();
    }

    public static void clickElementWithActionAfterMoving(By locator) {
        WebElement elem = findElement(locator);
        new Actions(driver()).moveToElement(elem).click(elem).build().perform();
    }

    public static void movetoElementByAction(By locator) {
        WebElement elem = findElement(locator);
        new Actions(driver()).moveToElement(elem).build().perform();
    }

    public static void movetoElementByActionandClick(By locator, By refBottom, By refUp, boolean clickElement) {
        WebElement elem = findElement(locator);
        WebElement refElem = findElement(refBottom);
        WebElement refUpElem = findElement(refUp);
        JavascriptExecutor js = (JavascriptExecutor) driver();

        int upperLocation = refUpElem.getLocation().getY();
        int bottomLocation = refElem.getLocation().getY();
        int actualLocation = elem.getLocation().getY();

        if (!(actualLocation >= upperLocation && actualLocation <= bottomLocation)) {
            js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", elem);
        }

        if (clickElement) click(elem);
    }

    public static Actions getActionEvent() {
        return new Actions(driver());
    }

    // -------------------- Checkbox Helpers --------------------
    public static void selectFirstCheckboxForClickAction(By locator) {
        List<WebElement> list = findElements(locator);
        if (!list.isEmpty() && !list.get(0).isSelected()) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", list.get(0));
        }
    }

    public static void unSelectCheckboxForClickAction(By locator, int index) {
        List<WebElement> list = findElements(locator);
        if (index < list.size() && list.get(index).isSelected()) {
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", list.get(index));
        }
    }

    // -------------------- Random / Positional Clicks --------------------
    public static int clickElementAtRandomLocation(By locator) {
        List<WebElement> list = findElements(locator);
        int index = (int) (Math.random() * list.size());
        clickWithoutJS(list.get(index));
        return index;
    }

    public static int clickElementAtRandomLocationWithAction(By locator) {
        List<WebElement> list = findElements(locator);
        int index = (int) (Math.random() * list.size());
        new Actions(driver()).click(list.get(index)).build().perform();
        return index;
    }

    public static WebElement getParent(WebElement childElement) {
        return (WebElement) ((JavascriptExecutor) driver()).executeScript("return arguments[0].parentNode;", childElement);
    }

    public static List<WebElement> getAllChild(WebElement parentElement) {
        return parentElement.findElements(By.xpath("./child::*"));
    }

    public static WebElement getChildAtLocation(WebElement parentElement, int location) {
        List<WebElement> childList = getAllChild(parentElement);
        return childList.get(location - 1);
    }

    public static WebElement getElementAtLocation(By locator, int location) {
        List<WebElement> list = findElements(locator);
        if (location > list.size() || location < 0) location = list.size() - 1;
        return list.get(location);
    }

    public static Long getCurrentWindowsPosition() {
        Object pos = ((JavascriptExecutor) driver()).executeScript("return window.pageYOffset;");
        return Long.valueOf(pos.toString());
    }

    // -------------------- Highlight / Debug --------------------
    public static void highlightElementTemporarily(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void unHighlightElementTemporarily(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].style.border=''", element);
    }

}
