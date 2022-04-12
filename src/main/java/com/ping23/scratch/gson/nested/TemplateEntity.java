package com.ping23.scratch.gson.nested;

import java.time.Instant;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TemplateEntity {

    private Long id;
    private String name;
    private String embedIndicator;
    private String salesReleaseIndicator;
    private Boolean deleteIndicator;
    private Instant deleteDate;
    private Integer programOriginator;
    private Long duration;
    private String className;
    private Boolean readOnly;
    private Instant usedOn;
    private String modifiedBy; // legacy field
    private Long modifiedAt; // legacy field
    
    private List<TemplateBinding> bindings;
    private List<Event> events;
    
}
