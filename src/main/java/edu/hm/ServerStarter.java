/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author nicfel
 */
public class ServerStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new JettyStarter().start();

    }

}
