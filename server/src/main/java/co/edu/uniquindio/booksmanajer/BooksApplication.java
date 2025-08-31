package co.edu.uniquindio.booksmanajer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ComponentScan(basePackages = { "co.edu.uniquindio", "co.edu.uniquindio.repository" })
@EnableJpaRepositories(basePackages = { "co.edu.uniquindio" })
@EntityScan(basePackages = { "co.edu.uniquindio" })
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

	@GetMapping(path = "/")
	public String index() {
		return "Hola!";
	}
}
