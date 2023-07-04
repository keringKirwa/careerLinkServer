package com.career_link.kenya.config;

import com.career_link.kenya.services.api_service.EmployeeApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient OnCreateSafaricomWebClient(){
        return WebClient.builder()
                .baseUrl("https://dummy.restapiexample.com/api/v1")
                .build();

    }

    /**
     * The class  below provides an implementation  of  the  @GetExchange annotated interface, and the resulting instance in
     * saved in the IoC container.
     */
    @Bean
    public EmployeeApiService getStudentApiService(){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(OnCreateSafaricomWebClient()))
                        .build();
        return httpServiceProxyFactory.createClient(EmployeeApiService.class);
    }




}
