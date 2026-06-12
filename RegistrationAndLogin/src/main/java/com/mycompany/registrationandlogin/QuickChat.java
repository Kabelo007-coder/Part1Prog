package com.mycompany.registrationandlogin;

import static com.mycompany.registrationandlogin.RegistrationAndLogin.loginUser;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

        // Welcoming message
        System.out.println("\nWelcome to QuickChat.");

        // Asking how many messages to send
        System.out.print("How many messages do you want to send? ");
        int maxMessages = Integer.parseInt(input.nextLine());

        //Arrays to store message info
        String[] messageID   = new String[maxMessages];
        String[] messageHash = new String[maxMessages];
        String[] recipient   = new String[maxMessages];
        String[] messageText = new String[maxMessages];

        
        //Arrays to store, send and disregard messages
        ArrayList<String> sentMessages        = new ArrayList<>();  // all sent messages
        ArrayList<String> disregardedMessages = new ArrayList<>();  // all disregarded messages
        ArrayList<String> storedMessages      = new ArrayList<>();  // loaded from JSON file
        ArrayList<String> storedRecipients    = new ArrayList<>();  // recipients of stored messages
        ArrayList<String> storedHashes        = new ArrayList<>();  // hashes of stored messages
        ArrayList<String> storedIDs           = new ArrayList<>();  // IDs of stored messages

        // Load any messages already saved in the JSON file
        loadStoredMessages("messages.json", storedMessages, storedRecipients, storedHashes, storedIDs);
        
        //Initializing message count and choice
        int count  = 0;
        int choice = 0;

        while (choice != 4) {   //I changed from 3 to 4 because we added a new menu option

            // Displaying menu
            System.out.println("\nMenu:");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages");
            System.out.println("4. Quit");
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
                                //Saves to the first declared arrays
                                messageID[count]   = id;
                                messageHash[count] = hash;
                                recipient[count]   = rec;
                                messageText[count] = msg;
                                count = count + 1;

                                //Also add to the sentMessages list
                                sentMessages.add("ID:" + id + " | Hash:" + hash + " | To:" + rec + " | Msg:" + msg);

                                System.out.println("Message successfully sent");

                            } else if (sendChoice == 0) {
                                //Also add to disregardedMessages list
                                disregardedMessages.add("ID:" + id + " | Hash:" + hash + " | To:" + rec + " | Msg:" + msg);
                                System.out.println("Message discarded.");

                            } else if (sendChoice == 2) {
                                //Save to JSON file
                                try {
                                    FileWriter fw = new FileWriter("messages.json", true);
                                    fw.write("{\n");
                                    fw.write("  \"id\": \""        + id   + "\",\n");
                                    fw.write("  \"recipient\": \"" + rec  + "\",\n");
                                    fw.write("  \"message\": \""   + msg  + "\",\n");
                                    fw.write("  \"hash\": \""      + hash + "\"\n");
                                    fw.write("}\n");
                                    fw.close();

                                    //Also add to the storedMessages lists
                                    storedMessages.add("ID:" + id + " | Hash:" + hash + " | To:" + rec + " | Msg:" + msg);
                                    storedRecipients.add(rec);
                                    storedHashes.add(hash);
                                    storedIDs.add(id);

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

            //OPTION 3:Stored Messages Menu
            } else if (choice == 3) {

                storedMessagesMenu(input, storedMessages, storedRecipients, storedHashes, storedIDs);

            //Last option: Quit
            } else if (choice == 4) {
                System.out.println("Total messages sent: " + count);
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }


    //STORED MESSAGES MENU
    //Display sub-options a to f
    static void storedMessagesMenu(Scanner input,
                                   ArrayList<String> storedMessages,
                                   ArrayList<String> storedRecipients,
                                   ArrayList<String> storedHashes,
                                   ArrayList<String> storedIDs) {

        boolean back = false;
        //While loop for stored messages
        while (!back) {
            System.out.println("\n--- Stored Messages ---");
            System.out.println("a. Show sender and recipient of all stored messages");
            System.out.println("b. Show the longest stored message");
            System.out.println("c. Search by Message ID");
            System.out.println("d. Search by recipient");
            System.out.println("e. Delete a message using its hash");
            System.out.println("f. Show full report");
            System.out.println("0. Back to main menu");
            System.out.print("Choose: ");
            
            //For the compiler to read the option entered by user
            String pick = input.nextLine().toLowerCase();

            if (pick.equals("a")) {
                showRecipients(storedMessages, storedRecipients);

            } else if (pick.equals("b")) {
                showLongest(storedMessages);

            } else if (pick.equals("c")) {
                searchByID(input, storedMessages, storedRecipients, storedIDs);

            } else if (pick.equals("d")) {
                searchByRecipient(input, storedMessages, storedRecipients);

            } else if (pick.equals("e")) {
                deleteByHash(input, storedMessages, storedRecipients, storedHashes, storedIDs);

            } else if (pick.equals("f")) {
                showReport(storedMessages, storedRecipients, storedHashes, storedIDs);

            } else if (pick.equals("0")) {
                back = true;

            } else {
                System.out.println("Invalid option.");
            }
        }
    }


    //Option a. Show recipients of all stored messages
    static void showRecipients(ArrayList<String> storedMessages,
                               ArrayList<String> storedRecipients) {

        System.out.println("\n--- Recipients of Stored Messages ---");

        if (storedMessages.size() == 0) {
            System.out.println("No stored messages.");
            return;
        }

        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Message " + (i + 1) + " | Recipient: " + storedRecipients.get(i));
        }
    }


    //Option b. Show the longest stored message
    static void showLongest(ArrayList<String> storedMessages) {

        System.out.println("\n--- Longest Stored Message ---");

        if (storedMessages.size() == 0) {
            System.out.println("No stored messages.");
            return;
        }

        //Start by assuming the first one is the longest
        String longest = storedMessages.get(0);

        //Compare each message and if it is longer, replace
        for (int i = 1; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).length() > longest.length()) {
                longest = storedMessages.get(i);
            }
        }

        System.out.println(longest);
    }


    //Option c. Search by Message ID
    static void searchByID(Scanner input,
                           ArrayList<String> storedMessages,
                           ArrayList<String> storedRecipients,
                           ArrayList<String> storedIDs) {

        System.out.print("Enter Message ID to search: ");
        String searchID = input.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < storedIDs.size(); i++) {
            if (storedIDs.get(i).equals(searchID)) {
                System.out.println("Recipient : " + storedRecipients.get(i));
                System.out.println("Message   : " + storedMessages.get(i));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with ID: " + searchID);
        }
    }


    //Option d. Search by recipient
    static void searchByRecipient(Scanner input,
                                  ArrayList<String> storedMessages,
                                  ArrayList<String> storedRecipients) {

        System.out.print("Enter recipient number to search: ");
        String name = input.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(name)) {
                System.out.println(storedMessages.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for: " + name);
        }
    }


    //Option e. Delete by hash
    static void deleteByHash(Scanner input,
                             ArrayList<String> storedMessages,
                             ArrayList<String> storedRecipients,
                             ArrayList<String> storedHashes,
                             ArrayList<String> storedIDs) {

        System.out.print("Enter the message hash to delete: ");
        String hash = input.nextLine().trim();

        //Find the position of this hash in the list
        int position = -1;  //-1 means it is not found
        for (int i = 0; i < storedHashes.size(); i++) {
            if (storedHashes.get(i).equals(hash)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            System.out.println("No message found with that hash.");
            return;
        }

        //Remember the message before deleting it for the confirmation message
        String deletedMsg = storedMessages.get(position);

        //Remove from all lists at the same position
        storedMessages.remove(position);
        storedRecipients.remove(position);
        storedHashes.remove(position);
        storedIDs.remove(position);

        //Rewrite the JSON file without the deleted message
        rewriteFile("messages.json", storedMessages, storedRecipients, storedHashes, storedIDs);

        System.out.println("Message \"" + deletedMsg + "\" successfully deleted.");
    }


    //Option f. Show full report
    static void showReport(ArrayList<String> storedMessages,
                           ArrayList<String> storedRecipients,
                           ArrayList<String> storedHashes,
                           ArrayList<String> storedIDs) {

        System.out.println("\n===== STORED MESSAGES REPORT =====");

        if (storedMessages.size() == 0) {
            System.out.println("No stored messages.");
            return;
        }

        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("\nMessage " + (i + 1));
            System.out.println("ID        : " + storedIDs.get(i));
            System.out.println("Recipient : " + storedRecipients.get(i));
            System.out.println("Hash      : " + storedHashes.get(i));
            System.out.println("Full      : " + storedMessages.get(i));
        }

        System.out.println("\n==================================");
    }


    //Load stored messages from the JSON file when the app starts
    static void loadStoredMessages(String fileName,
                                   ArrayList<String> storedMessages,
                                   ArrayList<String> storedRecipients,
                                   ArrayList<String> storedHashes,
                                   ArrayList<String> storedIDs) {

        java.io.File file = new java.io.File(fileName);
        if (!file.exists()) {
            return;  //if there is no file yet, nothing to load
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //Temporary variables to hold each piece while reading line by line
            String id = "", rec = "", msg = "", hash = "";
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("\"id\""))        id   = getValue(line);
                if (line.startsWith("\"recipient\"")) rec  = getValue(line);
                if (line.startsWith("\"message\""))   msg  = getValue(line);
                if (line.startsWith("\"hash\""))      hash = getValue(line);

                //When we hit the closing brace we have a full message
                if (line.equals("}")) {
                    if (!id.equals("")) {
                        storedMessages.add("ID:" + id + " | Hash:" + hash + " | To:" + rec + " | Msg:" + msg);
                        storedRecipients.add(rec);
                        storedHashes.add(hash);
                        storedIDs.add(id);
                        //Reset for next message
                        id = rec = msg = hash = "";
                    }
                }
            }

            reader.close();

            if (storedMessages.size() > 0) {
                System.out.println("Loaded " + storedMessages.size() + " stored message(s) from file.");
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }


    //REWRITE the JSON file after a deletion
    static void rewriteFile(String fileName,
                            ArrayList<String> storedMessages,
                            ArrayList<String> storedRecipients,
                            ArrayList<String> storedHashes,
                            ArrayList<String> storedIDs) {
        try {
            //false = overwrite the file from scratch
            FileWriter fw = new FileWriter(fileName, false);

            for (int i = 0; i < storedMessages.size(); i++) {
                //Extract just the message text from the full string
                String fullMsg = storedMessages.get(i);
                String msgBody = fullMsg.substring(fullMsg.indexOf("| Msg:") + 6);
                
                //Displaying stored information
                fw.write("{\n");
                fw.write("  \"id\": \""        + storedIDs.get(i)        + "\",\n");
                fw.write("  \"recipient\": \"" + storedRecipients.get(i) + "\",\n");
                fw.write("  \"message\": \""   + msgBody                 + "\",\n");
                fw.write("  \"hash\": \""      + storedHashes.get(i)     + "\"\n");
                fw.write("}\n");
            }

            fw.close();
            
            //Message for files that are not updated or added
        } catch (IOException e) {
            System.out.println("Could not update file: " + e.getMessage());
        }
    }


    //Pull the value out of a JSON line
    static String getValue(String line) {
        int start = line.indexOf(": \"") + 3;  // position after the opening quote
        int end   = line.lastIndexOf("\"");    // position of the closing quote
        if (start < 3 || end <= start) return "";
        return line.substring(start, end);
    }
}
