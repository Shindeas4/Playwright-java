import base.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigManager;

public class LoginTest {
    private Page page;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        page = PlaywrightFactory.initBrowser();
        loginPage = new LoginPage(page);
    }

    @Test
    public void testLogin() {
        page.navigate(ConfigManager.get("BASE_URL") + "/login");
        loginPage.loginWithSalesforce();
    }

    @AfterMethod
    public void teardown() {
        PlaywrightFactory.closeBrowser();
    }
}
