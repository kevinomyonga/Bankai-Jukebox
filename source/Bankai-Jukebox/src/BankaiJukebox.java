import com.bankai.jukebox.pages.HomePage;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BankaiJukebox {
    public static void main(String[] args) {

//        FlatMacLightLaf.setup();
        FlatMacDarkLaf.setup();

//        Jukebox jukebox = new Jukebox();
//        jukebox.guiLaunch();

        new HomePage();
    }
}