package com.shafner.mod1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Component
@Profile("default")
public class DefaultContactsDataLoader implements ContactDataLoader {
    @Value("${app.filePath}")
    private String pathToFile;

    @Override
    public HashMap<String, ContactDetails> fetchContacts() throws IOException {
        var contactMap = new HashMap<String, ContactDetails>();
        var fileReader = new BufferedReader(new FileReader(pathToFile));
        String lineContent;

        while ((lineContent = fileReader.readLine()) != null) {
            var contactEntry = new ContactDetails().parseFromString(lineContent, ";");
            contactMap.put(contactEntry.email, contactEntry);
        }

        return contactMap;
    }
}
