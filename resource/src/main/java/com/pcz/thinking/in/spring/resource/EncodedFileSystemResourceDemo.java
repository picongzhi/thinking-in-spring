package com.pcz.thinking.in.spring.resource;

import com.pcz.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

/**
 * @author picongzhi
 * @see org.springframework.core.io.FileSystemResource
 * @see org.springframework.core.io.support.EncodedResource
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") +
                "/resource/src/main/java/com/pcz/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        File file = new File(path);
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        String content = ResourceUtils.getContent(fileSystemResource, "UTF-8");
        System.out.println(content);
    }
}
