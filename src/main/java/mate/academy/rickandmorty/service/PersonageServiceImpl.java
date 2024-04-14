package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonageServiceImpl implements PersonageService {
    private final PersonageApiClient personageApiClient;
    private final PersonageRepository personageRepository;
    private final PersonageMapper personageMapper;
    private final Random random = new Random();

    @Override
    @PostConstruct
    public void init() {
        List<Personage> listPersonages = personageApiClient
                .getAllResponsePersonages()
                .stream()
                .map(personageMapper::toModel)
                .toList();
        List<Personage> personages = personageRepository.saveAll(listPersonages);
    }

    @Override
    public PersonageDto getRandomPersonage() {
        Optional<Personage> personage = Optional.empty();
        while (personage.isEmpty() && personageRepository.getMaxId() != 0) {
            Long randomId = random.nextLong(personageRepository.getMaxId() + 1);
            personage = personageRepository.findById(randomId);
        }
        return personageMapper
                .toDto(personage.orElseThrow(
                                () -> new EntityNotFoundException("Not found any random personage")
                        )
                );
    }

    @Override
    public List<PersonageDto> getPersonagesByName(String name) {
        return personageRepository
                .findAllByNameContainsIgnoreCase(name)
                .stream()
                .map(personageMapper::toDto)
                .toList();
    }
}
