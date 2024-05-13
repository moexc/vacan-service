package cn.moexc.vcs.web;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.infrasture.oss.ObjectStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final ObjectStorageService objectStorageService;

    public FileController(ObjectStorageService objectStorageService) {
        this.objectStorageService = objectStorageService;
    }

    @PostMapping
    public R save(@RequestParam("file") MultipartFile file){
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try (InputStream photoStream = file.getInputStream()){
            return R.success(objectStorageService.write(fileName, photoStream));
        }catch (Exception e){
            e.printStackTrace();
            return R.error("上传失败");
        }
    }
}
