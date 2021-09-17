package managment;

import java.util.*;

import model.*;
import repository.*;

public class CardManagment {

    //Variables
    private final util.ScannerExt scannerExt;
    private CardRepository cardRepository;
    private static Card currentCard;

    public static Card getCurrentCard(){
        return currentCard;
    }
    //Constructor
    public CardManagment(util.ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.cardRepository = new CardRepository();
    }

    //Login option
    public void login() {
        boolean successfulLogin = true;
        while (successfulLogin) {
            System.out.print("\t\tInsert Card (CardNo): ");
            String cardNo = this.scannerExt.scanField();

            System.out.print("\t\tInsert pin: ");
            String pin = this.scannerExt.scanField();

            Card card = this.cardRepository.login(cardNo, pin);
            if (Objects.isNull(card)) {
                System.out.println("\t\tIncorrect combination. Try again.");
            } else {
                currentCard = card;

                OptionImplement optionImplement = new OptionImplement(scannerExt);
                optionImplement.showAtmLocation(currentCard);

                successfulLogin = false;
                option();
            }
        }
    }

    //Menu option
    public void option() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\t*************************************************************");
            System.out.println("\t\tChoose an option: ");
            System.out.println("\t\t1.Withdraw");
            System.out.println("\t\t2.Deposit");
            System.out.println("\t\t3.Control balance");
            System.out.println("\t\t4.Change pin");
            System.out.println("\t\t5.Logout!");
            System.out.print("\t\tChoice: ");
            Integer choice = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
            System.out.println("\t*************************************************************");

            logout = choice(logout, choice, scannerExt);
        }
    }

    private static boolean choice(boolean logout, Integer choice, util.ScannerExt scannerExt) {
        OptionImplement optionImplement = new OptionImplement(scannerExt);
        switch (choice) {
            case 1 -> optionImplement.withdraw(currentCard);
            case 2 -> optionImplement.deposit(currentCard);
            case 3 -> optionImplement.controlBalance(currentCard);
            case 4 -> optionImplement.changePin(currentCard);
            case 5 -> logout = true;
            default -> System.out.println("Choose: 1-5");
        }
        return logout;
    }


}
