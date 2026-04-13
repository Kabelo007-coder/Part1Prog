/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandlogin;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class RegistrationAndLogin {



    public static void main(String[] args) {
                Scanner input = new Scanner(System.in);

        //REGISTRATION
        System.out.println("====== REGISTRATION ======");
        
        System.out.println("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.println("Enter last name: ");
        String lastName = input.nextLine();
        
        System.out.println("Enter username: ");
        String registeredUsername = input.nextLine();

        if (validUsername(registeredUsername)) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }
        // Confirming if the passwords match
        String registeredPassword = " ";
        while (true) {
            System.out.println("Create new password: ");
            registeredPassword = input.nextLine();
            System.out.println("Confirm password: ");
            String confirmPassword = input.nextLine();
            if (registeredPassword.equals(confirmPassword)) {
                break; //if password match
            } else {
                System.out.println("Passwords do not match, ensure that they match and try again.");
            }
        

        if (validPassword(registeredPassword)) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted. Ensure it has at least eight characters, a capital letter, a number and a special character.");
        }
        }

        System.out.println("Enter cell phone number (+27xxxxxxxxx): ");
        String cellphoneNumber = input.nextLine();

        if (validCellphoneNumber(cellphoneNumber)) {
            System.out.println("Cellphone number successfully added.");
        } else {
            System.out.println("Cellphone number incorrectly formatted or does not contain international code.");
        }

        if (validUsername(registeredUsername) && validPassword(registeredPassword) && validCellphoneNumber(cellphoneNumber)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Invalid input. Please check your credentials.");
            input.close();
            return; // Stop if registration failed
        }

        //LOGIN
        System.out.println("\n====== LOGIN ======\n");
        System.out.println("Enter username: ");
        String loginUsername = input.nextLine();
        System.out.println("Enter password: ");
        String loginPassword = input.nextLine();

        if (loginUser(loginUsername, loginPassword, registeredUsername, registeredPassword)) {
            System.out.println("Welcome " + firstName + ", " + lastName + " it is great to see you again.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }

        input.close();
    }
    
    // Last step: All methods for the code

    public static boolean validUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

  public static boolean validPassword(String password) {
    // Password must be at least 8 characters long
    if (password.length() < 8) {
        return false;
    }

    // Password must include at least 1 uppercase, 1 digit, 1 special character
    boolean hasUppercase = false;
    boolean hasDigit = false;
    boolean hasSpecial = false;

    // Go through each character in the password
    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);

        if (Character.isUpperCase(c)) {
            hasUppercase = true;
        }
        if (Character.isDigit(c)) {
            hasDigit = true;
        }
        // Special character is not a letter and not a digit
        if (!Character.isLetterOrDigit(c)) {
            hasSpecial = true;
        }
    }

    // All 3 conditions must be true
    return hasUppercase && hasDigit && hasSpecial;
}

public static boolean validCellphoneNumber(String cellphoneNumber) {
    //Cellphone number must start with +27
    if (!cellphoneNumber.startsWith("+27")) {
        return false;
    }

    //Cellphone number must be 12 characters total (+27 + 9 digits = 12)
    if (cellphoneNumber.length() != 12) {
        return false;
    }

    //All 9 characters after +27 must all be digits
    for (int i = 3; i < cellphoneNumber.length(); i++) {
        char c = cellphoneNumber.charAt(i);
        if (!Character.isDigit(c)) {
            return false;
        }
    }

    return true; // if cellphone number matches the requirements
}

    public static boolean loginUser(String loginUsername, String loginPassword, String registeredUsername, String registeredPassword) {
        return loginUsername.equals(registeredUsername) && loginPassword.equals(registeredPassword);
    }
}