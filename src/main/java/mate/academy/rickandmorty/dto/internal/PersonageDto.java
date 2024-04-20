package mate.academy.rickandmorty.dto.internal;

public record PersonageDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender) {
}
