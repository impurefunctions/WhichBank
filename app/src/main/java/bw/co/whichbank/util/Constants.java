package bw.co.whichbank.util;

import android.app.Activity;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import bw.co.whichbank.models.Bank;
import bw.co.whichbank.models.ClientClass;
import bw.co.whichbank.models.DSR;


/**
 * Created by dan on 3/19/16 at 1:12 AM in com.example.bottombar.sample
 */
public class Constants {
    public static ClientClass currentClient = new ClientClass();
    public static Resources res = null;
    public static Activity activity = null;
    public static List<Bank> bankList = new ArrayList<Bank>();
    public static List<Bank> qualifyingBanks = new ArrayList<>();
    public static int loanAmount = 0;
    public static int loanDuration = 0;


    public static void populateBanks(){
    //Populate the banks
        /*
         public Bank(String bankName, int minimumSalaryGov, int minimumSalaryPriv, int minimumLoan, int maxLoanGov,
        int maxLoanPriv, int massBandMin, int personalBandMin, int pretigeBandMin, int premiumBandMin,
        int maxTerm, int minTerm, double baseRate, double rateNTB, double rateETB)*/
        Bank bank = new Bank("Barclays", 2000, 3000, 50000, 450000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("FNBB", 2500, 3500, 45000, 45000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("Bank of Baroda", 5000, 5000, 5000, 50000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("Bank Gaborone", 2000, 3000, 50000, 450000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("NDB", 2000, 3000, 50000, 450000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("Standard Chartered Bank", 2000, 3000, 50000, 450000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

        bank =new Bank("Capital Bank", 2000, 3000, 50000, 450000, 450000, 3000, 10000, 30000, 6, 5, 0.15, 0.145, 0.14);
        bank.dsr = new DSR(0.5,0.45);
        bank.isQualifyForLoan(currentClient.getMonthlyIncome());
        bankList.add(bank);

}


    //TODO
    public static void populateQualifyingBanks() {
        for (Bank bank: bankList){
            if (bank.qualifyForLoan){

                qualifyingBanks.add(bank);
            }
        }

    }

}
