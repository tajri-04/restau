import com.javatech.mail.dao.product.ProductDao;
import com.javatech.mail.dao.product.ProductRepositoryJdbc;
import com.javatech.service.product.ProductService;
import com.javatech.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.javatech.*"})
@EntityScan(basePackages = {"com.javatech.*"})
@EnableJpaRepositories(basePackages = {"com.javatech.*"})
public class HMSApplication {
    @Autowired
    ProductDao productDao;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    private static ProductService service;


    @Bean
    public ProductService productService2() {
        return new ProductServiceImpl(new ProductRepositoryJdbc(jdbcTemplate));
    }


    public static void main(String[] args){
        SpringApplication.run(HMSApplication.class);
        /*ApplicationContext ctx=new AnnotationConfigApplicationContext(HMSApplication.class);
        ProductService ProductService2=ctx.getBean("productService2",ProductService.class);
        ProductService ProductService3=ctx.getBean("productServiceImpl",ProductService.class);

        ProductService3.ajouterProduit(ProductDto.builder().nom("test23").prix(12).build());
        ProductService3.ajouterProduit(ProductDto.builder().nom("test23").prix(12).build());
        ProductService2.ajouterProduit(ProductDto.builder().nom("test2").prix(12).build());
*/
    }
}
