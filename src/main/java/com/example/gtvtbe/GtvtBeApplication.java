package com.example.gtvtbe;

import com.example.gtvtbe.enumeration.EnumRoles;
import com.example.gtvtbe.repository.RolesRepository;
import com.example.gtvtbe.security.domain.Roles;
import com.example.gtvtbe.util.StoreProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(StoreProperties.class)
public class GtvtBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GtvtBeApplication.class, args);
    }

//    private final RolesRepository rolesRepository;

//    public GtvtBeApplication(RolesRepository rolesRepository) {
//        this.rolesRepository = rolesRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
        // save roles information
//        Arrays.stream(EnumRoles.values())
//                .forEach(roles -> {
//                    try {
//                        Roles entity = new Roles();
//                        entity.setId(roles.getId());
//                        entity.setName(roles.getName());
//                        rolesRepository.save(entity);
//                    } catch (IllegalArgumentException ignored) {
//                        throw new RuntimeException(ignored.getMessage());
//                    }
//                });
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

}
