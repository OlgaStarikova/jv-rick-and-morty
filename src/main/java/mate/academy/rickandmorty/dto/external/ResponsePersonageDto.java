package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponsePersonageDto(
        Long id,
        String name,
        String status,
        String gender
) {
}
