package br.com.fiap.zelourbano.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ZeloUrbano API - Global Solution 3SIR")
                        .version("v1")
                        .description("API REST para zeladoria colaborativa, acessibilidade urbana e gatilho automático de solicitações à prefeitura."));
    }
}

