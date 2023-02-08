package indi.madoka.weeb.core.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@lombok.Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Message {
    private String type;
    private Data data;
}
