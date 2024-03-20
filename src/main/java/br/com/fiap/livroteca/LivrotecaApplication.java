package br.com.fiap.livroteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class LivrotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrotecaApplication.class, args);
	}

	@GetMapping
	public String home(){
		return "index";
	}

}
