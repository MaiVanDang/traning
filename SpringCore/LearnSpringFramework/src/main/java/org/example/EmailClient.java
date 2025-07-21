package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailClient {
    @Autowired
    @Qualifier("advancedSpellChecker")
    private SpellChecker spellChecker;

    EmailClient() {
    }

    void sendEmail (String emailMessage){
        spellChecker.checkSpelling(emailMessage);
    }

}
