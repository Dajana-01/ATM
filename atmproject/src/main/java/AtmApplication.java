import managment.CardManagment;
import util.HibernateUtil;
import java.util.Scanner;

public class AtmApplication {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        //init hibernate
        HibernateUtil.getSessionFactory();
        util.ScannerExt scannerExt = new util.ScannerExt(new Scanner(System.in));
        CardManagment cardManagment = new CardManagment(scannerExt);

        System.out.println("\t*************************************************************");
        System.out.println("\t************************ WELCOME ****************************");
        System.out.println("\t*************************************************************");
        cardManagment.login();

        System.exit(0);
    }
}
