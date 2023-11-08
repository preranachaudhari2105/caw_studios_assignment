package assignment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONValue;  
import org.junit.Assert;
public class assignment {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\omkar\\Downloads\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JSONParser parser = new JSONParser();
		Object obj;
		try {     
            obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\assignment\\jsondata.txt"));
            
            
    		driver.navigate().to("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
    		WebElement element = driver.findElement(By.tagName("summary"));
    		element.click();
    		WebElement inputBox = driver.findElement(By.id("jsondata"));
    		inputBox.clear();
    		String jsontext = JSONValue.toJSONString(obj);  
    		inputBox.sendKeys(jsontext);
    		WebElement refreshBtn = driver.findElement(By.id("refreshtable"));
    		refreshBtn.click();
    		
    		int numOfRow =  driver.findElements(By.xpath("//*[@id=\"dynamictable\"]//tr")).size();
    		int numOfCell;

    		WebElement data;
    		
    		JSONArray jarr = new JSONArray();
    		for(int i=2; i<=numOfRow; i++) {
    			numOfCell =  driver.findElements(By.xpath("//*[@id=\"dynamictable\"]/tr[" + i +"]/td")).size();
    			JSONObject jobj = new JSONObject();
    			jobj.put("gender", driver.findElement(By.xpath("//*[@id=\"dynamictable\"]/tr[" + i +"]/td[1]")).getText());
    			jobj.put("name", driver.findElement(By.xpath("//*[@id=\"dynamictable\"]/tr[" + i +"]/td[2]")).getText());
    			jobj.put("age", driver.findElement(By.xpath("//*[@id=\"dynamictable\"]/tr[" + i +"]/td[3]")).getText());

    			jarr.add(jobj);
    			
    			
			}
    		String actual_json = jarr.toJSONString();
    		Assert.assertEquals(jsontext, actual_json);
    		
    		
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
		
}
