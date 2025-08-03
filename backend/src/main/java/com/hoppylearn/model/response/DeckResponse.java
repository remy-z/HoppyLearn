package com.hoppylearn.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.hoppylearn.model.entity.Deck;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeckResponse {
    private String id;
    private String deckName;
    private List<String> cardIds;

    public DeckResponse(Deck deck) {
        this.id = deck.getId();
        this.deckName = deck.getDeckName();
        this.cardIds = deck.getCardIds();
    }
}
