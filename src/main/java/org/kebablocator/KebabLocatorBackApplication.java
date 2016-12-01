package org.kebablocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KebabLocatorBackApplication {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("python3 rollback.py &");
			System.out.println("Le script de rollback a bien été démarré");
		} catch (Exception e) {
			System.out.println("Le script de rollback n'a pas pu être démarré");
		}
		SpringApplication.run(KebabLocatorBackApplication.class, args);
	}
}
