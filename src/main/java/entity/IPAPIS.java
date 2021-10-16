package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class IPAPIS {
    @JsonProperty("query")
    private String query;
    @JsonProperty("status")
    private String status;
    @JsonProperty("continent")
    private String continent;
    @JsonProperty("continentCode")
    private String continentCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("region")
    private String region;
    @JsonProperty("regionName")
    private String regionName;
    @JsonProperty("city")
    private String city;
    @JsonProperty("district")
    private String district;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("isp")
    private String isp;
    @JsonProperty("org")
    private String org;
    @JsonProperty("as")
    private String as;
    @JsonProperty("asname")
    private String asname;
    @JsonProperty("reverse")
    private String reverse;
    @JsonProperty("mobile")
    private Boolean mobile;
    @JsonProperty("proxy")
    private Boolean proxy;
    @JsonProperty("hosting")
    private Boolean hosting;
}
