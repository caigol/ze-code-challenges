package br.com.caique.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.caique.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 message")
                                        .build()
                                , new ResponseMessageBuilder()
                                        .code(400)
                                        .message("400 Bad Request")
                                        .build()
                                , new ResponseMessageBuilder()
                                        .code(404)
                                        .message("404 Not Found")
                                        .build()
                                , new ResponseMessageBuilder()
                                        .code(200)
                                        .message("200 Ok")
                                        .build()))
                .apiInfo(new ApiInfoBuilder()
                        .title("Ze Code Challenges REST API")
                        .version("1.0.0")
                        .build());
    }
}
