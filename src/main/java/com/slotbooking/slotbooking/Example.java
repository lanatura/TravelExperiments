package com.slotbooking.slotbooking;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Example {
	
	  private final WebDriver driver;

	  public Example(WebDriver driver) {
	      this.driver = driver; 
	  }

  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC2f85ccad87dfeba55f0d1c80c877e32c";
  public static final String AUTH_TOKEN = "147ae7afde1a8d320b1f08a1fb9f48a6";

  public void execute() throws TwilioRestException {
    

    // Build a filter for the MessageList
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    System.setProperty("webdriver.gecko.driver", "D:/Users/stanikan/Desktop/GeikoDriver/geckodriver.exe");
    Example login = new Example(new FirefoxDriver());
    String body=login.loginAs("suena4u@gmail.com", "Lanatura4u");
    if(body!=null){
    	body="Hello Nature..Sonio Speaking..Gentle Remainder.. Slot is open for Dec.. ";
    	TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        
        params.add(new BasicNameValuePair("Body", body));
        params.add(new BasicNameValuePair("To", "+17735309484"));
        params.add(new BasicNameValuePair("From", "+17088165352"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
        driver.close();
    }
    else{
    	body="Hello Nature..Sonio Speaking..No Slot Available for the selected days..";
    	/*driver.Dispose();*/
    }
    
  }
  
 
  

  public String loginAs(String username, String password) {
      driver.get("https://cgifederal.secure.force.com/?language=English&country=India");        
      driver.findElement(By.id("loginPage:SiteTemplate:siteLogin:loginComponent:loginForm:username")).sendKeys(username);
      driver.findElement(By.id("loginPage:SiteTemplate:siteLogin:loginComponent:loginForm:password")).sendKeys(password);
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      driver.findElement(By.id("loginPage:SiteTemplate:siteLogin:loginComponent:loginForm:loginButton")).click();
      //driver.findElement(By.className("button ui-button ui-widget ui-state-default ui-corner-all")).submit();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      
      WebElement webElement = driver.findElement(By.id("ctl00_nav_side1"));
      String[] daysToSelect={"24","25","26","27","28","29","30","31"};
      String result= webElement.getText();
      String availableDate = null;
      if(result.contains("December")){
      	String[] output=result.split("[^\\d]+");
      	for(int j=0;j<daysToSelect.length;j++){
      		if(daysToSelect[j].equalsIgnoreCase(output[1])){
      			availableDate = output[1];
      		}
      		
      	}
      }
      
    return availableDate;

  }

  
}