package com.ping23.scratch.gson.nested;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class TemplateDescriptor {

    private String name;
    private String className;
    private int status;
    private int programOriginator;
    private String networkName;
    private boolean readOnly;
    private long templateTimestamp;

}
