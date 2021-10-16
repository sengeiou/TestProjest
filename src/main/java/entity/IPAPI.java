package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class IPAPI {

    @JsonProperty("ip")
    private String ip;
    @JsonProperty("version")
    private String version;
    @JsonProperty("city")
    private String city;
    @JsonProperty("region")
    private String region;
    @JsonProperty("region_code")
    private String regionCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_code_iso3")
    private String countryCodeIso3;
    @JsonProperty("country_capital")
    private String countryCapital;
    @JsonProperty("country_tld")
    private String countryTld;
    @JsonProperty("continent_code")
    private String continentCode;
    @JsonProperty("in_eu")
    private Boolean inEu;
    @JsonProperty("postal")
    private String postal;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("utc_offset")
    private String utcOffset;
    @JsonProperty("country_calling_code")
    private String countryCallingCode;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("currency_name")
    private String currencyName;
    @JsonProperty("languages")
    private String languages;
    @JsonProperty("country_area")
    private Double countryArea;
    @JsonProperty("country_population")
    private Double countryPopulation;
    @JsonProperty("asn")
    private String asn;
    @JsonProperty("org")
    private String org;
}
