package icvs.icvs.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class inputParams {

    @NotNull
    @JsonProperty("startTime")
    private LocalDateTime startDate;

    @NotNull
    @JsonProperty("endTime")
    private LocalDateTime endDate;

    @NotNull
    @JsonProperty("area")
    private String area;

    @NotNull
    @JsonProperty("parameter")
    private String parameter;
}
