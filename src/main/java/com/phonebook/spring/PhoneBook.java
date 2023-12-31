package com.phonebook.spring;

import com.phonebook.exception.PhoneIncorrectFormatException;
import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * PhoneBook service implementation
 */
@Service
public class PhoneBook {

    // @Autowired
    private InMemoryRepository repository;

    public PhoneBook() {
        // be careful this.repository will not be initialised if injection on setter is chosen
    }

    /**
     * injection is supported on constructor level.
     *
     * @param repository
     */
    // @Autowired
    public PhoneBook(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * injection is supported on setter level
     *
     * @param repository
     */
    public void setRepository(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * @return all pairs of type {name: [phone1, phone2]}
     */
    public Map<String, Set<String>> findAll() {
        return repository.findAll();
    }

    public Set<String> findAllPhonesByName(String name) {
        return repository.findAllPhonesByName(name);
    }

    public String findNameByPhone(String phone) {
        return repository.findNameByPhone(phone);
    }

    public void addContact(String[] contactInfo) {
        String name = contactInfo[0];
        Set<String> phones = Arrays.stream(contactInfo).filter(x -> x.contains("+")).collect(Collectors.toSet());
        if (phones.isEmpty()) {
            throw new PhoneIncorrectFormatException("Incorrect format!");
        }
        repository.addPhone(name, phones);
    }

    public void removePhone(String phone) {
        repository.removePhone(phone);
    }

    public void removeContact(String name) {
        repository.removeContact(name);
    }
}
