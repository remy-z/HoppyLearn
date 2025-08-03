package com.hoppylearn.service.impl;

import com.hoppylearn.exception.IllegalUserInputException;
import com.hoppylearn.model.entity.Deck;
import com.hoppylearn.model.paramaters.DeckSearchParams;
import com.hoppylearn.repository.DeckRepository;
import com.hoppylearn.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementation of DeckService that handles business logic for Decks
@Service
public class InMemoryDeckService implements DeckService {

    private final DeckRepository deckRepository;

    @Autowired
    public InMemoryDeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Deck createDeck(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalUserInputException("Deck name cannot be null or empty");
        }

        if (this.deckExistsByName(name)) {
            throw new IllegalUserInputException("Deck name already exists: " + name.trim());
        }

        Deck deck = new Deck(name.trim());
        return deckRepository.saveDeck(deck);
    }

    @Override
    public Deck getDeck(String id) {
        if (id == null) {
            return null;
        }
        return deckRepository.getDeck(id);
    }

    @Override
    public List<Deck> getDecks(DeckSearchParams searchParams) {
        if (searchParams == null) {
            return null;
        }
        return deckRepository.getDecks(searchParams);
    }

    @Override
    public boolean deleteDeck(String id) {
        if (id == null) {
            throw new IllegalUserInputException("Deck ID cannot be null");
        }
        return deckRepository.deleteDeck(id);
    }

    @Override
    public boolean deckExistsById(String id) {
        if (id == null) {
            throw new IllegalUserInputException("Deck ID cannot be null");
        }
        return deckRepository.deckExistsById(id);
    }

    @Override
    public boolean deckExistsByName(String name) {
        return deckRepository.deckExistsByName(name);
    }
}
