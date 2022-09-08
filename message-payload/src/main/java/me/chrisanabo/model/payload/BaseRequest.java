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
 * This is the model class for BaseRequest with Getter and Setter
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest implements Serializable {
    @Valid
    @NotNull(message = "BusMsg is mandatory")
    @JsonProperty("BusMsg")
    private BusMsg busMsg;
}
