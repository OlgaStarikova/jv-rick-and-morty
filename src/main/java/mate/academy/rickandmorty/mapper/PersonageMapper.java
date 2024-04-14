package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ResponsePersonageDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.model.Personage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonageMapper {
    @Mapping(source = "id", target = "id")
    PersonageDto toDto(Personage personage);

    @Mapping(source = "id", target = "externalId")
    Personage toModel(ResponsePersonageDto responsePersonageDto);
}
