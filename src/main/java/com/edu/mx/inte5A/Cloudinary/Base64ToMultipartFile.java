package com.edu.mx.inte5A.Cloudinary;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class Base64ToMultipartFile implements MultipartFile {
    private final byte[] imgContent;
    private final String header;

    public Base64ToMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    public static MultipartFile convert(String base64){
        try{
            String [] parts = base64.split("=");
            byte[] imagesBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(parts[1]);
            return new Base64ToMultipartFile(imagesBytes, parts[0]);

        } catch (Exception e) {
            throw new IllegalArgumentException("Formato base 64 invalido");
        }
    }

    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + " . " + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis() + (int) (Math.random() * 10000) + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        return FileOutputStream(dest).write(imgContent);
    }

}
