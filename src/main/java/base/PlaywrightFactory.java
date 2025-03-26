package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import utils.ConfigManager;

public class PlaywrightFactory {
    private static final ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();

    public static Page initBrowser() {
        if (threadLocalPlaywright.get() == null) {
            threadLocalPlaywright.set(Playwright.create());
        }
        if (threadLocalBrowser.get() == null) {
            boolean isHeadless = ConfigManager.getBoolean("HEADLESS");
            threadLocalBrowser.set(threadLocalPlaywright.get().chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(isHeadless)));
        }
        threadLocalPage.set(threadLocalBrowser.get().newPage());
        return threadLocalPage.get();
    }

    public static void closeBrowser() {
        if (threadLocalPage.get() != null) threadLocalPage.get().close();
        if (threadLocalBrowser.get() != null) threadLocalBrowser.get().close();
        if (threadLocalPlaywright.get() != null) threadLocalPlaywright.get().close();

        threadLocalPage.remove();
        threadLocalBrowser.remove();
        threadLocalPlaywright.remove();
    }
}
