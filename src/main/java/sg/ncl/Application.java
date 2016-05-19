package sg.ncl;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
	public static String ROOT = "uploaded-dataset-dir";
	public static String EXP_CONFIG_DIR = "uploaded-exp-config";
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(ROOT).mkdir();
            new File(EXP_CONFIG_DIR).mkdir();
        };
    }
}
