/**
 *
 */
package me.chrisanabo.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import me.chrisanabo.model.constants.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * This is the model class for FinInstnId with Getter and Setter
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinInstnId implements Serializable {
    @NotBlank(message = "BICFI is mandatory and cannot be empty")
    @Size(min = 11, max = 11, message = "BICFI must be ${max} chars")
    @Pattern(regexp = Constants.SWIFTBIC_FORMAT, message = "BICFI should be alphanumeric format")
    @JsonProperty("BICFI")
    private String bICFI;
}
