package icvs.icvs.controller;

import icvs.icvs.service.GsicsGetImagePathService;
import icvs.icvs.vo.InputIrParams;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class IrController {

    private final GsicsGetImagePathService gsicsGetImagePathService;

    @PostMapping(path="/gsicsAmilr.do", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> chartData(@RequestBody InputIrParams input) throws IOException {
        ResponseEntity<Resource> result = gsicsGetImagePathService.getIrImage(input);
        System.out.println(result);
        return result;
    }
}
