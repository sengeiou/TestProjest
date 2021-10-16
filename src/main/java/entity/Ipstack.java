package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class Ipstack {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("type")
    private String type;
    @JsonProperty("continent_code")
    private String continentCode;
    @JsonProperty("continent_name")
    private String continentName;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("region_code")
    private String regionCode;
    @JsonProperty("region_name")
    private String regionName;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("location")
    private LocationDTO location;

    @NoArgsConstructor
    @Data
    @ToString
    public static class LocationDTO {
        @JsonProperty("geoname_id")
        private Integer geonameId;
        @JsonProperty("capital")
        private String capital;
        @JsonProperty("languages")
        private List<LanguagesDTO> languages;
        @JsonProperty("country_flag")
        private String countryFlag;
        @JsonProperty("country_flag_emoji")
        private String countryFlagEmoji;
        @JsonProperty("country_flag_emoji_unicode")
        private String countryFlagEmojiUnicode;
        @JsonProperty("calling_code")
        private String callingCode;
        @JsonProperty("is_eu")
        private Boolean isEu;

        @NoArgsConstructor
        @Data
        public static class LanguagesDTO {
            @JsonProperty("code")
            private String code;
            @JsonProperty("name")
            private String name;
            @JsonProperty("native")
            private String nativeX;
        }
    }
}
