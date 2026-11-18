package com.gossamercms.products.config;

import com.gossamercms.mvc.autoconfig.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@AutoConfiguration
@EnableModuleModels(basePackage = "com.gossamercms.products.models")
@EnableModuleConverters(basePackage = "com.gossamercms.products.converters")
@EnableModuleDbServices(basePackage = "com.gossamercms.products.data")
@EnableModuleHandlers(basePackage = "com.gossamercms.products.handlers")
@EnableModuleServices(basePackage = "com.gossamercms.products.services")
@EnableModuleProviders(basePackage = "com.gossamercms.products.providers")
@EnableModuleEventListeners(basePackage = "com.gossamercms.products.listeners")
public class ProductsAutoConfig {

    @PostConstruct
    public void init() {
        System.out.println("************************************************************");
        System.out.println(">>> ProductsAutoConfig LOADED");
        System.out.println("************************************************************");
    }

}
