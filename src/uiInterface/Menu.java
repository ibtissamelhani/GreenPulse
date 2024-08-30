package uiInterface;

import java.util.Scanner;

public class Menu {

    private UserUI userUI;
    private final Scanner scanner = new Scanner(System.in);
    private boolean quit = false;


    public void start(){

        while (true){
            System.out.println("Principal uiInterface.Menu\n");
            System.out.println("1. Account Management");
            System.out.println("2. Show all Accounts");
            System.out.println("3. Carbon Consumption uiInterface.Menu");
            System.out.println("4. Reporting");
            System.out.println("5. Exit");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    AccountManagement();
                    break;
                case "2":
                    userUI.showAllAccounts();
                    break;
                case "5":
                    System.out.println("exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void AccountManagement(){

        while(!quit){
            System.out.println("Account Management uiInterface.Menu");
            System.out.println("1. Create Account");
            System.out.println("2. modify Account");
            System.out.println("3. delete Account");
            System.out.println("4. Return to Main uiInterface.Menu ");
            System.out.println("5. Exit\n");

            System.out.print("enter your choice: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                        userUI.createAccount();
                        break;
                case 2:
                        userUI.modifyAccount();
                        break;
                case 3:
                        userUI.deleteAccount();
                        break;
                case 4:
                        quit = true;
                        System.out.println("return to principal menu");
                        break;
                case 5:
                        System.out.println("exit");
                        System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void ConsumptionMenu(){

        while(!quit){
            System.out.println("Account Management uiInterface.Menu");
            System.out.println("1. Add New Consumption");
            System.out.println("2. ");
            System.out.println("3. Return to Main uiInterface.Menu ");
            System.out.println("4. Exit\n");

            System.out.print("enter your choice: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    userUI.AddNewConsommation();
                    break;
                case 2:

                    break;
                case 3:
                    quit = true;
                    System.out.println("return to principal menu");
                    break;
                case 4:
                    System.out.println("exit");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }


}
