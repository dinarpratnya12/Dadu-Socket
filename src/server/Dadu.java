/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Random;

/**
 *
 * @author Dinar
 */
public class Dadu {
    int jumlah;
    
    public Dadu(){
        int max = 6;
        int min = 1;
        Random random = new Random();
        this.jumlah = random.nextInt(max - min + 1) + min;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    
}
