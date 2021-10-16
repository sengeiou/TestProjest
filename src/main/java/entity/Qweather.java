package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Qweather {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    @JsonProperty("adm2")
    private String adm2;
    @JsonProperty("adm1")
    private String adm1;
    @JsonProperty("country")
    private String country;
    @JsonProperty("tz")
    private String tz;
    @JsonProperty("utcOffset")
    private String utcOffset;
    @JsonProperty("isDst")
    private String isDst;
    @JsonProperty("type")
    private String type;
    @JsonProperty("rank")
    private String rank;
    @JsonProperty("fxLink")
    private String fxLink;
}
