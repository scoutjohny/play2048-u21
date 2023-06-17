package tests;

import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MainPage;

public class GamePlay extends BaseTest{
    @BeforeMethod
    @Parameters({"browser","wait","env"})
    public void setup(String browser, String wait, String env) throws Exception {
        init(browser,wait);
        openApp(env);
    }

    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit){
        if(quit.equalsIgnoreCase("Yes")){
            quit();
        }
    }
    @Test(description = "New Game button test")
    @Description("Testiranje funkcionisanja dugmeta 'New Game'")
    public void newGame() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.startNewGame();
        mainPage.checkTileCount("2");
        mainPage.startNewGame();
        mainPage.checkTileCount("2");
    }

    @Test
    public void score() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.play(50);
        mainPage.checkScore();
    }

    @Test
    public void fullGame() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.playUntilGameOver();
    }

    @Test
    public void fullGameWin() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.playUntilGameOver("64");
    }
}
