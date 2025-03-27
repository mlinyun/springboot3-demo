package com.mlinyun.springboot3demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    // 使用配置属性注入路径，便于在不同环境中灵活配置
    @Value("${file.upload.path:./src/main/resources/upload/}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("success", false);
            response.put("message", "上传失败，请选择文件");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // 确保上传目录存在
            File uploadDir = new File(fileUploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 获取文件名和后缀
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : "";

            // 生成唯一文件名 (使用更安全的UUID + 时间戳方式)
            String newFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                    + "_" + UUID.randomUUID().toString().substring(0, 8) + suffixName;

            // 保存文件
            Path targetPath = Paths.get(fileUploadPath, newFileName);
            Files.write(targetPath, file.getBytes());

            // 返回成功信息
            response.put("success", true);
            response.put("message", "上传成功");
            response.put("fileName", newFileName);
            response.put("filePath", "/files/" + newFileName);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "上传失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}