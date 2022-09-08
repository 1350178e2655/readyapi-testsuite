/**
 *
 */
package me.chrisanabo.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import me.chrisanabo.model.validation.DateFormatCheck;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * This is the model class for AppHdr with Getter and Setter
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppHdr implements Serializable {

    @NotBlank(message = "xmlns is mandatory and cannot be empty")
    private String xmlns;

    @Valid
    @NotNull(message = "Fr is mandatory")
    @JsonProperty("Fr")
    private Fr fr;

    @Valid
    @NotNull(message = "To is mandatory")
    @JsonProperty("To")
    private To to;

    @NotBlank(message = "BizMsgIdr is mandatory and cannot be empty")
    @JsonProperty("BizMsgIdr")
    private String bizMsgIdr;

    @NotBlank(message = "MsgDefIdr is mandatory and cannot be empty")
    @JsonProperty("MsgDefIdr")
    private String msgDefIdr;

    @NotBlank(message = "CreDt is mandatory and cannot be empty")
    @DateFormatCheck(message = "CreDt should be ISO Date format")
    @JsonProperty("CreDt")
    private String creDt;
}
