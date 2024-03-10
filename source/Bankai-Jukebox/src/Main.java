import com.bankai.jukebox.config.Constants;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.views.SplashScreen;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // MacOS
        if (SystemInfo.isMacOS) {
            // Application name used in screen menu bar
            System.setProperty("apple.awt.application.name", Constants.APP_NAME);
        }

        FlatMacLightLaf.setup();

        SplashScreen splashScreen = new SplashScreen(3000);
        splashScreen.showSplash();

        new HomePage();
    }
}