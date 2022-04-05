package uz.pdp.appcinemarestservice.config;
// Nurkulov Nodirbek 4/5/2022  9:01 AM

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
@Configuration
public class MailConfig {

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfig() {
        FreeMarkerConfigurationFactoryBean config = new FreeMarkerConfigurationFactoryBean();
        config.setTemplateLoaderPath("classpath:/templates");
        return config;
    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        return new JavaMailSender();
//    }
//    @Bean
//    public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
//        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
//        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/mail-templates");
//        configuration.setTemplateLoader(templateLoader);
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setConfiguration(configuration);
//        return freeMarkerConfigurer;
//    }
//
//    @Bean
//    public ResourceBundleMessageSource emailMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("mailMessages");
//        return messageSource;
//    }
//
//    @Bean
//    public SpringTemplateEngine freemarkerTemplateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
//        return templateEngine;
//    }
}
