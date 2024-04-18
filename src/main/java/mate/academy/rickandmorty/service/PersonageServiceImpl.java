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
import org.springframework.transaction.annotation.Transactional;

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
        List<Personage> personages = personageRepository.saveAll(
                personageMapper.toModelList(personageApiClient.getAllResponsePersonages())
        );
    }

    @Override
    @Transactional
    public PersonageDto getRandomPersonage() {
        Optional<Personage> personage = Optional.empty();
        Long personageCount = personageRepository.count();
        if (personageCount > 0) {
            Long randomId = random.nextLong(personageCount + 1);
            personage = personageRepository.findById(randomId);
        }
        return personageMapper
                .toDto(personage.orElseThrow(
                                () -> new EntityNotFoundException("Not found any personage")
                        )
                );
    }

    @Override
    public List<PersonageDto> getPersonagesByName(String name) {
        return personageMapper.toDtoList(personageRepository
                .findAllByNameContainsIgnoreCase(name));
    }
}
