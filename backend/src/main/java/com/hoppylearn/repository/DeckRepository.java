package com.hoppylearn.repository;

import com.hoppylearn.model.entity.Deck;
import com.hoppylearn.model.paramaters.DeckSearchParams;
import java.util.List;

// Interface for different implementations of storage
public interface DeckRepository {

    /**
     * Save a deck (create or update)
     * 
     * @param deck The deck to save
     * @return The saved deck with generated ID
     */
    Deck saveDeck(Deck deck);

    /**
     * Find a deck by its ID
     * 
     * @param id The deck ID
     * @return Optional containing the deck if found, empty otherwise
     */
    Deck getDeck(String id);

    /**
     * Get all decks
     * 
     * @return List of all decks
     */
    List<Deck> getDecks(DeckSearchParams searchParams);

    /**
     * Delete a deck by ID
     * 
     * @param id The deck ID to delete
     * @return true if deck was deleted, false if not found
     */
    boolean deleteDeck(String id);

    /**
     * Check if a deck exists by ID
     * 
     * @param id The deck ID
     * @return true if deck exists, false otherwise
     */
    boolean deckExistsById(String id);

    /**
     * Check if a deck exists by name
     * 
     * @param name The name of the deck
     * @return true if deck with the given name exists, false otherwise
     */
    boolean deckExistsByName(String name);

    /**
     * Count total number of decks
     * 
     * @return Number of decks
     */
    long count();
}
