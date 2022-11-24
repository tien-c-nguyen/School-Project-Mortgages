package cpsc2150.banking.views;

import cpsc2150.banking.controllers.IMortgageController;

import java.util.Scanner;

public class MortgageView implements IMortgageView{
    private Scanner input;
    private IMortgageController mC;

    public MortgageView(){
        input = new Scanner(System.in);
    }

    @Override
    public void setController(IMortgageController c) {
        mC = c;
    }

    @Override
    public double getHouseCost() {
        //asks for the house cost
        double cost;
        System.out.println("How much does the house cost?");
        cost = Double.parseDouble(input.nextLine());
        return cost;
    }

    @Override
    public double getDownPayment() {
        //asks for down payment
        double cost;
        System.out.println("How much is the down payment?");
        cost = Double.parseDouble(input.nextLine());
        return cost;
    }

    @Override
    public int getYears() {
        //asks for the number of years
        int years;
        System.out.println("How many years?");
        years = Integer.parseInt(input.nextLine());
        return years;
    }

    @Override
    public double getMonthlyDebt() {
        //asks for monthly debt payments
        double payments;
        System.out.println("How much are your monthly debt payments?");
        payments = Integer.parseInt(input.nextLine());
        return payments;
    }

    @Override
    public double getYearlyIncome() {
        //asks for the user's income
        double income;
        System.out.println("How much is your yearly income?");
        income = Double.parseDouble(input.nextLine());
        return income;
    }

    @Override
    public int getCreditScore() {
        //asks the user for credit score
        int credit;
        System.out.println("What is your credit score?");
        credit = Integer.parseInt(input.nextLine());
        return credit;
    }

    @Override
    public String getName() {
        //asks for name
        String name;
        System.out.println("What's your name?");
        name = input.nextLine();
        return name;
    }

    @Override
    public void printToUser(String s) {
        System.out.println(s);
    }

    @Override
    public void displayPayment(double p) {
        System.out.println(p);
    }

    @Override
    public void displayRate(double r) {
        System.out.println(r);
    }

    @Override
    public void displayApproved(boolean a) {
        System.out.println(a);
    }

    @Override
    public boolean getAnotherMortgage() {
        char userChar;
        //input validation for the right character
        do {
            System.out.println("Would you like to apply for another mortgage? Y/N");
            userChar = (input.nextLine()).charAt(0);
            userChar = Character.toUpperCase(userChar);
        }while(userChar != 'Y' && userChar != 'N');

        if(userChar == 'Y')
            return true;
        return false;
    }

    @Override
    public boolean getAnotherCustomer() {
        char userChar;
        //input validation for the right character
        do {
            System.out.println("Would you like to consider another customer? Y/N");
            userChar = (input.nextLine()).charAt(0);
            userChar = Character.toUpperCase(userChar);
        }while(userChar != 'Y' && userChar != 'N');

        if(userChar == 'Y')
            return true;
        return false;
    }
}
