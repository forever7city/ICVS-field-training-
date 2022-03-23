package icvs.icvs.domain.entity;
import lombok.*;

import javax.management.Query;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "VW_L1B_MBC")
public class VwL1bMbc {

    @Id
    @Column(name = "PRODUCT_REFERENCE", nullable = false)
    private LocalDateTime product_reference;

    @Column(name = "PRODUCT_MODE")
    private String product_mode;

    @Column(name = "SOURCE_SYSTEM")
    private String source_system;

    @Column(name = "QI_ID")
    private String qi_id;

    @Column(name = "`ALL`")
    private double value;
}
