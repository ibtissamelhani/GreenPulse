
import database.DBConfiguration;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";

        System.out.println(GREEN+"\t\t\t  ________                           __________      .__                 ");
        System.out.println("\t\t\t /  _____/______   ____   ____   ____\\______   \\__ __|  |   ______ ____  ");
        System.out.println("\t\t\t/   \\  __\\_  __ \\_/ __ \\_/ __ \\ /    \\|     ___/  |  \\  |  /  ___// __ \\ ");
        System.out.println("\t\t\t\\    \\_\\  \\  | \\/\\  ___/\\  ___/|   |  \\    |   |  |  /  |__\\___ \\\\  ___/ ");
        System.out.println("\t\t\t \\______  /__|    \\___  >\\___  >___|  /____|   |____/|____/____  >\\___  >");
        System.out.println("\t\t\t        \\/            \\/     \\/     \\/                         \\/     \\/ " );

        System.out.println(" ");
        System.out.println("                          Hello and welcome to GreenPulse!           "+RESET);

        DBConfiguration dbConfig = DBConfiguration.getInstance();
//        DBConfiguration dbConfig1 = DBConfiguration.getInstance();




//        Menu menu = new Menu();
//        menu.start();

    }
}