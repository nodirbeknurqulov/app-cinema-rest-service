package uz.pdp.appcinemarestservice.config;
// Nurkulov Nodirbek 4/5/2022  9:01 AM

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
}
