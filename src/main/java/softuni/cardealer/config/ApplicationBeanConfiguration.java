package softuni.cardealer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.cardealer.utils.ValidationUtil;
import softuni.cardealer.utils.ValidationUtilImpl;
import softuni.cardealer.utils.XmlParser;
import softuni.cardealer.utils.XmlParserImpl;

import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
    @Bean
    public Random random(){
        return new Random();
    }
}
