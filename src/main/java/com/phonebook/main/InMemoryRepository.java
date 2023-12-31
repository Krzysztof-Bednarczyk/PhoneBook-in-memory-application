package com.phonebook.main;

import java.util.Map;
import java.util.Set;

/**
 * Repository interface
 */
public interface InMemoryRepository {
    /**
     * @return all repository records "{name: [phone1, phone2]}"
     */
    Map<String, Set<String>> findAll();

    /**
     * @param name
     * @return all phone numbers associated with a name
     */
    Set<String> findAllPhonesByName(String name);

    /**
     * @param phone
     * @return name associated with a phone
     */
    String findNameByPhone(String phone);

    /**
     * add phone number for a name or create new record
     *
     * @param name
     * @param phones
     */
    void addPhone(String name, Set<String> phones);

    /**
     * removes a phone from set. If set becomes empty after deletion remove record "{name:[phone]}" completely
     *
     * @param phone
     * @throws IllegalArgumentException if there is no such phone in repo
     */
    void removePhone(String phone) throws IllegalArgumentException;

    /**
     * removes a contact and all it's phone numbers from memory
     *
     * @param name
     * @throws IllegalArgumentException
     */
    void removeContact(String name) throws IllegalArgumentException;
}
