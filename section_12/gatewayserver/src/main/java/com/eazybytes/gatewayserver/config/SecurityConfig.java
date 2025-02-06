package com.eazybytes.gatewayserver.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        serverHttpSecurity.authorizeExchange(exchnages-> exchnages
                .pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers("/eazybank/accounts/**").authenticated()
                .pathMatchers("/eazybank/loans/**").authenticated()
                .pathMatchers("/eazybank/cards/**").authenticated())
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(Customizer.withDefaults()));

        serverHttpSecurity.csrf(csrf->csrf.disable());
        return serverHttpSecurity.build();
    }

}
