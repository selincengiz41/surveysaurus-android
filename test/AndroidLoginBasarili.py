from appium import webdriver
from appium.webdriver.common.appiumby import AppiumBy
import time

desired_cap = {
    "deviceName": "emulator-5554",
    "platformName": "android",
    "app": "C:\\Users\\Simge\\Downloads\\app-release.apk",
    "appActivity": "com.android.surveysaurus.activity.MainActivity",
    "appPackage": "com.android.surveysaurus",
    "noReset": True
}

driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_cap)

el2 = driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="Bar Settings")
el2.click()

el3 = driver.find_element(by=AppiumBy.XPATH, value="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
el3.click()

el4 = driver.find_element(by=AppiumBy.ID, value="com.android.surveysaurus:id/editTextTextEmailAddress")
el4.send_keys("ali@gmail.com")
time.sleep(2)

el5 = driver.find_element(by=AppiumBy.ID, value="com.android.surveysaurus:id/editTextTextPassword")
el5.send_keys("Abc.123456")
time.sleep(3)

el6 = driver.find_element(by=AppiumBy.ID, value="com.android.surveysaurus:id/button")
el6.click()
time.sleep(2)

driver.quit()
