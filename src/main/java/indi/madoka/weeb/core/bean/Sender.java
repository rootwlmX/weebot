package indi.madoka.weeb.core.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Sender {
    private Integer age;
    private String nickname;
    private String sex;
    private Integer userId;
}
