package com.shafner.mod1;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Component
@Profile("init")
public class InitContactsDataLoader implements ContactDataLoader {
    @Override
    public HashMap<String, ContactDetails> fetchContacts() throws IOException {
        var initialContacts = new HashMap<String, ContactDetails>();
        var resourceFilePath = "src/main/resources/default-contacts.txt";
        var bufferedReader = new BufferedReader(new FileReader(resourceFilePath));
        String fileLine;

        while ((fileLine = bufferedReader.readLine()) != null) {
            var contactRecord = new ContactDetails().parseFromString(fileLine, ";");
            initialContacts.put(contactRecord.email, contactRecord);
        }

        return initialContacts;
    }
}
