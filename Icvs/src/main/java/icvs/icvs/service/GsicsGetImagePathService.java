package icvs.icvs.service;

import icvs.icvs.vo.InputIrParams;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
public class GsicsGetImagePathService {

    private final fileService fileService;

    public ResponseEntity<Resource> getIrImage(@Validated InputIrParams param) throws IOException {

        String path= fileService.createIrPath(param);

        String imgPath = fileService.getImgPath(path);

        System.out.println(imgPath);

        Resource resource = new FileSystemResource(imgPath);

        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", Files.probeContentType(Paths.get(imgPath)));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}
