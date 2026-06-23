package mainpackage;

import java.util.Scanner;

import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

/**
 * Main class to run the Bank Account Management System.
 */
public final class Main {

    /** Menu choice: use an existing account. */
    private static final int CHOICE_USE_ACCOUNT = 1;
    /** Menu choice: create a new account. */
    private static final int CHOICE_CREATE_ACCOUNT = 2;
    /** Menu choice: list all accounts. */
    private static final int CHOICE_LIST_ACCOUNTS = 3;
    /** Menu choice: get a single account. */
    private static final int CHOICE_GET_ACCOUNT = 4;
    /** Menu choice: view transaction history. */
    private static final int CHOICE_TRANSACTION_HISTORY = 5;
    /** Menu choice: filter transactions. */
    private static final int CHOICE_FILTER_ACCOUNTS = 6;
    /** Menu choice: sort transactions. */
    private static final int CHOICE_SORT_ACCOUNTS = 7;
    /** Menu choice: exit the application. */
    private static final int CHOICE_EXIT = 8;
    /** Number of decimal places used when rounding amounts. */
    private static final double ROUND_PERCENT = 100.0;

    private Main() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     * @throws InsufficientFundsException if funds are insufficient
     * @throws AccountFrozenException if the account is frozen
     * @throws InvalidAmountException if the amount is invalid
     */
    public static void main(final String[] args)
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        BankAccountManager manager = new BankAccountManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readChoice(sc);
            if (choice == -1) {
                continue;
            }

            switch (choice) {
                case CHOICE_USE_ACCOUNT:
                    useAccount(sc, manager);
                    break;
                case CHOICE_CREATE_ACCOUNT:
                    createAccount(sc, manager);
                    break;
                case CHOICE_LIST_ACCOUNTS:
                    manager.listAccounts();
                    break;
                case CHOICE_GET_ACCOUNT:
                    getAccount(sc, manager);
                    break;
                case CHOICE_TRANSACTION_HISTORY:
                    showTransactionHistory(sc, manager);
                    break;
                case CHOICE_FILTER_ACCOUNTS:
                    filterAccounts(sc, manager);
                    break;
                case CHOICE_SORT_ACCOUNTS:
                    sortAccounts(sc, manager);
                    break;
                case CHOICE_EXIT:
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println(
                "\nWelcome to the Bank Account Management System!"
                        + "\n [1] Use Account \n [2] Create Account "
                        + "\n [3] List Accounts \n [4] Get Account "
                        + "\n [5] Transaction History of Account "
                        + "\n [6] Filter Accouts \n [7] Sort Accounts "
                        + "\n [8] Exit");
    }

