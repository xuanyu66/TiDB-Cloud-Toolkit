package com.github.xuanyu66.tidbcloudtoolkit.backend;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.logging.Level;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthTokenResolver {

  private static final String AUTH_URL = "https://tidbcloud.com/";

  static {
    WebDriverManager.chromedriver().setup();
  }

  /**
   * @return tokens format as "Bearer \\W+"
   */
  public static String getUserToken(String user, String password) {
    ChromeDriver driver = getChromeDriver();

    loginTiDBCloud(user, password, driver);

    // wait the page to redirect
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10),
        Duration.ofMillis(500));
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ui.button")));

    String tokens = parseAuthToken(driver);

    driver.quit();
    return tokens;
  }

  @NotNull
  private static String parseAuthToken(ChromeDriver driver) {
    LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
    for (LogEntry entry : logEntries) {
      try {
        JSONObject json = new JSONObject(entry.getMessage());

        JSONObject message = json.getJSONObject("message");

        if (message.has("params")) {
          JSONObject params = message.getJSONObject("params");

          if (params.has("headers")) {
            JSONObject headers = params.getJSONObject("headers");
            if (headers.has("Authorization")) {
              return headers.getString("Authorization").toString();
            }
            if (headers.has("authorization")) {
              return headers.getString("authorization").toString();
            }
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    throw new IllegalArgumentException("Failed to get auth token from TiDB Cloud");
  }

  private static void loginTiDBCloud(String user, String password, ChromeDriver driver) {
    driver.get(AUTH_URL);
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10),
        Duration.ofMillis(500));
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    driver.findElement(By.id("email")).sendKeys(user);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("btn-login")).click();
  }

  @NotNull
  private static ChromeDriver getChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    options.setCapability(ChromeOptions.CAPABILITY, options);

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability("goog:loggingPrefs", logPrefs);
    return new ChromeDriver(options);
  }
}