package icvs.icvs.service;

import static icvs.icvs.domain.entity.QVwL1bMbc.vwL1bMbc;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import icvs.icvs.domain.entity.*;
import icvs.icvs.repository.*;
import icvs.icvs.vo.inputParams;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChartDataService {

    @Autowired
    private final VwL1bMbcRepository vwL1bMbcRepository;

    LocalDateTime utcZero = LocalDateTime.of(1970, 1, 1, 0, 0);

    public Object findALl(inputParams param) {
        QSort sortRequest = QSort.unsorted();
        BooleanBuilder predicate = new BooleanBuilder();

        List<Object> dto = new ArrayList<>();

        LocalDateTime from = param.getStartDate().plusHours(9);
        LocalDateTime to = param.getEndDate().plusHours(9);

        if (param.getParameter().equals("NAV_PERFORM_EW")) {

            predicate.and(vwL1bMbc.product_mode.eq(param.getArea()));
            predicate.and(vwL1bMbc.qi_id.eq(param.getParameter()));
            predicate.and(vwL1bMbc.product_reference.between(from, to));

            sortRequest = sortRequest.and(vwL1bMbc.product_reference.asc());

            Iterable<VwL1bMbc> entities = vwL1bMbcRepository.findAll(predicate, sortRequest);

            dto = VwL1bMbcNsHighchartsForm(entities);


        } else if (param.getParameter().equals("NAV_PERFORM_NS")) {

            predicate.and(vwL1bMbc.product_mode.eq(param.getArea()));
            predicate.and(vwL1bMbc.qi_id.eq(param.getParameter()));
            predicate.and(vwL1bMbc.product_reference.between(from, to));

            sortRequest = sortRequest.and(vwL1bMbc.product_reference.asc());

            Iterable<VwL1bMbc> entities = vwL1bMbcRepository.findAll(predicate, sortRequest);

            dto = VwL1bMbcNsHighchartsForm(entities);

        }
        return dto;
    }
    private List<Object> VwL1bMbcNsHighchartsForm(Iterable<VwL1bMbc> entities) {
        List<Object> allData = new ArrayList<>();
        List<Object> dto = new ArrayList<>();

        entities.forEach(entity -> {
            long utc = utcZero.until(entity.getProduct_reference(), ChronoUnit.MILLIS);
            Object[] SingleData = {utc, entity.getValue()};
            allData.add(SingleData);
        });

        Map<String, Object> nav_perform_ns = new LinkedHashMap<String, Object>();

        nav_perform_ns.put("showInLegend", false);
        nav_perform_ns.put("name", "NAV PERFORM NS");
        nav_perform_ns.put("data", allData);

        dto.add(nav_perform_ns);

        return dto;
    }
}