    /**
     * Reads the user's menu choice, handling invalid input.
     *
     * @param sc the scanner to read from
     * @return the chosen option, or -1 if the input was invalid
     */
    private static int readChoice(final Scanner sc) {
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            return choice;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
            return -1;
        }
    }

    /**
     * Reads an account ID from the user, handling invalid input.
     *
     * @param sc the scanner to read from
     * @return the account ID entered, or null if the input was invalid
     */
    private static Integer readAccountId(final Scanner sc) {
        System.out.println("Enter account ID:");
        try {
            int id = sc.nextInt();
            sc.nextLine();
            return id;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
            return null;
        }
    }

    /**
     * Logs into an account and lets the user run account commands.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     * @throws InvalidAmountException if the amount is invalid
     * @throws AccountFrozenException if the account is frozen
     * @throws InsufficientFundsException if funds are insufficient
     */
    private static void useAccount(final Scanner sc,
            final BankAccountManager manager) throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        Integer id = readAccountId(sc);
        if (id == null) {
            return;
        }

        if (!manager.getBankAccount().containsKey(id)) {
            System.out.println("Account with ID " + id + " does not exist.");
            return;
        }

        SavingsAccount account =
                (SavingsAccount) manager.getBankAccount().get(id);
        System.out.println("Hello, " + account.getOwnerName()
                + "! Your savings account has been accessed");
        System.out.println("Your current balance is: "
                + account.getBalance());

        runAccountSession(sc, account);
    }

    /**
     * Runs the interactive command loop for a logged in account.
     *
     * @param sc the scanner to read from
     * @param account the account being operated on
     * @throws InvalidAmountException if the amount is invalid
     * @throws AccountFrozenException if the account is frozen
     * @throws InsufficientFundsException if funds are insufficient
     */
    private static void runAccountSession(final Scanner sc,
            final SavingsAccount account) throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        while (true) {
            System.out.println("Enter a command (deposit, withdraw, "
                    + "freeze, unfreeze, logout):");
            String command = sc.nextLine();

            try {
                if (handleAccountCommand(sc, account, command)) {
                    break;
                }
            } catch (InvalidAmountException | AccountFrozenException
                    | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Handles a single account command.
     *
     * @param sc the scanner to read from
     * @param account the account being operated on
     * @param command the command entered by the user
     * @return true if the session should end, false otherwise
     * @throws InvalidAmountException if the amount is invalid
     * @throws AccountFrozenException if the account is frozen
     * @throws InsufficientFundsException if funds are insufficient
     */
    private static boolean handleAccountCommand(final Scanner sc,
            final SavingsAccount account, final String command)
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        if (command.equalsIgnoreCase("deposit")) {
            System.out.println("Enter amount to deposit:");
            Double amount = readDouble(sc);
            if (amount == null) {
                return false;
            }
            account.deposit(roundToTwoDecimalPlaces(amount));
            System.out.println("Current balance: " + account.getBalance());
        } else if (command.equalsIgnoreCase("withdraw")) {
            System.out.println("Enter amount to withdraw:");
            Double amount = readDouble(sc);
            if (amount == null) {
                return false;
            }
            account.withdraw(roundToTwoDecimalPlaces(amount));
            System.out.println("Current balance: " + account.getBalance());
        } else if (command.equalsIgnoreCase("freeze")) {
            account.freezeAccount();
            System.out.println("Your account has been frozen.");
        } else if (command.equalsIgnoreCase("unfreeze")) {
            account.unfreezeAccount();
            System.out.println("Your account has been unfrozen.");
        } else if (command.equalsIgnoreCase("logout")) {
            System.out.println("Logging out of the account.");
            return true;
        } else {
            System.out.println("Invalid command. Please try again.");
        }
        return false;
    }

    /**
     * Reads a double value from the user, handling invalid input.
     *
     * @param sc the scanner to read from
     * @return the value entered, or null if the input was invalid
     */
    private static Double readDouble(final Scanner sc) {
        try {
            double value = sc.nextDouble();
            sc.nextLine();
            return value;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
            return null;
        }
    }

    /**
     * Creates a new account from user input.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     */
    private static void createAccount(final Scanner sc,
            final BankAccountManager manager) {
        System.out.println("Enter your name:");
        String nameInput = sc.nextLine();
        BankAccount account = new SavingsAccount(nameInput);
        manager.addAccount(account);
    }

    /**
     * Prints details for a single account.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     */
    private static void getAccount(final Scanner sc,
            final BankAccountManager manager) {
        Integer id = readAccountId(sc);
        if (id == null) {
            return;
        }
        manager.getAccount(id);
    }

    /**
     * Prints the transaction history for a single account.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     */
    private static void showTransactionHistory(final Scanner sc,
            final BankAccountManager manager) {
        Integer id = readAccountId(sc);
        if (id == null) {
            return;
        }
        BankAccount account = getSavingsAccountOrNull(manager, id);
        if (account == null) {
            System.out.println("Account with ID " + id + " does not exist.");
            return;
        }

        System.out.println("Transaction history for account ID " + id + ":");
        for (Transaction t : account.getTransactionHistory()) {
            System.out.println("-------------------------");
            System.out.println(t);
        }
    }

    /**
     * Filters an account's transactions at or above an amount.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     */
    private static void filterAccounts(final Scanner sc,
            final BankAccountManager manager) {
        Integer id = readAccountId(sc);
        if (id == null) {
            return;
        }
        BankAccount account = getSavingsAccountOrNull(manager, id);
        if (account == null) {
            System.out.println("Account with ID " + id + " does not exist.");
            return;
        }

        System.out.println("Enter range: ");
        Double amount = readDouble(sc);
        if (amount == null) {
            return;
        }
        manager.filterTransactionsAtOrAbove(amount,
                account.getTransactionHistory());
    }

    /**
     * Sorts an account's transactions by amount.
     *
     * @param sc the scanner to read from
     * @param manager the account manager
     */
    private static void sortAccounts(final Scanner sc,
            final BankAccountManager manager) {
        Integer id = readAccountId(sc);
        if (id == null) {
            return;
        }
        BankAccount account = getSavingsAccountOrNull(manager, id);
        if (account == null) {
            System.out.println("Account with ID " + id + " does not exist.");
            return;
        }

        manager.sortTransactionsByAmount(account.getTransactionHistory());
    }

    /**
     * Retrieves an account by ID if it is a savings account.
     *
     * @param manager the account manager
     * @param id the account ID
     * @return the savings account, or null if not found
     */
    private static BankAccount getSavingsAccountOrNull(
            final BankAccountManager manager, final int id) {
        Object account = manager.getBankAccount().get(id);
        if (account instanceof SavingsAccount) {
            return (SavingsAccount) account;
        }
        return null;
    }

    /**
     * Utility method to round a double value to two decimal places.
     *
     * @param amount the amount to be rounded
     * @return the rounded amount
     */
    public static double roundToTwoDecimalPlaces(final double amount) {
        return Math.round(amount * ROUND_PERCENT) / ROUND_PERCENT;
    }
}
