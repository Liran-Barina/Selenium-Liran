import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter your user name: ");
        String userName = scanner.nextLine();
        System.out.println(" Enter your user password: ");
        String password = scanner.nextLine();

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\97252\\Desktop\\driver\\chromedriver.exe"
        );
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.aac.ac.il/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement menu = driver.findElement(By.id("menu-%d7%9b%d7%9c%d7%9c%d7%99"));
        if (menu != null) {
            List<WebElement> menuItems = driver.findElements(By.tagName("li"));
            WebElement personalInfo = menuItems.get(30);
            personalInfo.click();
        }
        WebElement userNameInput = driver.findElement(By.id("Ecom_User_ID"));
        if (userNameInput != null) {
            userNameInput.sendKeys(userName);
        }
        WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
        if (passwordInput != null) {
            passwordInput.sendKeys(password);
        }
        driver.findElement(By.id("wp-submit")).click();
        driver.get("https://moodle.aac.ac.il/login/index.php");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> courses = driver.findElements(By.className("course-summaryitem"));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> titles = courses.stream().map(e -> e.findElement(By.tagName("h6")).getText()).collect(Collectors.toList());
        System.out.println(" Your courses: ");
        int i = 1;
        for (String title : titles) {
            System.out.println(title + " ." + (i));
            i++;
        }
        System.out.println(" Choose a course number:");
        int courseNumInput = scanner.nextInt();
        courses.get(courseNumInput - 1).findElement(By.tagName("a")).click();

        driver.get("https://portal.aac.ac.il/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement exit = driver.findElement(By.id("menu-top-header"));
        if (exit != null) {
            List<WebElement> exitWebsite = exit.findElements(By.tagName("li"));
            WebElement website = exitWebsite.get(1);
            website.click();

        }

    }
}
