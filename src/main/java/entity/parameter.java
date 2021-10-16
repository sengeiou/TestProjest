package entity;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class parameter {

    private String pageNum;
    private String pageSize;
    private String unitName;

}
