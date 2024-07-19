package com.BJJ.BJJSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.UserFactory;

@SpringBootApplication
public class KimuraBjjSiteApplication implements CommandLineRunner {

	@Autowired
	private UserFactory userFactory;

	public static void main(String[] args) {
		SpringApplication.run(KimuraBjjSiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User demoUser = userFactory.createUser(builder -> {
			builder.firstName("Mike")
					.lastName("Ladderbush")
					.username("mikeladderbush")
					.password("mike")
					.email("mikeladderbush@gmail.com")
					.phone("9787708430")
					.address("boston")
					.sex("male")
					.dob("2241997")
					.paymentOptions(null);
		});
		System.out.println("Demo User: " + demoUser);
	}
}
