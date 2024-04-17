package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import mate.academy.rickandmorty.dto.external.ResponsePersonageDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonageApiClientImpl implements PersonageApiClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    @Override
    public List<ResponsePersonageDto> getAllResponsePersonages() {
        ResponseDto responseDto = getResponseDto(BASE_URL);
        List<ResponsePersonageDto> listPersonageDto = new ArrayList<>(responseDto.getPersonages());
        while (responseDto.getInfo().getNext() != null) {
            responseDto = getResponseDto(responseDto.getInfo().getNext());
            listPersonageDto.addAll(responseDto.getPersonages());
        }
        return listPersonageDto;
    }

    @Override
    public ResponseDto getResponseDto(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httprequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httprequest, HttpResponse.BodyHandlers.ofString());
            ResponseDto responseDto = objectMapper.readValue(
                    response.body(),
                    ResponseDto.class);
            return responseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
