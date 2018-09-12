package com.ta.steps;

import com.ta.framework.utils.Browser;
import com.ta.framework.utils.CustomLogger;
import com.ta.framework.utils.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Hooks {

    private final static Logger LOGGER = CustomLogger.getLogger(ActionSteps.class);

    private Browser browser = Browser.getInstance();

    @Before
    public void initDriver() {
        DriverManager.initBrowser();
        open(browser.getBaseUrl());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveAllureScreenshot() throws IOException {
        BufferedImage originalImage = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getWebDriver()).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( originalImage, "png", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }

    @After
    public void closeDriver(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                saveAllureScreenshot();
            }
        }
        catch (IOException err) {
            LOGGER.error(err);
        }
        finally {
            getWebDriver().close();
        }

    }

}
