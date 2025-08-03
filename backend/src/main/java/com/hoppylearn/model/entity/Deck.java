package com.hoppylearn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Deck {
    private String id;
    private String deckName;
    private List<String> cardIds;

    // Custom constructor for common use case
    public Deck(String deckName) {
        this.deckName = deckName;
        this.cardIds = new ArrayList<String>();
    }
}
