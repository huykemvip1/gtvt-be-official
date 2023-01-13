package com.example.gtvtbe.service;

import com.example.gtvtbe.common.Result;
import com.example.gtvtbe.model.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    Result<FileResponse> uploadFile(MultipartFile file,String name) throws IOException;

    InputStream downloadFile(String nameFile) throws Exception;

    FileResponse viewFile(String nameFile) throws Exception;
}
