package bw.co.whichbank.models;

import static bw.co.whichbank.util.Constants.currentClient;
import static bw.co.whichbank.util.Constants.loanAmount;
import static bw.co.whichbank.util.Constants.loanDuration;

/**
 * Created by Kesego on 13-Aug-17.
 */

public class Bank {
    public String bankName;
    public int  minimumSalaryGov;
    public int minimumSalaryPriv;
    public int minimumLoan;
    public int maxLoanGov;
    public int maxLoanPriv;
    public int massBandMin;
    public int personalBandMin;
    public int pretigeBandMin;
    public int premiumBandMin;
    public int maxTerm;
    public int minTerm;
    public double baseRate;
    public double rateNTB;
    public double rateETB;
    public boolean ETB;
    public boolean qualifyForLoan;
    public DSR dsr;
    public double dsrToUse;
    double EMI;
    int band1;
    int band2;
    int band3;
    int band4;
    int band5;
    int band6;
    int band7;
    int band8;
    int band9;

    int MUE1;
    int MUE2;
    int MUE3;
    int MUE4;
    int MUE5;
    int MUE6;
    int MUE7;
    int MUE8;
    int MUE9;



    /*MUE (maximum unsecured exposure): the maximum amount the bank is willing to lend you according to your income bracket
*/
    public int MUE;
    private int band;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public Bank(String bankName, int minimumSalaryGov, int minimumSalaryPriv, int minimumLoan, int maxLoanGov,
                int maxLoanPriv, int personalBandMin, int prestigeBandMin, int premiumBandMin,
                int maxTerm, int minTerm, double baseRate, double rateNTB, double rateETB) {
        this.bankName = bankName;
        this.minimumSalaryGov = minimumSalaryGov;
        this.minimumSalaryPriv = minimumSalaryPriv;
        this.minimumLoan = minimumLoan;
        this.maxLoanGov = maxLoanGov;
        this.maxLoanPriv = maxLoanPriv;
        massBandMin = minimumSalaryGov;
        this.personalBandMin = personalBandMin;
        this.pretigeBandMin = prestigeBandMin;
        this.premiumBandMin = premiumBandMin;
        this.maxTerm = maxTerm;
        this.minTerm = minTerm;
        this.baseRate = baseRate;
        this.rateNTB = rateNTB;
        this.rateETB = rateETB;
        ETB = currentClient.bank == bankName;


    }

    public boolean isQualifyForLoan(int salary) {
        getEMI(loanAmount,loanDuration);
        if (currentClient.hasCreditCard){
            dsrToUse = dsr.DSRCreditCard;

        }else{
            dsrToUse = dsr.DSRNoCreditCard;
        }
        double dsrInCash = dsrToUse * salary;
        qualifyForLoan = dsrInCash >= EMI;

        return qualifyForLoan;
    }
    public double getEMI(int loanAmount,int term){
        if(ETB){
            EMI = LoanPaymentCalculator.calculateMonthlyPayment(loanAmount,term,rateETB);
        }else{
            EMI = LoanPaymentCalculator.calculateMonthlyPayment(loanAmount,term,rateNTB);
        }

        return EMI;
    }

    public void setMUE(int MUE1, int MUE2, int MUE3, int MUE4, int MUE5, int MUE6, int MUE7, int MUE8, int MUE9) {
        this.MUE1 = MUE1;
        this.MUE2 = MUE2;
        this.MUE3 = MUE3;
        this.MUE4 = MUE4;
        this.MUE5 = MUE5;
        this.MUE6 = MUE6;
        this.MUE7 = MUE7;
        this.MUE8 = MUE8;
        this.MUE9 = MUE9;
    }

    public void setBands(int band1, int band2, int band3, int band4, int band5, int band6, int band7, int band8, int band9) {
        this.band1 = band1;
        this.band2 = band2;
        this.band3 = band3;
        this.band4 = band4;
        this.band5 = band5;
        this.band6 = band6;
        this.band7 = band7;
        this.band8 = band8;
        this.band9 = band9;
    }

    public int getIncomeBand(int salary) {
        int yourBand = 0;
        if (salary > band9){
            yourBand = band9;
        }else if (salary > band8){
            yourBand = band8;
        }else if (salary > band7){
            yourBand = band7;
        }else if (salary > band6){
            yourBand = band6;
        }else if (salary > band5){
            yourBand = band5;
        }else if (salary > band4){
            yourBand = band4;
        }else if (salary > band3){
            yourBand = band3;
        }else if (salary > band2){
            yourBand = band2;
        }else if (salary > band1){
            yourBand = band1;
        }
        band = yourBand;
        return yourBand;
    }

    public void setMUE(int salary) {
        int thisBand = getIncomeBand(salary);
        band = thisBand;

       if (band == band1 ){
           MUE = MUE1;
       }else if (band == band2 ){
           MUE = MUE2;
       }else if (band == band3 ){
           MUE = MUE3;
       }else if (band == band4 ){
           MUE = MUE4;
       }else if (band == band5 ){
           MUE = MUE5;
       }else if (band == band6 ){
           MUE = MUE6;
       }else if (band == band7 ){
           MUE = MUE7;
       }else if (band == band8 ){
           MUE = MUE8;
       }else if (band == band9 ){
           MUE = MUE9;
       }
    }
}
