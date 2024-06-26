package io.appium.java_client.android;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class AndroidScreenRecordTest extends BaseAndroidTest {

    @BeforeEach
    public void setUp() {
        startActivity(".ApiDemos");
    }

    @Test
    public void verifyBasicScreenRecordingWorks() throws InterruptedException {
        try {
            driver.startRecordingScreen(
                    new AndroidStartScreenRecordingOptions()
                            .withTimeLimit(Duration.ofSeconds(5))
            );
        } catch (WebDriverException e) {
            if (e.getMessage().toLowerCase().contains("emulator")) {
                // screen recording only works on real devices
                return;
            }
        }
        Thread.sleep(5000);
        String result = driver.stopRecordingScreen();
        assertThat(result, is(not(emptyString())));
    }

}
