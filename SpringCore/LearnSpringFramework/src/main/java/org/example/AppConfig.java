package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "org.example")
public class AppConfig {

    @Bean(name = "basicSpellChecker", initMethod = "init", destroyMethod = "destroy")
    public BasicSpellChecker createBasicSpellChecker(){
        return new BasicSpellChecker();
    }
//
//    @Bean(name = "advancedSpellChecker")
//    public AdvancedSpellChecker createAdvancedSpellChecker(){
//        return new AdvancedSpellChecker();
//    }
//
//    @Bean(name = "emailClient")
//    public EmailClient createEmailClient(){
//        EmailClient emailClient = new EmailClient();
//        emailClient.setSpellChecker(createBasicSpellChecker());
//        return emailClient;
//    }
}
