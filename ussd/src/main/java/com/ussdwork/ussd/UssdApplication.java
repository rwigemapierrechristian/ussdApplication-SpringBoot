package com.ussdwork.ussd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ussdwork.ussd.model.User;
import com.ussdwork.ussd.repository.UserRepository;

@SpringBootApplication
public class UssdApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UssdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> listOfUsers = new ArrayList<User>();

		User user1 = new User("Bob", "0781111111", "1234", 80000.00, 8000.00);
		User user2 = new User("mary", "0782222222", "5678", 60000.00, 6000.00);
		User user3 = new User("jane", "0783333333", "9101", 40000.00, 4000.00);
		User user4 = new User("alan", "0784444444", "2345", 20000.00, 2000.00);

		listOfUsers.add(user1);
		listOfUsers.add(user2);
		listOfUsers.add(user3);
		listOfUsers.add(user4);

		userRepository.insert(listOfUsers);

	}

}
