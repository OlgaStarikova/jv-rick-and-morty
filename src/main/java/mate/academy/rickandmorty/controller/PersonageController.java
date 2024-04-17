package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.service.PersonageApiClient;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters info", description = "Endpoints for request information")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/characters")
public class PersonageController {
    private final PersonageApiClient personageApiClient;
    private final PersonageService personageService;

    @GetMapping("/search")
    @Operation(summary = "Get a list of characters ", description = " Get a list of all characters"
            + " whose name contains the search string. "
            + " Parameters: name = String for searching")
    public List<PersonageDto> findByName(@RequestParam String name) {
        return personageService.getPersonagesByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get a Random character", description = "The request randomly generates"
            + " a wiki about one character in the universe the animated series Rick & Morty")
    public PersonageDto findRandomId() {
        return personageService.getRandomPersonage();
    }
}
