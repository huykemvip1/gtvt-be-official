package com.example.gtvtbe.service.impl;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.entity.FileEntity;
import com.example.gtvtbe.model.response.FileResponse;
import com.example.gtvtbe.repository.FileRepository;
import com.example.gtvtbe.service.FileService;
import com.example.gtvtbe.util.StoreProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private  MessageSource messageSource;
    private final Path fileUpload;
    private final Long timCreated;

    private final FileRepository fileRepository;


    public FileServiceImpl(StoreProperties storeProperties, FileRepository fileRepository){
        this.fileRepository = fileRepository;
        fileUpload = Paths.get(storeProperties.getFileUpload());
        timCreated = System.currentTimeMillis();
        try{

            Files.createDirectories(fileUpload);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Result<FileResponse> uploadFile(MultipartFile file,String name) throws IOException {

        String fileName = String.valueOf(timCreated).concat(file.getOriginalFilename());
        String contentType = file.getContentType();
        Long size  = file.getSize();

        if(contentType.equals("application/png") ||
            contentType.equals("application/jpg") ||
         contentType.equals("application/jpeg")){
            fileUpload.resolve("images");
        }
        String path = fileUpload.toString();
        FileEntity entity = new FileEntity();
        if (contentType.split("/")[0].equals("image")) {
            name = name +".png";
        }else{
            name = name + "." + contentType.split("/")[1];
        }
        entity.setFileName(name);
        entity.setFilePath(fileUpload.toString());
        entity.setContentType(contentType);
        entity.setSize(size);
        System.out.println();
        var response = getFileResponse(fileRepository.save(entity));
        try{
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(fileUpload.resolve(name).toUri()));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return Result.ok(response);
    }

    @Override
    public InputStream downloadFile(String nameFile) throws Exception {
        var entity = fileRepository.getFileEntityByFileName(nameFile)
                .orElseThrow(() -> new Exception(messageSource.getMessage("file.not.found",new Object[]{nameFile},null)));
        try {
            String pathFile = Paths.get(entity.getFilePath()).resolve(entity.getFileName()).toString().replace("\\","/");
            InputStream inputStream = new FileInputStream(pathFile);
            return inputStream;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public FileResponse viewFile(String nameFile) throws Exception {
        return fileRepository.getFileEntityByFileName(nameFile)
                .map(this::getFileResponse)
                .orElseThrow(() -> new Exception(messageSource.getMessage("file.not.found",new Object[]{nameFile},null)));
    }

    private FileResponse getFileResponse(FileEntity entity){
        return new FileResponse(entity.getId(),
                entity.getFileName(),
                entity.getFilePath(),
                entity.getContentType(),
                entity.getSize());
    }
}
