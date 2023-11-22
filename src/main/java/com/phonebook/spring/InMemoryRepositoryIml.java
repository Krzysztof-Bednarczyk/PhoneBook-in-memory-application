package com.phonebook.spring;

import com.phonebook.exception.ContactNotFoundException;
import com.phonebook.exception.ContactPhoneNumbersUpdatedException;
import com.phonebook.exception.ContactRemovedException;
import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Keeps phoneBook data in memory in ordered in accordance to addition.
 */
@Repository
public class InMemoryRepositoryIml implements InMemoryRepository {

    private Map<String, Set<String>> data;

    /**
     * no args constructor
     */
    public InMemoryRepositoryIml() {
        // LinkedHashMap is chosen because usually iteration order matters
        this(new LinkedHashMap<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public InMemoryRepositoryIml(Map<String, Set<String>> data) {
        this.data = new LinkedHashMap<>(data);
    }

    @Override
    public Map<String, Set<String>> findAll() {
        return new LinkedHashMap<>(this.data);
    }

    @Override
    public Set<String> findAllPhonesByName(String name) {
        return new LinkedHashMap<>(this.data).get(name);
    }

    @Override
    public String findNameByPhone(String phone) {
        Optional<Set<String>> phoneSet = new LinkedHashMap<>(this.data).values()
                .stream()
                .filter(phones -> phones.contains(phone)).
                findFirst();
        if (phoneSet.isPresent()) {
            Optional<Map.Entry<String, Set<String>>> nameEntry = findAll().entrySet().stream().filter(entry -> entry.getValue().equals(phoneSet.get())).findFirst();
            if (nameEntry.isPresent()) return nameEntry.get().getKey();
        }
        throw new RuntimeException("ERROR!");
    }

    @Override
    public void addPhone(String name, Set<String> phones) {
        Optional<Set<String>> phoneSet = Optional.ofNullable(this.data.get(name));
        phoneSet.ifPresentOrElse(strings -> {
            strings.addAll(phones);
            throw new ContactPhoneNumbersUpdatedException("Updated phone numbers!");
        }, () -> this.data.put(name, phones));
    }

    @Override
    public void removePhone(String phone) {
        String name = findNameByPhone(phone);
        Set<String> phoneSet = this.data.get(name);
        phoneSet.remove(phone);
        if (phoneSet.isEmpty()) {
            this.data.remove(name);
            throw new ContactRemovedException("No more phone numbers!");
        }
    }

    @Override
    public void removeContact(String name) throws IllegalArgumentException {
        if (this.data.get(name) == null) throw new ContactNotFoundException("No contact!");
        this.data.remove(name);
    }
}
