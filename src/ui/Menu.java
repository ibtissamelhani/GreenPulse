package ui;

import java.util.Scanner;

public class Menu {

    private AccountUI accountUI = new AccountUI();
    private ConsumptionUI consumptionUI = new ConsumptionUI();
    private final Scanner scanner = new Scanner(System.in);
    private boolean quit = false;


    public void start(){

        while (true){
            quit = false;
            System.out.println("Principal Menu\n");
            System.out.println("1. Account Management");
            System.out.println("2. Show all Accounts");
            System.out.println("3. Carbon Consumption Menu");
            System.out.println("4. Exit");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    AccountManagement();
                    break;
                case "2":
                    accountUI.showAllAccounts();
                    break;
                case "3":
                    ConsumptionMenu();
                    break;
                case "4":
                    System.out.println("exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void AccountManagement(){

        while(!quit){
            System.out.println("Account Management Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Modify Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Return to Main Menu ");
            System.out.println("6. Exit\n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                        accountUI.createAccount();
                        break;
                case "2":
                        accountUI.modifyAccount();
                        break;
                case "3":
                        accountUI.deleteAccount();
                        break;
                case "4":
                    accountUI.showAllAccounts();
                    break;
                case "5":
                        quit = true;
                        System.out.println("return to principal menu");
                        break;
                case "6":
                        System.out.println("exit");
                        System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void ConsumptionMenu(){

        while(!quit){
            System.out.println("Consumption Management Menu");
            System.out.println("1. Add New Consumption");
            System.out.println("2. Show total consumption of user");
            System.out.println("3. Show Daily consumption of user");
            System.out.println("4. Show Weekly consumption of user");
            System.out.println("5. Show Monthly consumption of user");
            System.out.println("6. Return to Main Menu ");
            System.out.println("7. Exit \n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    consumptionUI.AddNewConsumption();
                    break;
                case "2":
                    accountUI.ShowTotalConsumption();
                    break;
                case "3":
                    consumptionUI.dailyConsumption();
                    break;
                case "6":
                    quit = true;
                    System.out.println("return to principal menu");
                    break;
                case "7":
                    System.out.println("exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


}
