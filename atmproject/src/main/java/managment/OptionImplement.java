package managment;

import model.*;
import repository.*;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.exit;

public class OptionImplement {

    private final ScannerExt scannerExt;

    public OptionImplement(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    //Main option
    public void withdraw(Card card) {
        Bank bank = BankRepository.getBank(card.getBank().getId());
        List<Account> accounts = AccountRepository.getAccounts(bank.getId());

        System.out.println("\t*************************************************************");
        System.out.println("\t\tFrom which account u wanna withdraw?");
        int lastIndex = 0;
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + accounts.get(i).getAccountNo() + " - " + accounts.get(i).getType());
            lastIndex = i + 1;
        }
        System.out.println("\t\t" + (lastIndex + 1) + ". Go at Main menu");

        System.out.print("\t\tChoise: ");
        Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
        System.out.println("\t*************************************************************");
        switch (choise) {
            case 1:
                withdrawAllEuro(accounts.get(0));
                break;
            case 2:
                withdrawAllEuro(accounts.get(1));
                break;
            case 3:
                break;
        }
    }

    private void withdrawAllEuro(Account account) {
        System.out.println("\t*************************************************************");
        System.out.print("\t\tEnter amount: ");
        Integer withdraw = this.scannerExt.scanNumberField();

        if (withdraw > account.getBalance()) {
            System.out.println("\t\tNot enough balance.");
        } else {
            Integer left = account.getBalance() - withdraw;
            account.setBalance(left);
            AccountRepository.update(account);
            recordTransaction(account, withdraw, false);

            System.out.println("\n\n==============================================");
            System.out.println("Wanna get the receipt?");
            getReceipt(account);
            System.out.println("\n\tTake your card and wait for the money.");
            exit(0);
        }
    }

    //Main option
    public void deposit(Card card) {
        System.out.println("\t*************************************************************");
        Bank bank = BankRepository.getBank(card.getBank().getId());
        List<Account> accounts = AccountRepository.getAccounts(bank.getId());

        System.out.println("\t\tFrom which account u wanna deposit?");
        int lastIndex = 0;
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + accounts.get(i).getAccountNo() + " - " + accounts.get(i).getType());
            lastIndex = i + 1;
        }
        System.out.println("\t\t" + (lastIndex + 1) + ". Go at Main menu");

        System.out.print("\t\tChoise: ");
        Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
        System.out.println("\t*************************************************************");
        switch (choise) {
            case 1:
                depositAllEuro(accounts.get(0));
                break;
            case 2:
                depositAllEuro(accounts.get(1));
                break;
            case 3:
                break;
        }
    }

    private void depositAllEuro(Account account) {
        System.out.println("\t*************************************************************");
        System.out.print("\t\tEnter amount: ");
        Integer deposit = this.scannerExt.scanNumberField();
        account.setBalance(account.getBalance() + deposit);

        System.out.println("\t\tCounting the money.");
        System.out.println("\t\tDeposit increased with " + deposit);
        AccountRepository.update(account);

        recordTransaction(account, deposit, true);

        System.out.println("\n\n==============================================");
        System.out.println("Wanna get the receipt?");
        getReceipt(account);
        System.out.println("\n\tDon't forget your card.");
        exit(0);
    }


    //Main option
    public void controlBalance(Card card) {
        Bank bank = BankRepository.getBank(card.getBank().getId());
        List<Account> accounts = AccountRepository.getAccounts(bank.getId());

        System.out.println("\t*************************************************************");
        System.out.println("\t\tShowing accounts balance");
        System.out.println("\t\tLeke: " + accounts.get(0).getBalance());
        System.out.println("\t\tEuro: " + accounts.get(1).getBalance());
        System.out.println("\t\tWanna do another transaction:");
        System.out.println("\t\t1- Yes");
        System.out.println("\t\t2- No");
        System.out.print("\t\tChoice:");

        Integer choice = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\t*************************************************************");

        switch (choice) {
            case 1:
                break;
            case 2:
                System.out.println("\t\tThank you! Take your card.");
                exit(0);
        }
    }


    //Main option
    public void changePin(Card card) {
        System.out.println("\t*************************************************************");
        System.out.print("\t\tInsert old pin to continue: ");
        String oldPin = scannerExt.scanField();

        if (oldPin.equals(card.getPin())) {
            newPin(card);
        } else {
            System.out.println("\t\tPins doesn't match.");
            changePin(card);
        }
    }

    private void newPin(Card card) {
        System.out.print("\t\tInsert new pin! :");
        String newPin = scannerExt.scanField();
        System.out.print("\t\tInsert new pin again! :");
        String newPinVerification = scannerExt.scanField();

        if (newPin.equals(newPinVerification)) {
            if (newPin.equals(card.getPin())) {
                System.out.println("\t\tInsert e pin different to the old one.");
                newPin(card);
            } else {
                card.setPin(newPin);
                CardRepository.update(card);
                exit(0);
            }
        } else {
            System.out.println("\t\tThe inserted pins doesn't match");
            System.out.println("\t\tTry again!");
            newPin(card);
        }
    }


    private void getReceipt(Account account) {
        System.out.println("\t\tChoose an option: ");
        System.out.println("\t\t1.Yes");
        System.out.println("\t\t2.No");
        System.out.print("\t\tChoise: ");
        Integer choice = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2));
        if (choice == 1) {
            receipt(account);
        }
    }

    public void receipt(Account account) {
        Transaction transaction = TransactionRepository.getTransaction(account.getId());

        System.out.println("\t*********************************");
        System.out.println("\t               Bill            ");
        showAtmLocation(CardManagment.getCurrentCard());
        System.out.println("\tTranssaction Number: " + transaction.getTransactionNo());
        System.out.println("\tTransaction Time: " + transaction.getTime());
        System.out.println("\tFrom account: " + transaction.getStatus());
        System.out.println("\tTransaction amount: " + transaction.getAmount());
        System.out.println("\tRemaining balance: " + account.getBalance());
        System.out.println("\t*********************************");

    }


    private void recordTransaction(Account account, Integer amount, boolean transactionType) {
        Transaction transaction = new Transaction();
        Random random = new Random();
        transaction.setTransactionNo(random.nextInt(999999999));
        transaction.setAccount(account);
        transaction.setTime(LocalDateTime.now());

        if (!transactionType) {
            transaction.setStatus("Withdrawing");
            transaction.setAmount(-amount);
        } else {
            transaction.setStatus("Depositing");
            transaction.setAmount(+amount);
        }

        TransactionRepository.save(transaction);

    }


    public void showAtmLocation(Card currentCard) {
        User currentUser = UserRepository.findUser(currentCard.getUser().getId());
        Bank bank = BankRepository.getBank(currentCard.getBank().getId());
        List<Atm> atms = AtmRepository.getAtm(bank.getId());

        Random random = new Random();
        System.out.println("\t*************************************************************");
        System.out.println("\t*\t\t\t\t\t\t  " + bank.getName() + "                     *" +
                "\n\t\t\t\t\t Atm Location: " + atms.get(random.nextInt(atms.size())).getLocation());

        System.out.println("\t\t\t\t\t  Welcome : " + currentUser.getFirstName() + " " + currentUser.getLastName());
        System.out.println("\t*************************************************************");
    }
}
