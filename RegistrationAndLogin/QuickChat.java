package com.mycompany.registrationandlogin;

import static com.mycompany.registrationandlogin.RegistrationAndLogin.loginUser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kabelo Mogale
 */
public class QuickChat {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //Welcoming message
        System.out.println("\nWelcome to QuickChat.");

        //Asking how many messages to send
        System.out.print("How many messages do you want to send? ");
        int maxMessages = Integer.parseInt(input.nextLine());

        //Arrays to store message info
        String[] messageID   = new String[maxMessages];
        String[] messageHash = new String[maxMessages];
        String[] recipient   = new String[maxMessages];
        String[] messageText = new String[maxMessages];
        
        int count  = 0;
        int choice = 0;

        while (choice != 3) {

            //Displaying menu
            System.out.println("\nMenu:");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(input.nextLine());

            //If statements for returning messages and recipient number
            if (choice == 1) {
                //Message limit and the return message
                if (count >= maxMessages) {
                    System.out.println("You have reached your message limit.");

                } else {

                    //Prompting the user to enter the recipient number
                    System.out.print("Enter recipient cell number (+27xxxxxxxxx): ");
                    String rec = input.nextLine();
                    //Message for incorrect number
                    if (!rec.startsWith("+") || rec.length() > 12) {
                        System.out.println("Invalid cell number. Must start with + and be max 12 characters.");

                    } else {

                        //Prompting the user to enter the message they want to send
                        System.out.print("Enter message (max 250 characters): ");
                        String msg = input.nextLine();
                        
                        //This is the message length limit
                        if (msg.length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");

                        } else {

                            //Creating a random message ID
                            Random r = new Random();
                            long num = Math.abs(r.nextLong() % 9000000000L) + 1000000000L;
                            String id = String.valueOf(num);

                            
                            String[] words = msg.trim().split(" ");
                            String first = words[0];
                            String last  = words.length > 1 ? words[words.length - 1] : "";
                            String hash  = (id.substring(0, 2) + ":" + count + ":" + first + last).toUpperCase();

                            //Showing message details
                            System.out.println("\nMessage ID   : " + id);
                            System.out.println("Message Hash : " + hash);
                            System.out.println("Recipient    : " + rec);
                            System.out.println("Message      : " + msg);

                            //Second menu
                            System.out.println("\n0. Disregard Message");
                            System.out.println("1. Send Message");
                            System.out.println("2. Store Message");
                            System.out.print("Choice: ");
                            int sendChoice = Integer.parseInt(input.nextLine());
                            
                            
                            if (sendChoice == 1) {
                                messageID[count]   = id;
                                messageHash[count] = hash;
                                recipient[count]   = rec;
                                messageText[count] = msg;
                                count = count + 1;
                                System.out.println("Message successfully sent");

                            } else if (sendChoice == 0) {
                                System.out.println("Message discarded.");

                            } else if (sendChoice == 2) {
                                
                                //Json file
                                try {
                                    FileWriter fw = new FileWriter("messages.json", true);
                                    fw.write("{ \"id\": \"" + id + "\", \"recipient\": \"" + rec + "\", \"message\": \"" + msg + "\" }\n");
                                    fw.close();
                                    System.out.println("Message successfully stored.");
                                } catch (IOException e) {
                                    System.out.println("Could not save message.");
                                }

                            } else {
                                System.out.println("Invalid option. Message discarded.");
                            }
                        }
                    }
                }

            //Option two and its return messages
            } else if (choice == 2) {

                if (count == 0) {
                    System.out.println("No messages sent yet.");
                } else {
                    System.out.println("\n--- Sent Messages ---");
                    for (int i = 0; i < count; i++) {
                        System.out.println("Message ID   : " + messageID[i]);
                        System.out.println("Message Hash : " + messageHash[i]);
                        System.out.println("Recipient    : " + recipient[i]);
                        System.out.println("Message      : " + messageText[i]);
                        System.out.println("---------------------");
                    }
                }

            //Last option
            } else if (choice == 3) {
                System.out.println("Total messages sent: " + count);
                System.out.println("Goodbye " + "!");

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
