import javax.swing.*;
import java.applet.Applet;
import java.awt.print.PrinterJob;
import java.io.IOException;

public class Printer extends Applet {
    public void init() {
        print("http://google.com");
    }

    public void print(String url) {
        JEditorPane pane;
        try {
            pane = new JEditorPane(url);
        } catch (IOException e) {
            return;
        }
        HTMLPrinter target = new HTMLPrinter(pane);
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(target);

        try {

            printJob.printDialog();
            printJob.print();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
