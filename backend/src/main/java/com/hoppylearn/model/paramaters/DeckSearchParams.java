package com.hoppylearn.model.paramaters;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckSearchParams {

    @NotBlank
    private String name;
    // private List<String> tags;
    // private String userId;
}
