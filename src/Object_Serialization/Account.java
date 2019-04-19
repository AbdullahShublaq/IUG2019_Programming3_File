/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object_Serialization;

import java.io.Serializable;

/**
 *
 * @author Abdulah Shublaq
 */
public class Account implements Serializable{

    private String AccNo;
    private String AccName;
    private String AccBalance;

    public Account(String AccNo, String AccName, String AccBalance) {
        this.AccNo = AccNo;
        this.AccName = AccName;
        this.AccBalance = AccBalance;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String AccNo) {
        this.AccNo = AccNo;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String AccName) {
        this.AccName = AccName;
    }

    public String getAccBalance() {
        return AccBalance;
    }

    public void setAccBalance(String AccBalance) {
        this.AccBalance = AccBalance;
    }

}
