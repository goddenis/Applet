import java.applet.Applet;

public class PrintApplet extends Applet {
    @Override
    public synchronized void init() {

        System.out.println("Start");
    }
}
