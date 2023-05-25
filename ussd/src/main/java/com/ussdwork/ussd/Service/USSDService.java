package com.ussdwork.ussd.Service;

import com.ussdwork.ussd.repository.*;
import com.ussdwork.ussd.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class USSDService {
    @Autowired
    UserRepository userRepository;

    public USSDService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String processUSSDRequest(String ussdCode, String input) {
        Scanner userInput1 = new Scanner(System.in);
        System.out.println("Enter a valid USSD code please: ");
        ussdCode = userInput1.nextLine();
        // Check the USSD code

        if (ussdCode.equals("*111#")) {
            return processMainMenu(input);
        } else if (ussdCode.equals("*131#")) {
            return processAirtimeBalance(input);
        } else {
            return "Invalid USSD code";
        }
    }

    private String processMainMenu(String input) {
        // Display main menu
        System.out.println("Main Menu:\n1. Send Money\n2. Buy Airtime\n3. Check Balance");
        Scanner menuInput = new Scanner(System.in);
        input = menuInput.nextLine();
        switch (input) {
            case "1":
                return processSendMoney(input, input, input);
            case "2":
                return processBuyAirtime(input, input, input, input);
            case "3":
                return processCheckBalance(input);
            default:
                return "Invalid option";
        }
    }

    private String processSendMoney(String phoneNumber, String amount, String pin) {
        Scanner userInput2 = new Scanner(System.in);
        Scanner userInput3 = new Scanner(System.in);
        Scanner userInput4 = new Scanner(System.in);
        User sender = userRepository.findByPhoneNumber("bob");

        System.out.println("Enter recipient's phone number");
        phoneNumber = userInput2.nextLine();
        User recipient = userRepository.findByPhoneNumber(phoneNumber);
        if (recipient != null) {
            System.out.println("Enter the amount to send to " + recipient.getName() + ": ");
            double balAmount = Double.parseDouble(amount);
            balAmount = userInput3.nextDouble();
            System.out.print("Enter your PIN to confirm: ");
            pin = userInput4.nextLine();
            if (pin.equals(sender.getPin())) {
                if (sender.getBalance() >= balAmount) {
                    sender.setBalance(sender.getBalance() - balAmount);
                    recipient.setBalance(recipient.getBalance() + balAmount);
                    return "money sent succesfully to" + recipient.getName();
                }

                else {
                    return "You do not have enough funds to complete this transaction";
                }

            } else {
                return "Invalid PIN";
            }

        } else

        {
            return "No match found for this number";
        }

    }

    private String processCheckBalance(String pin) {
        User user = userRepository.findByPhoneNumber("bob");

        if (!pin.equals(user.getPin())) {
            return "Invalid PIN";
        }

        return "Your balance is: " + user.getBalance();
    }

    private String processAirtimeBalance(String pin) {
        User user = userRepository.findByPhoneNumber("bob");
        Scanner userInput5 = new Scanner(System.in);
        System.out.println("Enter your pin please");
        pin = userInput5.nextLine();
        if (!pin.equals(user.getPin())) {
            return "Invalid PIN";
        }

        return "Your airtime balance is: " + user.getAirtimeBalance();
    }

    private String processBuyAirtime(String option, String amount, String phoneNumber, String pin) {
        User user = userRepository.findByPhoneNumber("bob");
        Scanner userInput6 = new Scanner(System.in);

        Scanner userInput7 = new Scanner(System.in);

        double airtimeAmount = Double.parseDouble(amount);

        System.out.println("Enter your pin please");
        pin = userInput6.nextLine();
        if (pin.equals(user.getPin())) {
            System.out.println("Enter the amount of airtime you would like to purchase:");
            airtimeAmount = userInput7.nextDouble();
            if (airtimeAmount < user.getBalance()) {
                // Deduct amount from user's balance and add to airtime balance
                user.setBalance(user.getBalance() - airtimeAmount);
                user.setAirtimeBalance(user.getAirtimeBalance() + airtimeAmount);
                userRepository.save(user);
                return "Airtime purchased successfully";
            } else {
                return "You do not have sufficient funds to undertake this transaction";
            }
        } else {
            return "Invalid PIN";
        }

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(User change) {
        return userRepository.save(change);
    }
}
