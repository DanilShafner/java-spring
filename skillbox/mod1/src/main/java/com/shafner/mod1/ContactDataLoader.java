package com.shafner.mod1;

import java.io.IOException;
import java.util.HashMap;

public interface ContactDataLoader {
    HashMap<String, ContactDetails> fetchContacts() throws IOException;
}
