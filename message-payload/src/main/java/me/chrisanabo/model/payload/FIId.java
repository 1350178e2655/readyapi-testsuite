/**
 *
 */
package me.chrisanabo.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This is the model class for FIId with Getter and Setter
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FIId implements Serializable {
    @Valid
    @NotNull(message = "FinInstnId is mandatory")
    @JsonProperty("FinInstnId")
    private FinInstnId finInstnId;
}