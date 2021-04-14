package cmtyMgmtSys;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(ApplicationConfig.class);// container
        System.out.println("Hello, Hibernate!");
    }
}
