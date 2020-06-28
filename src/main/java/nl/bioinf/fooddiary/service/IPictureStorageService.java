package nl.bioinf.fooddiary.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
/**
 @Author Tobias Ham
 */
public interface IPictureStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
