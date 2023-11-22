package com.phonebook.spring;

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
        return "No phone like this in our phonebook";
    }

    @Override
    public void addPhone(String name, Set<String> phones) {
       this.data.put(name, phones);
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Implement it!");
    }
}
