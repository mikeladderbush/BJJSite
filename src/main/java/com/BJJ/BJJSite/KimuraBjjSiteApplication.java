package com.BJJ.BJJSite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.UserFactory;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KimuraBjjSiteApplication implements CommandLineRunner {

	@Autowired
	private UserFactory userFactory;

	public static void main(String[] args) {
		SpringApplication.run(KimuraBjjSiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> demoUser = userFactory.createUser(builder -> {
			builder.fullName("Mike Ladderbush")
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
