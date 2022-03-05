package com.pcz.thinking.in.spring.resource;

import com.pcz.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author picongzhi
 * @see org.springframework.core.io.FileSystemResourceLoader
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String path = "/" +
                System.getProperty("user.dir") +
                "/resource/src/main/java/com/pcz/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(path);

        String content = ResourceUtils.getContent(resource, "UTF-8");
        System.out.println(content);
    }
}
