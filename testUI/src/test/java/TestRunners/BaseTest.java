package TestRunners;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.yaml.snakeyaml.Yaml;

import io.netty.handler.logging.LogLevel;

import static testUI.Utils.AppiumHelps.sleep;

import pages.GoogleLanding;

import TestRunners.ConfigClasses.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import testUI.Configuration;
import testUI.Utils.TestUIException;
import testUI.collections.UICollection;
import testUI.elements.UIElement;
import testUI.BrowserLogs;
import testUI.elements.WaitAsserts;

import static testUI.UIOpen.open;
import static testUI.UIOpen.navigate;
import static testUI.UIUtils.putLog;
import static testUI.UIUtils.executeJs;
import static testUI.Utils.AppiumHelps.sleep;
import static testUI.Utils.By.*;
import static testUI.elements.TestUI.E;
import static testUI.collections.TestUI.EE;
import static testUI.TestUIDriver.getSelenideDriver;
import static testUI.TestUIDriver.setDriver;

import static testUI.UIOpen.open;

public class BaseTest {
    private Config config;
    private GoogleLanding googleLanding = new GoogleLanding();

    @Before
    public void beforeHook() {
        Configuration.automationType = Configuration.DESKTOP_PLATFORM;
        Configuration.useAllure = false;
        Configuration.browserLogs = true;
//        Configuration.testUILogLevel = LogLevel.DEBUG;

        Yaml yaml = new Yaml();

        try {
            InputStream in = Files.newInputStream(Paths.get("config.yaml"));
            config = yaml.loadAs(in, Config.class);
        } catch (Exception e) {
            System.out.println("[ERROR] Could not get config: " + e.getMessage());
        }

        Configuration.browser = "chrome";

        ChromeOptions options = new ChromeOptions();
        config.getChromeOptions().forEach((v) -> {
            options.addArguments(v);
        });

        Map<String, Object> allowMedia = new HashMap<>();
        Map<String, Object> settings = new HashMap<>();
        settings.put("setting", 1);
        allowMedia.put("https://*,*", settings);
        Map<String, Object> mediaSettings = new HashMap<>();
        mediaSettings.put("media_stream_camera", allowMedia);
        mediaSettings.put("media_stream_mic", allowMedia);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("profile.content_settings.exceptions", mediaSettings);
        options.setExperimentalOption("prefs", prefs);

        Configuration.selenideBrowserCapabilities.setCapability(
                ChromeOptions.CAPABILITY, options);
    }

    @Test
    public void Test() {
        open("https://google.com").setElement(byCssSelector("helpmepls")).waitFor(10).untilIsVisible();

        getSelenideDriver().switchTo().frame(0);

        googleLanding.agreeButton.waitFor(10).untilIsVisible().click();

        googleLanding.searchInput.waitFor(10).untilIsVisible().sendKeys("TDL School Liepaja");

        sleep(2 * 1000);
    }
}