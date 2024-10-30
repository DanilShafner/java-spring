package com.shafner.mod1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

@Component
public class ContactManager {
    ContactDataLoader contactDataLoader;
    private HashMap<String, ContactDetails> contactList;

    @Value("${app.filePath}")
    private String fileOutputPath;

    public ContactManager(ContactDataLoader contactDataLoader) {
        this.contactDataLoader = contactDataLoader;
    }

    public void initiate() {
        try {
            contactList = contactDataLoader.fetchContacts();
        } catch (IOException e) {
            System.out.println("Error loading file");
            contactList = new HashMap<>();
        }

        System.out.println(
                "commands: list - show all contacts"
                        + "\\nadd: John Doe; +78001111111; johndoe@example.com - add a contact"
                        + "\\ndelete: johndoe@example.com - remove contact by email"
                        + "\\nexit - save and exit"
        );

        var scanner = new Scanner(System.in);

        while (true) {
            var userInput = scanner.nextLine();
            var inputParts = userInput.split(": ");
            var action = inputParts[0];
            var actionArgs = inputParts.length == 1 ? "" : inputParts[1];

            if (action.equals("list")) {
                displayContacts();
            } else if (action.equals("add")) {
                addContact(actionArgs);
            } else if (action.equals("delete")) {
                removeContact(actionArgs);
            } else if (action.equals("exit")) {
                saveAndExit();
                break;
            } else {
                System.out.println("Unrecognized command");
            }
        }
    }

    private void displayContacts() {
        if (contactList.isEmpty())
            System.out.println("Contact list is empty");

        for (var contact : contactList.values())
            System.out.println(contact);
    }

    private void addContact(String args) {
        var contact = new ContactDetails().parseFromString(args);
        contactList.put(contact.email, contact);
    }

    private void removeContact(String email) {
        contactList.remove(email);
    }

    private void saveAndExit() {
        var saveSuccessful = true;

        try {
            saveContactsToFile();
        } catch (IOException e) {
            System.out.println("File save error");
            saveSuccessful = false;
        }

        if (saveSuccessful)
            System.out.println("Contacts saved successfully");
    }

    private void saveContactsToFile() throws IOException {
        var writer = new BufferedWriter(new FileWriter(fileOutputPath));
        for (var contact : contactList.values()) {
            writer.write(contact.toString(";"));
            writer.newLine();
        }
        writer.close();
    }
}
