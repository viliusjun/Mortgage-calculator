/*
Calculator, Linear and Annuity class for storing the main data and calculating
the linear and annuity formula
 */

package MortgageCalculator;

public class Calculator{
    protected double amount;
    protected int years;
    protected int months;
    protected double percentage;


    public Calculator(double amount, int years, int months, double percentage){
        this.amount = amount;
        this.years = years;
        this.months = months;
        this.percentage = percentage;
    }
}

class Linear extends Calculator{
    public Linear(double amount, int years, int months, double percentage){
        super(amount, years, months, percentage);
    }


    public double[] linearCalculations(){
        double leftToPay = amount;
        double monthlyPayment;
        double sum = 0;
        double[] monthlyPayments = new double[years*12 + months];

        for (int i = 0; i < years*12 + months; ++i){
            monthlyPayment = amount / (years * 12 + months) + leftToPay * (percentage / 12 / 100);
            leftToPay -= amount / (years*12 + months);
            sum += monthlyPayment;
            monthlyPayments[i] = monthlyPayment;
        }

        return monthlyPayments;
    }
}

class Annuity extends Calculator{
     double amount;
     int years;
     int months;
     double percentage;

    public Annuity(double amount, int years, int months, double percentage) {
        super(amount, years, months, percentage);
        this.amount = amount;
        this.years = years;
        this.months = months;
        this.percentage = percentage;
    }

    public double[] annuityCalculations(){
        double monthlyPayment;
        double sum = 0;
        double[] monthlyPayments = new double[years*12 + months];

        for (int i = 0; i < years*12 + months; ++i){
            monthlyPayment = ( (percentage/12/100) * Math.pow((1 + (percentage/12/100)), (years*12 + months)) )
                    / ( Math.pow((1 + (percentage/12/100)), (years*12 + months)) - 1 );
            monthlyPayment *= amount;
            sum += monthlyPayment;
            monthlyPayments[i] = monthlyPayment;
        }

        return monthlyPayments;
    }
}