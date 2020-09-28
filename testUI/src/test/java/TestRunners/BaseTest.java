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

public class BaseTest {
    private Config config;
    private GoogleLanding googleLanding = new GoogleLanding();

    @Test
    public void Test() {
        open("https://google.com");
    }
}