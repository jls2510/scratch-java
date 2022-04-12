package com.ping23.scratch.gson.nested;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventBinding {
    private Long id;

    private String roleType;
    private String resourceName;
    private String modifiedBy;

}
