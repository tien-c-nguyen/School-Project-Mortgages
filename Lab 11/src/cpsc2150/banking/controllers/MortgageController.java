package cpsc2150.banking.controllers;

import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.ICustomer;
import cpsc2150.banking.models.IMortgage;
import cpsc2150.banking.models.Mortgage;
import cpsc2150.banking.views.IMortgageView;

public class MortgageController implements IMortgageController{
    private IMortgageView view;

    public MortgageController(IMortgageView v) {
        view = v;
    }

    @Override
    public void submitApplication() {
        //loop for separate loan applicants
        do{
            //notifies the view to ask for name
            String name;
            IMortgage mortgage;
            name = view.getName();

            //notifies the view to ask for income
            double income;
            income = view.getYearlyIncome();
            while (income <= 0) {
                System.out.println("Income must be greater than 0.");
                income = view.getYearlyIncome();
            }

            //notifies the view to ask the user for the debt payment amount
            double debt;
            debt = view.getMonthlyDebt();
            while (debt < 0){
                System.out.println("Debt must be greater than or equal to 0.");
                debt = view.getMonthlyDebt();
            }

            //notifies view to ask the user for their credit score
            int credit;
            int maxCredit = 850;
            credit = view.getCreditScore();
            while (credit <= 0 || credit > maxCredit){
                System.out.println("Credit Score must be greater than 0 and less than " + maxCredit);
                credit = view.getCreditScore();
            }
            //loops for different loans
            do {
                //notifies the view to ask for the cost of the house
                double house;
                house = view.getHouseCost();
                while (house <= 0){
                    System.out.println("Cost must be greater than 0.");
                    house = view.getHouseCost();
                }

                //notifies the view to ask for cost of the down payment
                double downPay;
                downPay = view.getDownPayment();
                while (downPay <= 0 || downPay > house){
                    System.out.println("Down Payment must be greater than 0 and less than the cost of the house.");
                    downPay = view.getDownPayment();
                }

                //notifies the view to ask for the number of years
                int years;
                years = view.getYears();
                while (years <= 0){
                    System.out.println("Years must be greater than 0.");
                    years = view.getYears();
                }
                //creates a new customer object
                ICustomer c = new Customer(debt, income, credit, name);
                c.applyForLoan(downPay, house, years);
                System.out.println(c.toString());
            } while (view.getAnotherMortgage());
        }while(view.getAnotherCustomer());
    }
}
