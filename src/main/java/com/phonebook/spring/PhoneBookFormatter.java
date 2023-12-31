package com.phonebook.spring;

import com.phonebook.exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.String.join;

/**
 * Responsible for formatting in {@code PhoneBook} application
 */
@Component
public class PhoneBookFormatter {

    // comes from application.properties file
    @Value("${lowerCaseNames}")
    private boolean lowerCaseNames = false;
    @Value("${columnsSeparator}")
    private char columnsSeparator = '|';

    /**
     * output level
     */
    public enum Level {
        INFO("[INFO]"),
        ERROR("[ERROR]");

        private String level;

        Level(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return this.level;
        }
    }

    /**
     * @param data to print send to stdout
     */
    public void show(Map<String, Set<String>> data) {
        data.forEach((k, v) -> System.out.println(format("%-20.20s%s%-10s", k, this.columnsSeparator, join(", ",
                v = lowerCaseNames ? v.stream().map(e -> e.toLowerCase()).collect(Collectors.toSet()) : v))));
    }

    public void showPhones(Set<String> phones) {
        phones.forEach(System.out::println);
    }

    public void showName(String name) {
        System.out.println(name);
    }

    /**
     * add {@code Level.INFO} message to stout
     *
     * @param message
     */
    public void info(String message) {
        System.out.println(format("\u001B[32m%s: %s\u001B[0m", Level.INFO, message));
    }

    /**
     * add {@code Level.ERROR} message to stout
     *
     * @param message
     */
    public void error(String message) {
        System.out.println(format("\u001B[31m%s: %s\u001B[0m", Level.ERROR, message));
    }

    /**
     * add {@code Level.ERROR} cause to stout
     *
     * @param cause of an error
     */
    public void error(Throwable cause) {
        try {
            if (cause.getMessage().contains("Incorrect Command!")) {
                throw new IncorrectCommandException("Incorrect command! Please try again :)", cause);
            }
            if (cause.getMessage().contains("phones") || cause.getMessage().contains("No contact!")) {
                throw new ContactNotFoundException("No contact like this in our phonebook!", cause);
            }
            if (cause.getMessage().contains("ERROR")) {
                throw new PhoneNotFoundException("No phone like this in our phonebook!", cause);
            }
            if (cause.getMessage().contains("Incorrect format!")) {
                throw new PhoneIncorrectFormatException("You are trying to add a phone number in an incorrect format!", cause);
            }
            if (cause.getMessage().contains("Incorrect ADD command!")) {
                throw new ContactWithoutPhoneException("You are trying to add a contact without a phone number!", cause);
            }
            if (cause.getMessage().contains("No more phone numbers!")) {
                throw new ContactRemovedException("Phone number removed! No more phone numbers for this contact! Contact removed!", cause);
            }
            if (cause.getMessage().contains("Updated phone numbers!")) {
                throw new ContactPhoneNumbersUpdatedException("Contact phone numbers updated!", cause);
            }

        } catch (ContactRemovedException | ContactPhoneNumbersUpdatedException e) {
            info(e.getMessage());
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    /*************************
     * getters required for bean properties to be injected
     *************************/

    /**
     * @param lowerCaseNames
     */
    public void setLowerCaseNames(boolean lowerCaseNames) {
        this.lowerCaseNames = lowerCaseNames;
    }

    /**
     * @param columnsSeparator
     */
    public void setColumnsSeparator(char columnsSeparator) {
        this.columnsSeparator = columnsSeparator;
    }
}
