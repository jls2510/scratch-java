package com.ping23.validation;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LatLon implements Serializable {

    /** Class Members * */
    private static final long serialVersionUID = 1L;

    /** Instance Members * */
    @Min(-90)
    @Max(90)
    @NotNull private Double lat;

    @Min(-180)
    @Max(180)
    @NotNull private Double lon;
}
