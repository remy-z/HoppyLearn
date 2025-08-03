package com.hoppylearn.repository.impl;

import com.hoppylearn.exception.IllegalUserInputException;
import com.hoppylearn.exception.ResourceNotFoundException;
import com.hoppylearn.model.entity.Deck;
import com.hoppylearn.model.paramaters.DeckSearchParams;
import com.hoppylearn.repository.DeckRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// In-memory implementation of DeckRepository, active when "memory" profile is set
@Repository
@Profile({ "memory", "default" })
public class InMemoryDeckRepository implements DeckRepository {

    // Thread-safe storage for decks
    private final Map<String, Deck> storage = new ConcurrentHashMap<>();
    private final Map<String, Deck> names = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Deck saveDeck(Deck deck) {
        if (deck == null) {
            throw new IllegalUserInputException("Deck cannot be null");
        }

        if (deck.getId() == null) {
            deck.setId(String.valueOf(idGenerator.getAndIncrement()));
        }

        storage.put(deck.getId(), deck);
        names.put(deck.getDeckName().trim(), deck);
        return deck;
    }

    @Override
    public Deck getDeck(String id) {
        if (id == null) {
            return null;
        }
        return storage.get(id);
    }

    @Override
    public List<Deck> getDecks(DeckSearchParams searchParams) {
        if (searchParams == null) {
            return new ArrayList<>(storage.values());
        }
        List<Deck> filtered = storage.values().stream()
                .filter(deck -> searchParams.getName() == null || deck.getDeckName().equals(searchParams.getName()))
                .collect(Collectors.toList());
        return filtered;
    }

    @Override
    public boolean deleteDeck(String id) {
        Deck removedDeck = storage.remove(id);
        if (removedDeck == null) {
            throw new ResourceNotFoundException("Deck not found with id: " + id);
        }
        names.remove(removedDeck.getDeckName());
        return removedDeck != null;
    }

    @Override
    public boolean deckExistsById(String id) {
        if (id == null) {
            return false;
        }
        return storage.containsKey(id);
    }

    @Override
    public boolean deckExistsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return names.containsKey(name.trim());
    }

    @Override
    public long count() {
        return storage.size();
    }

    // Delete all - for testing purposes
    public void clear() {
        storage.clear();
        idGenerator.set(1);
    }

    // Get current state - for testing purposes
    public Map<String, Deck> getStorageSnapshot() {
        return new HashMap<>(storage);
    }
}
