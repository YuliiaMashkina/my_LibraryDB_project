package com.library.utilities;

import org.openqa.selenium.WebElement;

public class BrowserUtil {

    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getIntFromText(WebElement element){
        String str = element.getText();
        return Integer.parseInt(str);
    }




}
