/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.methodchecker;

/**
 *
 * @author Student
 */
import java.util.Scanner;
public class MethodChecker {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // REGISTRATION
        System.out.println("Enter username: ");
        String username = input.nextLine();

        
        if (ValidUsername(username)) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        //Password
        String password;
        while (true) {
            System.out.println("Create new password: ");
            password = input.nextLine();
            System.out.println("Confirm password: ");
            String confirmPassword = input.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match, ensure that they match and try again.");
                continue;
            }

            // Check password
            boolean capitalLetter = false; 
            boolean specialCharacter = false;  
            boolean containsNumber = false; 
            boolean correctLength = false; 

            // Check if length is 8 or more
            if (password.length() >= 8) {
                correctLength = true;
            }

            // Go through every letter in the password to check
            for (int i = 0; i < password.length(); i++) {
                char letter = password.charAt(i); // check one character at a time

                if (Character.isUpperCase(letter)) {
                    capitalLetter = true; // check if a capital letter is found
                }
                
                if (Character.isDigit(letter)) {
                    containsNumber = true; // check if a number is found
                }
                
                if (!Character.isLetterOrDigit(letter)) {
                    specialCharacter = true; // check if specialchars are found
                }
            }

            // Check if conditions are met
            if (correctLength && capitalLetter && specialCharacter && containsNumber) {
                System.out.println("Password successfully captured.");
                break; // break only if the password is valid and matching
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        System.out.println("Enter cell phone number (+27xxxxxxxxx): ");
        String cellphoneNumber = input.nextLine();

        // Check cellphone number 
        boolean validCellphoneNumber = true; 

        // Cellphone number must start with +27
        if (!cellphoneNumber.startsWith("+27")) {
            validCellphoneNumber = false;
        }

        // Cellphone number must be exactly 12 characters long(+27 plus 9 digits)
        if (cellphoneNumber.length() != 12) {
            validCellphoneNumber = false;
        }

        // Check if the last 9 digits are numbers
        for (int i = 3; i < cellphoneNumber.length(); i++) {
            char digit = cellphoneNumber.charAt(i);
            if (!Character.isDigit(digit)) {
                validCellphoneNumber = false; // only if a letter or symbol is found, it's invalid
            }
        }

        if (validCellphoneNumber) {
            System.out.println("Cellphone number successfully added.");
        } else {
            System.out.println("Cellphone number incorrectly formatted or does not contain international code.");
        }

        // Final check using the methods
        if (ValidUsername(username) && ValidPassword(password) && ValidCellphoneNumber(cellphoneNumber)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Invalid input. Please check your credentials.");
        }

        input.close();
    }

    // Last step: All methods for the code
    public static boolean ValidUsername(String username) {
        // Must contain an underscore & must be 5 characters or less
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean ValidPassword(String password) {
        // Check length
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        // All three must be met or must be true
        return hasUpper && hasDigit && hasSpecial;
    }

    public static boolean ValidCellphoneNumber(String cellphoneNumber) {
        if (!cellphoneNumber.startsWith("+27")) {
            return false;
        }
        
        if (cellphoneNumber.length() != 12) {
            return false;
        }
        
        for (int i = 3; i < cellphoneNumber.length(); i++) {
            if (!Character.isDigit(cellphoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
   
    