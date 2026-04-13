/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandlogin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class RegistrationAndLoginIT {
    
    public RegistrationAndLoginIT() {
    }

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class RegistrationAndLogin.
     */
    @org.junit.jupiter.api.Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        RegistrationAndLogin.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validUsername method, of class RegistrationAndLogin.
     */
    @org.junit.jupiter.api.Test
    public void testValidUsername() {
        System.out.println("validUsername");
        String username = "";
        boolean expResult = false;
        boolean result = RegistrationAndLogin.validUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validPassword method, of class RegistrationAndLogin.
     */
    @org.junit.jupiter.api.Test
    public void testValidPassword() {
        System.out.println("validPassword");
        String password = "";
        boolean expResult = false;
        boolean result = RegistrationAndLogin.validPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validCellphoneNumber method, of class RegistrationAndLogin.
     */
    @org.junit.jupiter.api.Test
    public void testValidCellphoneNumber() {
        System.out.println("validCellphoneNumber");
        String cellphoneNumber = "";
        boolean expResult = false;
        boolean result = RegistrationAndLogin.validCellphoneNumber(cellphoneNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class RegistrationAndLogin.
     */
    @org.junit.jupiter.api.Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String loginUsername = "";
        String loginPassword = "";
        String registeredUsername = "";
        String registeredPassword = "";
        boolean expResult = false;
        boolean result = RegistrationAndLogin.loginUser(loginUsername, loginPassword, registeredUsername, registeredPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
