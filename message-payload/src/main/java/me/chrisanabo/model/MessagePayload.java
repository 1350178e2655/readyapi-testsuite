package me.chrisanabo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chrisanabo.model.validation.DateFormatCheck;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This is model class for Message Payload with basic validation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagePayload implements Serializable {

    @NotBlank(message = "issuer field is mandatory and cannot be empty")
    @JsonProperty("issuer")
    private String issuer;

    @NotBlank(message = "issueTime field is mandatory and cannot be empty")
    @DateFormatCheck(message = "issueTime should be ISO Date format")
    @JsonProperty("issueTime")
    private String issueTime;

    @Valid
    @NotNull(message = "payload is mandatory")
    @JsonProperty("payload")
    private Object payload;

}
