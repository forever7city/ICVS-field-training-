package icvs.icvs.service;

import icvs.icvs.config.AppConfig;
import icvs.icvs.vo.InputIrParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class fileService {

    private final AppConfig appConfig;

    public String getImgPath(String path) {
        String imgPath = appConfig.getImgPath()+path;
        System.out.println(imgPath);
        return imgPath;
    };

    public String createIrPath(InputIrParams param) {
        String YYYYMMDD = param.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String smallSatelliteName = MatchingSatName(param.getLeo());

        String band = MatchingBandName(param.getAmi());

        String path = "\\IR\\"+param.getLeo()+"\\gk2a_ami_"+smallSatelliteName+"_"+band+"_"+param.getType()+"_"+param.getNode()+"_"+YYYYMMDD+".png";
        return path;
    }

    public String MatchingSatName(String Sat) {

        String Matching = null;

        switch(Sat) {
            case "AIRS" :
                Matching = "aqua_airs";
                break;
            case "IASI" :
                Matching = "metopa_iasi";
                break;
            case "IASI_B" :
                Matching = "metopb_iasi";
                break;
            case "IASI_C" :
                Matching = "metopc_iasi";
                break;
            case "SNPP_CrISF" :
                Matching = "snpp_crif";
                break;
            case "NOAA20_CrISF" :
                Matching = "noaa20_crif";
                break;
        }
        return Matching;
    }
    public String MatchingBandName(String band) {

        String Matching = null;

        switch (band) {
            case "1":
                Matching = "vi004";
                break;
            case "2":
                Matching = "vi005";
                break;
            case "3":
                Matching = "vi006";
                break;
            case "4":
                Matching = "vi008";
                break;
            case "5":
                Matching = "nr013";
                break;
            case "6":
                Matching = "nr016";
                break;
            case "7":
                Matching = "ir087";
                break;
            case "8":
                Matching = "ir096";
                break;
            case "9":
                Matching = "ir105";
                break;
            case "10":
                Matching = "ir112";
                break;
            case "11":
                Matching = "ir123";
                break;
            case "12":
                Matching = "ir133";
                break;
            case "13":
                Matching = "sw038";
                break;
            case "14":
                Matching = "wv063";
                break;
            case "15":
                Matching = "wv069";
                break;
            case "16":
                Matching = "wv073";
                break;
        }
        return Matching;
    }
}
