package com.quemsi.commons.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResource implements MultipartFile {
    private String dir;
    private String name;
    private String originalFilename;
    private String contentType;
    private boolean empty;
    private long size;
    private byte[] data;


    @Override
    public byte[] getBytes() throws IOException {
        return data;
    }

    @Override
    public InputStream getInputStream(){
        return new ByteArrayInputStream(data);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
}
