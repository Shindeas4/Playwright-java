package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.ConfigManager;

public class LoginPage extends BasePage {
    private final Locator signInWithSalesForceBtn;
    private final Locator salesForceEmailField;
    private final Locator signInBtn;
    private final Locator salesForcePassField;
    private final Locator loginBtn;
    private final Locator loggedUserName;
    private final Locator logout;

    // ✅ Constructor correctly initializes locators
    public LoginPage(Page page) {
        super(page);
        this.signInWithSalesForceBtn = page.getByText("Sign in with Salesforce");
        this.salesForceEmailField = page.getByPlaceholder("johndoe@xyz.com");
        this.signInBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in with Salesforce"));
        this.salesForcePassField = page.getByLabel("Password");
        this.loginBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in"));
        this.loggedUserName = page.locator(".sc-cPiKLX.evjsAa.MuiTypography-root.MuiTypography-body1.sc-hzhJZQ");
        this.logout = page.getByTitle("Sign Out");
    }


    public void login() {
        page.fill("#username", ConfigManager.get("USERNAME"));
        page.fill("#password", ConfigManager.get("PASSWORD"));
        loginBtn.click();
    }


    public void loginWithSalesforce() {
        signInWithSalesForceBtn.click();
        salesForceEmailField.fill(ConfigManager.get("USERNAME"));
        signInBtn.click();
        salesForcePassField.fill(ConfigManager.get("PASSWORD"));
        loginBtn.click();
    }



    public boolean isUserLoggedIn() {
        return loggedUserName.isVisible();
    }


    public void logout() {
        logout.click();
    }
}