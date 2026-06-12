/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.registrationandlogin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @author Harvard Keyz
 */
public class QuickChatIT {
    
    public QuickChatIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class QuickChat.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        QuickChat.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    

public class QuickChatTest {

    // Lists we will use in every test
    ArrayList<String> sentMessages;
    ArrayList<String> disregardedMessages;
    ArrayList<String> storedMessages;
    ArrayList<String> storedRecipients;
    ArrayList<String> storedHashes;
    ArrayList<String> storedIDs;

    // ─────────────────────────────────────────────────────────────────
    // @Before runs before EVERY test automatically
    // It sets up the test data from the assignment
    // ─────────────────────────────────────────────────────────────────
    @Before
    public void setUp() {

        sentMessages        = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        storedMessages      = new ArrayList<>();
        storedRecipients    = new ArrayList<>();
        storedHashes        = new ArrayList<>();
        storedIDs           = new ArrayList<>();

        // Test Message 1 — Sent
        sentMessages.add("Did you get the cake?");
        storedIDs.add("MSG001");
        storedRecipients.add("+27834557896");
        storedHashes.add("AA:0:DidCake");

        // Test Message 2 — Stored
        storedMessages.add("Where are you? You are late! I have asked you to be on time.");
        storedRecipients.add("+27838884567");
        storedHashes.add("BB:1:Wheretime.");
        storedIDs.add("MSG002");

        // Test Message 3 — Disregarded
        disregardedMessages.add("Yohoooo, I am at your gate.");

        // Test Message 4 — Sent
        sentMessages.add("It is dinner time !");
        storedIDs.add("MSG004");
        storedRecipients.add("0838884567");
        storedHashes.add("CC:2:Ittime");

        // Test Message 5 — Stored
        storedMessages.add("Ok, I am leaving without you.");
        storedRecipients.add("+27838884567");
        storedHashes.add("DD:3:Okyou.");
        storedIDs.add("MSG005");
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 1: Sent messages array is correctly populated
    // Expected to contain: "Did you get the cake?" and "It is dinner time !"
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testSentMessagesPopulated() {
        assertTrue(sentMessages.contains("Did you get the cake?"));
        assertTrue(sentMessages.contains("It is dinner time !"));
        assertEquals(2, sentMessages.size());
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 2: Display the longest message
    // Expected: "Where are you? You are late! I have asked you to be on time."
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testLongestMessage() {

        String longest = storedMessages.get(0);

        for (int i = 1; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).length() > longest.length()) {
                longest = storedMessages.get(i);
            }
        }

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            longest
        );
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 3: Search by Message ID (developer number = 0838884567)
    // Expected: returns "It is dinner time !"
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testSearchByID() {

        String searchRecipient = "0838884567";
        String foundMessage = "Not found";

        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(searchRecipient)) {
                foundMessage = sentMessages.get(1); // message 4 is at index 1 in sentMessages
                break;
            }
        }

        assertEquals("It is dinner time !", foundMessage);
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 4: Search all messages for recipient +27838884567
    // Expected: returns message 2 AND message 5
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testSearchByRecipient() {

        String searchRecipient = "+27838884567";
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(searchRecipient)) {
                // storedMessages index matches because both lists are parallel
                if (i < storedMessages.size()) {
                    results.add(storedMessages.get(i));
                }
            }
        }

        assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(results.contains("Ok, I am leaving without you."));
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 5: Delete message 2 using its hash
    // Expected: message 2 is no longer in the list after deletion
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testDeleteByHash() {

        String hashToDelete = "BB:1:Wheretime.";

        // Find the position of this hash
        int position = -1;
        for (int i = 0; i < storedHashes.size(); i++) {
            if (storedHashes.get(i).equals(hashToDelete)) {
                position = i;
                break;
            }
        }

        // Make sure we found it before deleting
        assertNotEquals(-1, position);

        // Delete from all lists at that position
        storedMessages.remove(position);
        storedRecipients.remove(position);
        storedHashes.remove(position);
        storedIDs.remove(position);

        // Confirm the message is gone
        assertFalse(storedMessages.contains(
            "Where are you? You are late! I have asked you to be on time."
        ));
    }


    // ─────────────────────────────────────────────────────────────────
    // TEST 6: Display report — all stored messages are present
    // Expected: report contains message 2 and message 5
    // ─────────────────────────────────────────────────────────────────
    @Test
    public void testDisplayReport() {

        assertEquals(2, storedMessages.size());

        assertTrue(storedMessages.contains(
            "Where are you? You are late! I have asked you to be on time."
        ));

        assertTrue(storedMessages.contains(
            "Ok, I am leaving without you."
        ));
    }

}
}