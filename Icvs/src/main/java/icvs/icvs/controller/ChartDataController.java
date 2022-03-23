package icvs.icvs.controller;

import icvs.icvs.service.ChartDataService;
import icvs.icvs.vo.inputParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Scanner;

@Controller
public class ChartDataController {

    @Autowired
    private ChartDataService chartDataService;

    @ResponseBody
    @PostMapping("/locate.do")
    public Object chartData(@RequestBody inputParams input){
        System.out.println(input);
        Object page = chartDataService.findALl(input);
        return page;
    }
}
