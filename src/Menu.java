import service.UserService;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean quit = false;
    private UserService userService = new UserService();


    public void start(){

        while (true){
            System.out.println("Principal Menu\n");
            System.out.println("1. Account Management");
            System.out.println("2.Exit");

            System.out.print("enter your choice: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    AccountManagement();
                    break;
                case 2:
                    System.out.println("exit");
                    quit = true;
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
            System.out.println("2.modify Account");
            System.out.println("3.delete Account");
            System.out.println("4.Return to Main Menu \n");

            System.out.print("enter your choice: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("ceate account");
                    break;
                case 2:
                    System.out.println("modify account");
                    break;
                case 3:
                        System.out.println("delete account");
                        break;
                case 4:
                    quit = true;
                    System.out.println("return to principal menu");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }



}
