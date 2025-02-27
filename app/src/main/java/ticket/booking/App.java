package ticket.booking;

import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        }
        catch (IOException ex){
            System.out.println("Something went wrong ! ");
            return;
        }

        while(option!=7){
            System.out.println("Choose option");
            System.out.println("1. Signu up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Tranis");
            System.out.println("5. Book a seat");
            System.out.println("6. Cancel my booking");
            System.out.println("7. Exit my app");
            option = sc.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter your name : ");
                    String nameToSingUp = sc.next();
                    System.out.print("Enter your password : ");
                    String passwordToSingUp = sc.next();
                    User userToSignUp = new User(nameToSingUp,passwordToSingUp, UserServiceUtil.hashPassword(passwordToSingUp),new ArrayList<>(), UUID.randomUUID().toString());
                    break;

                case 2:
                    System.out.println("Enter user name to login : ");
                    String nameToLogin = sc.next();
                    System.out.println("Enter password to login : ");
                    String passwordToLogin = sc.next();
                    User userToLogin = new User(nameToLogin,passwordToLogin,UserServiceUtil.hashPassword(passwordToLogin),new ArrayList<>(),UUID.randomUUID().toString());
                    try{
                         userBookingService = new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        System.out.println("Oops! Something went wrong !");
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching your Bookings : ");
                    userBookingService.fetchBooking();
                    break;

                case 4:
                    System.out.println("Search Trains : ");


            }
        }
    }
}
