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
 * This is the model class for BusMsg with Getter and Setter
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusMsg implements Serializable {

    @Valid
    @NotNull(message = "AppHdr is mandatory")
    @JsonProperty("AppHdr")
    private AppHdr appHdr;

    @NotNull(message = "Document is mandatory")
    @JsonProperty("Document")
    private Object document;

}
