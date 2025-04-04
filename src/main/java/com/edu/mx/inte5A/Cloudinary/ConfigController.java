package com.edu.mx.inte5A.Cloudinary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @GetMapping
    public String getCloudinaryConfig() {
        return "Cloudinary Cloud Name: "  + cloudName;
    }

}

