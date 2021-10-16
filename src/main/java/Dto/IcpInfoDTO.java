package Dto;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@ToString
public class IcpInfoDTO {
    private String unitName;
    private String limitAccess;
    private String natureName;
    private String serviceName;
    private String updateRecordTime;
    private Long domainId;
    private String homeUrl;
    private String serviceLicence;
    private String leaderName;
    private String domain;
    private String mainLicence;
    private Integer mainId;
    private Integer serviceId;
    private String contentTypeName;
}
