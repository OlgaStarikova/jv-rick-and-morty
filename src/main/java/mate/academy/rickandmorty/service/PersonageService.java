package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.PersonageDto;

public interface PersonageService {
    void init();

    PersonageDto getRandomPersonage();

    List<PersonageDto> getPersonagesByName(String name);
}
