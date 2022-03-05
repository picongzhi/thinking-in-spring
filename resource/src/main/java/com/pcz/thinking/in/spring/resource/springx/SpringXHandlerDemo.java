package com.pcz.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author picongzhi
 */
public class SpringXHandlerDemo {
    public static void main(String[] args) throws IOException {
        // -Djava.protocol.handler.pkgs=com.pcz.thinking.in.spring.resource
        URL url = new URL("springx:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }
}
