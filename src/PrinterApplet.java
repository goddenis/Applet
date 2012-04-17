import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PrinterApplet extends Applet {

    private PrintRequestAttributeSet aset;
    public Doc doc;
    DocPrintJob printerJob;
    private InputStream urlStream;

    public void init() {
        print();
        this.stop();
    }

    void prep() {

        URL url;
        aset = new HashPrintRequestAttributeSet();
        aset.add(MediaSizeName.ISO_A4);
        aset.add(new Copies(1));
        File file = new File("C:\\Users\\goddennis\\Documents\\Java\\Projects\\Applet\\sdd.xps");
        if (!file.exists()) {
            try {
                if (!file.createNewFile())
                    throw new IOException("Не создан");
            } catch (IOException e) {

                System.out.print(e.getStackTrace().toString());
            }
        }
        aset.add(new javax.print.attribute.standard.Destination(file.toURI()));

        try {
            url = new URL("http://googl.com/");
            url.openConnection();
            urlStream = url.openStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintService pservices = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println(pservices.getName());
        doc = new SimpleDoc(urlStream, javax.print.DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        try {
            System.out.println("DOC : \n " + doc.getPrintData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        printerJob = pservices.createPrintJob();
    }

    void print() {
        prep();
        System.out.println("Printer Name : " +
                printerJob.getPrintService());
        try {
            printerJob.print(doc, aset);

        } catch (PrintException e) {
            e.printStackTrace();
        }
        System.out.println("Done Printing.");
    }

}