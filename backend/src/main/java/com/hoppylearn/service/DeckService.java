package com.hoppylearn.service;

import com.hoppylearn.model.entity.Deck;
import com.hoppylearn.model.paramaters.DeckSearchParams;

import java.util.List;

/**
 * Service interface for business logic related to Decks
 */
public interface DeckService {

    /**
     * Create a new deck
     * 
     * @param name The name of the deck
     * @return The created deck
     */
    Deck createDeck(String name);

    /**
     * Get a deck by ID
     * 
     * @param id The deck ID
     * @return Optional containing the deck if found
     */
    Deck getDeck(String id);

    /**
     * Get a deck by name
     * 
     * @param searchParams search paramaters to filter decks
     * @return List of decks matching the search parameters
     */
    List<Deck> getDecks(DeckSearchParams searchParams);

    /**
     * Delete a deck
     * 
     * @param id The deck ID
     * @return true if deleted, false if not found
     */
    boolean deleteDeck(String id);

    /**
     * Check if deck exists
     * 
     * @param id The deck ID
     * @return true if exists, false otherwise
     */
    boolean deckExistsById(String id);

    /**
     * Check if deck exists by name
     * 
     * @param name The name of the deck
     * @return true if exists, false otherwise
     */
    boolean deckExistsByName(String name);
}
