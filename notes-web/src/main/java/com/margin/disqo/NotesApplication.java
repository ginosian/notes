package com.margin.disqo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ImportResource("classpath:margin-disqo-web-context.xml")
@PropertySources({
        @PropertySource("classpath:application-facade.properties")
})
public class NotesApplication {

    protected NotesApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(NotesApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}
