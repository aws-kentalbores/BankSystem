package mainpackage;

import java.util.Scanner;

import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

/**
 * Main class to run the Bank Account Management System.
 */
public final class Main {
    private Main() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     * @throws InsufficientFundsException
     * @throws AccountFrozenException
     * @throws InvalidAmountException
     */
    public static void main(final String[] args) throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        BankAccount account;
        while (true) {
            int choice;
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println(
                        "\nWelcome to the Bank Account " + "Management System!"
                                + "\n [1] Use Account \n [2] Create Account "
                                + "\n [3] List Accounts \n [4] Get Account "
                                + "\n [5] Transaction History of Account "
                                + "\n [6] Filter Accouts \n [7] Sort Accounts "
                                + "\n [8] Exit");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input
                continue;
            }
            if (choice == 1) {
                int id;
                try {
                    System.out.println("Enter account ID:");
                    id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); // Consume the invalid input
                    continue;
                }
                if (manager.getBankAccount().containsKey(id)) {
                    account = (SavingsAccount) manager.getBankAccount().get(id);
                    System.out.println("Hello, "
                            + ((SavingsAccount) account).getOwnerName()
                            + "! Your savings account has been accessed");
                    System.out.println(
                            "Your current balance is: " + account.getBalance());

                    while (true) {
                        System.out.println(
                                "" + "Enter a command (deposit, withdraw, "
                                        + "freeze, unfreeze, logout):" + "");
                        String command = sc.nextLine();

                        try {
                            if (command.equalsIgnoreCase("deposit")) {
                                System.out.println("Enter amount to deposit:");
                                double amount = sc.nextDouble();
                                sc.nextLine(); // Consume newline
                                account.deposit(
                                        roundToTwoDecimalPlaces(amount));
                                System.out.println("Current balance: "
                                        + account.getBalance());
                            } else if (command.equalsIgnoreCase("withdraw")) {
                                System.out.println("Enter amount to withdraw:");
                                double amount = sc.nextDouble();
                                sc.nextLine(); // Consume newline
                                account.withdraw(
                                        roundToTwoDecimalPlaces(amount));
                                System.out.println("Current balance: "
                                        + account.getBalance());
                            } else if (command.equalsIgnoreCase("freeze")) {
                                account.freezeAccount();
                                System.out.println(
                                        "Your account has been frozen.");
                            } else if (command.equalsIgnoreCase("unfreeze")) {
                                account.unfreezeAccount();
                                System.out.println(
                                        "Your account has " + "been unfrozen.");
                            } else if (command.equalsIgnoreCase("logout")) {
                                System.out
                                        .println("Logging out of the account.");
                                break;
                            } else {
                                System.out.println("Invalid command. "
                                        + "Please try again.");
                            }
                        } catch (InvalidAmountException | AccountFrozenException
                                | InsufficientFundsException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println(
                            "Account with ID " + id + " does not exist.");
                }

            } else if (choice == 2) {
                System.out.println("Enter your name:");
                String nameInput = sc.nextLine();
                account = new SavingsAccount(nameInput);
                manager.addAccount(account);
            } else if (choice == 3) {
                manager.listAccounts();
            } else if (choice == 4) {
                System.out.println("Enter account ID:");
                int id = sc.nextInt();
                sc.nextLine(); // Consume newline
                manager.getAccount(id);
            } else if (choice == 5) {
                System.out.println("Enter account ID:");
                int id = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (manager.getBankAccount()
                        .get(id) instanceof SavingsAccount) {
                    account = (SavingsAccount) manager.getBankAccount().get(id);
                    System.out.println(
                            "Transaction history for account ID " + id + ":");
                    for (Transaction t : account.getTransactionHistory()) {
                        System.out.println("-----------" + "--------------");
                        System.out.println(t);
                    }
                } else {
                    System.out.println(
                            "Account with ID " + id + " does not exist.");
                }
            } else if (choice == 6) {

                System.out.println("Enter range: ");
                double amount = sc.nextDouble();
                sc.nextLine(); // Consume newline
                System.out.println("Transactions at or above " + amount + ":");

            } else if (choice == 7) {
             // use BankAccountManager sort method heheh not done
                //manager.sortAccountsByBalance();
                    System.out.println("Account ID to sort by balance:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (manager.getBankAccount()
                            .get(id) instanceof SavingsAccount) {
                        account = (SavingsAccount) manager.getBankAccount()
                                .get(id);
                        System.out.println("Accounts sorted by balance:");
                        System.out.println("-----------" + "--------------");
                        System.out.println("Account ID: " + id);
                        System.out.println("Balance: " + account.getBalance());
                    } else {
                        System.out.println(
                                "Account with ID " + id + " does not exist.");
                    }

            } else if (choice == 8) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Utility method to round a double value to two decimal places.
     *
     * @param amount the amount to be rounded
     * @return the rounded amount
     */
    public static double roundToTwoDecimalPlaces(final double amount) {
        final double percent = 100.0;
        return Math.round(amount * percent) / percent;

    }
}
