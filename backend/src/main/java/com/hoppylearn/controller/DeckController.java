package com.hoppylearn.controller;

import com.hoppylearn.exception.IllegalUserInputException;
import com.hoppylearn.exception.ResourceNotFoundException;
import com.hoppylearn.model.entity.Deck;
import com.hoppylearn.model.paramaters.DeckSearchParams;
import com.hoppylearn.model.request.DeckRequest;
import com.hoppylearn.model.response.DeckResponse;
import com.hoppylearn.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping("/decks")
    public ResponseEntity<DeckResponse> handlePost(@RequestBody DeckRequest deckRequest) {
        Deck deck = deckService.createDeck(deckRequest.getDeckName());
        if (deck == null) {
            throw new IllegalUserInputException("Failed to create deck with name: " + deckRequest.getDeckName());
        }
        DeckResponse response = new DeckResponse(deck);
        URI location = URI.create("/v1/decks/" + deck.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/decks/{id}")
    public ResponseEntity<DeckResponse> handleGetById(@PathVariable String id) {
        Deck deck = deckService.getDeck(id);
        if (deck == null) {
            throw new ResourceNotFoundException("Deck not found with id: " + id);
        }
        return ResponseEntity.ok(new DeckResponse(deck));
    }

    @GetMapping("/decks")
    public ResponseEntity<List<DeckResponse>> handleGet(
            @Validated DeckSearchParams searchParams) {
        List<Deck> decks = deckService.getDecks(searchParams);
        List<DeckResponse> responses = new ArrayList<>();
        for (Deck deck : decks) {
            DeckResponse response = new DeckResponse(deck);
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/decks/{id}")
    public ResponseEntity<String> handleDelete(@PathVariable String id) {
        boolean deleted = deckService.deleteDeck(id);
        if (!deleted) {
            throw new IllegalUserInputException("Deck of id " + id + " not deleted");
        }
        return ResponseEntity.ok("Deck deleted successfully");
    }
}
