/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import javax.swing.JFrame;
/**
 *
 * @author 18706986
 */
public class Start {
    
    public static void main(String[] args)
    {
        Jogo jogo = new Jogo();
        
        JFrame window = new JFrame("2048");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(jogo);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        jogo.start();
    }
        
}
    

