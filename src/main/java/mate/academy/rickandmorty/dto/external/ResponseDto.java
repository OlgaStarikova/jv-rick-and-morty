package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ResponseDto {
    private ResponseInfoDto info;
    @JsonProperty("results")
    private List<ResponsePersonageDto> personages;
}
