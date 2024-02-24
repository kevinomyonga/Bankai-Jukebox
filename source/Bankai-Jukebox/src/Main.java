import com.bankai.jukebox.gui.Jukebox;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        FlatMacDarkLaf.setup();

        Jukebox jukebox = new Jukebox();
        jukebox.guiLaunch();
    }
}