package com.phonebook.main;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBook;
import com.phonebook.spring.PhoneBookFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.*;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {

    public static void main(String[] args) {
        ApplicationContext context = newApplicationContext(args);

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.getProperty("line.separator"));

        PhoneBook phoneBook = context.getBean("phoneBook", PhoneBook.class);
        PhoneBookFormatter renderer = (PhoneBookFormatter) context.getBean("phoneBookFormatter");

        renderer.info("type 'exit' to quit.");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("exit")) {
                renderer.info("Have a good day...");
                break;
            }
            try {
                if ( line.equals("SHOW")){
                    Map<String, Set<String>> data = phoneBook.findAll();
                    renderer.show(data);
                } else if (line.matches("SHOW\\s\\w+")) {
                    String name = line.substring(5);
                    Set<String> phones = phoneBook.findAllPhonesByName(name);
                    renderer.showPhones(phones);
                } else if (line.startsWith("+")) {
                    String name = phoneBook.findNameByPhone(line);
                    renderer.showName(name);
                } else if (line.startsWith("ADD")) {
                    String contactInfo = line.substring(4);
                    String[] contactInfoArgs = contactInfo.split(" |,");
                    if (contactInfoArgs.length > 1) phoneBook.addContact(contactInfoArgs);
                }else if (line.startsWith("REMOVE_PHONE")) {
                    String phone = line.substring(13);
                    phoneBook.removePhone(phone);
                    renderer.info("Phone number removed!");
                }
            } catch (Exception e) {
                renderer.error(e);
            }
        }
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }

}
