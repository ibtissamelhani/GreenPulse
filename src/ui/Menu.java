package ui;

import database.DBConfiguration;
import repository.UserRepository;
import service.ConsumptionService;
import service.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {

    private final Connection connection = DBConfiguration.getConnection() ;
    private final UserRepository userRepository = new UserRepository(connection);
    private final UserService userService = new UserService(userRepository);
    private final ConsumptionService consumptionService = new ConsumptionService(userService);
    private final AccountUI accountUI = new AccountUI(userService);
    private final ConsumptionUI consumptionUI = new ConsumptionUI(consumptionService);

    private final Scanner scanner = new Scanner(System.in);
    private boolean quit = false;

    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String RESET = "\u001B[0m";


    public void start(){

        while (true){
            quit = false;
            System.out.println("\n********************************************************************************");
            System.out.println("*                                  Principal Menu                               *");
            System.out.println("********************************************************************************");
            System.out.println("*  1. Account Management                                                        *");
            System.out.println("*  2. Show all users                                                         *");
            System.out.println("*  3. Carbon Consumption Menu                                                   *");
            System.out.println("*  4. Exit                                                                      *");
            System.out.println("********************************************************************************\n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    AccountManagement();
                    break;
                case "2":
                    accountUI.showAllUsersWithConsumption();
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
            System.out.println(BLUE +"\n\n********************************************************************************");
            System.out.println("*                               Account Management Menu                         *");
            System.out.println("********************************************************************************");
            System.out.println("*       1. Create Account                                                       *");
            System.out.println("*       2. Modify Account                                                       *");
            System.out.println("*       3. Delete Account                                                       *");
            System.out.println("*       4. Show All Accounts                                                    *");
            System.out.println("*       5. Return to Main Menu                                                  *");
            System.out.println("*       6. Exit                                                                 *");
            System.out.println("********************************************************************************"+RESET);

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
            System.out.println(YELLOW+"\n\n********************************************************************************");
            System.out.println("*                               Consumption Management Menu                      *");
            System.out.println("*********************************************************************************");
            System.out.println("*       1. Add New Consumption                                                   *");
            System.out.println("*       2. Show total consumption of user                                        *");
            System.out.println("*       3. Show Daily consumption                                                *");
            System.out.println("*       4. Show Weekly consumption                                               *");
            System.out.println("*       5. Show Monthly consumption                                              *");
            System.out.println("*       6. Return to Main Menu                                                   *");
            System.out.println("*       7. Exit                                                                  *");
            System.out.println("********************************************************************************"+RESET);

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            switch(choice){
//                case "1":
//                    consumptionUI.AddNewConsumption();
//                    break;
//                case "2":
//                    consumptionUI.ShowTotalConsumption();
//                    break;
//                case "3":
//                    consumptionUI.dailyConsumption();
//                    break;
//                case "4":
//                    consumptionUI.weeklyConsumption();
//                    break;
//                case "5":
//                    consumptionUI.monthlyConsumption();
//                    break;
                case "6":
                    quit = true;
                    System.out.println("return to principal menu");
                    break;
                case "7":
                    System.out.println("exit...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


}
