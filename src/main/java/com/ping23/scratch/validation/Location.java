package com.ping23.scratch.validation;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class Location implements Serializable {

    /** Class Members * */
    private static final long serialVersionUID = 1L;

    /** Instance Members * */
    private String label;

    @Size(max = 1000)
    private String name;
    @Valid
    private LatLon latLon;
    private List<String> addressLines;
    @Size(min = 2, max = 50)
    private String city;
    @Size(min = 2, max = 50)
    private String county;
    @Pattern(regexp="[A-Z]{2}", message="State must be exactly two letters")
    private String state;
    @Pattern(regexp="[A-Z]{2}", message="Country must be exactly two letters")
    private String country;
    @Pattern(regexp="\\d{5}", message="Postal code must be exactly 5 digits")
    private String postalCode;
    @Size(min = 2, max = 50)
    private String timeZoneId;
}
