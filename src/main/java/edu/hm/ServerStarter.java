/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm;

/**
 *
 * @author nicfel
 */
public class ServerStarter {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception Jetty throws this.
     */
    public static void main(String[] args) throws Exception {
        new JettyStarter().start();

    }

}
