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
public class InputIrParams {

    @NotNull
    @JsonProperty("Leo")
    private String Leo;

    @NotNull
    @JsonProperty("band")
    private String Ami;

    @NotNull
    @JsonProperty("node")
    private String Node;

    @NotNull
    @JsonProperty("type")
    private String Type;

    @NotNull
    @JsonProperty("date")
    private LocalDateTime Date;
}
