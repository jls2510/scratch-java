package com.ping23.scratch.gson.nested;

import java.util.List;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Event {
    private Long id;

    private String segmentType;
    private Integer segmentTypeSeqNo;
    private Integer positionInSegment;
    private Integer segmentNo;
    private Long duration;
    private Long offset;
    private String className;
    private Long eventId;
    private Long parentEventId;
    private Integer overrun;
    private String modifiedBy; // legacy field
    
    private List<TemplateBinding> bindings;
    private List<Event> children;

}
