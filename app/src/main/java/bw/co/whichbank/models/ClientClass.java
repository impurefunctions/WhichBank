package bw.co.whichbank.models;

/**
 * Created by Kesego on 11-Aug-17.
 Project : WhichBank
 */

public class ClientClass {
    public String fName,lName,occupation,employer,natureOfEmployment,contractOrPerm,location;int monthlyIncome;
    public String bank;boolean hasCreditCard;
   // public int incomeBand,MUE;
    public ClientClass(){}

    public ClientClass(String fName, String lName, String occupation, String employer, String natureOfEmployment, String contractOrPerm, String location, int monthlyIncome) {
        this.fName = fName;
        this.lName = lName;
        this.occupation = occupation;
        this.employer = employer;
        this.natureOfEmployment = natureOfEmployment;
        this.contractOrPerm = contractOrPerm;
        this.location = location;
        this.monthlyIncome = monthlyIncome;

    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public void setNatureOfEmployment(String natureOfEmployment) {
        this.natureOfEmployment = natureOfEmployment;
    }

    public void setContractOrPerm(String contractOrPerm) {
        this.contractOrPerm = contractOrPerm;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getfName() {

        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmployer() {
        return employer;
    }

    public String getNatureOfEmployment() {
        return natureOfEmployment;
    }

    public String getContractOrPerm() {
        return contractOrPerm;
    }

    public String getLocation() {
        return location;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

}
