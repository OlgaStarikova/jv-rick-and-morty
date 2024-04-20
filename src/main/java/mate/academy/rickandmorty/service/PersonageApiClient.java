package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import mate.academy.rickandmorty.dto.external.ResponsePersonageDto;

public interface PersonageApiClient {
    List<ResponsePersonageDto> getAllResponsePersonages();

    ResponseDto getResponseDto(String url);
}
