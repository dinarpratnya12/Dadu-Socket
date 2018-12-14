/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Dinar
 */
public class Pemain {
    String username;
    int daduPilihan;

    public Pemain(String username) {
        this.username = username;
        daduPilihan = -1;
    }

   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDaduPilihan() {
        return daduPilihan;
    }

    public void setDaduPilihan(int daduPilihan) {
        this.daduPilihan = daduPilihan;
    }
    
    
    
}
