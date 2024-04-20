package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseInfoDto(
        Integer count,
        Integer pages,
        String next
) {
}
