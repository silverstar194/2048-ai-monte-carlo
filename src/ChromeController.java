
/**
 * @author Admin
 *
 *
 *
 */

import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeController {


	public static void main(String[] args) throws InterruptedException, JSONException {
		// Create a new instance of the html unit driver
		// Notice that the remainder of the code relies on the interface, 
		// not the implementation.

		System.setProperty("webdriver.chrome.driver","/Users/Admin/Downloads/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--test-type");


		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		ChromeDriver driver = new ChromeDriver(caps);


		// And now use this to visit Google
		driver.get(Config.PATH_TO_2048);

		int lastInput = 0;

		
		//prevents infinite cycles
		int lastInputCount = 0;

		Board myGame = new Board(new int[4][4]);
		while(!myGame.gameOver()){

			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("console.clear();");

			MaxScoreMonteCarlo calcNextMove = new MaxScoreMonteCarlo(myGame);
			
			int input = calcNextMove.nextMove();
			
			if(input == lastInput){
				lastInputCount++;
			}else{
				lastInputCount = 0;
			}
			
			lastInput = input;
			
			if(lastInputCount > 5){
				System.out.println("Random");
				input = (int)(Math.random() * 4);
				lastInputCount = 0;
			}
			
			if(input == 0){
				driver.getKeyboard().sendKeys(Keys.ARROW_UP);
			}else if(input == 1){
				driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
			}else if(input == 2){
				driver.getKeyboard().sendKeys(Keys.ARROW_RIGHT);
			}else if(input == 3){
				driver.getKeyboard().sendKeys(Keys.ARROW_LEFT);
			}
			

			LogEntries logs = driver.manage().logs().get("browser");

			LogEntry l = null;
			if(logs.getAll().size() > 0){
			l = logs.getAll().get(logs.getAll().size()-1);
			}else{
				Thread.sleep(2000);
				logs = driver.manage().logs().get("browser");
				l = logs.getAll().get(logs.getAll().size()-1);
			}

			if(l.getMessage().contains("{")){
				String data = l.getMessage().substring(l.getMessage().indexOf("{"),l.getMessage().length()-1);
				JSONObject dataJSON = new JSONObject(data.replaceAll("\\\\", ""));
				JSONArray dataArray = dataJSON.getJSONObject("grid").getJSONArray("cells");

				int[][] board = new int[4][4];
				for(int j=0; j<dataArray.length(); j++){
					for(int k=0; k<dataArray.getJSONArray(j).length(); k++){

						if(dataArray.getJSONArray(j).get(k) instanceof JSONObject){
							int x = dataArray.getJSONArray(j).getJSONObject(k).getJSONObject("position").getInt("x");
							int y = dataArray.getJSONArray(j).getJSONObject(k).getJSONObject("position").getInt("y");
							board[y][x] = dataArray.getJSONArray(j).getJSONObject(k).getInt("value");
						}

					}

				}
				myGame = new Board(board);
				Thread.sleep(100);
			}

		}

		driver.quit();

	}

}
