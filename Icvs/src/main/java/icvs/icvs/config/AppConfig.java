package icvs.icvs.config;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Path;

@Data
@Component
@ConfigurationProperties("appconfig")
@Validated
public class AppConfig {

    @NotNull
    Path imgPath;
}
