package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ResponsePersonageDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.model.Personage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonageMapper {
    PersonageDto toDto(Personage personage);

    @Mapping(source = "id", target = "externalId")
    Personage toModel(ResponsePersonageDto responsePersonageDto);

    List<Personage> toModelList(List<ResponsePersonageDto> dtoList);

    List<PersonageDto> toDtoList(List<Personage> list);
}
