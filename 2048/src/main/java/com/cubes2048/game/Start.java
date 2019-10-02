package com.cubes2048.game;

import javax.swing.JFrame;

/** 
    @author Lucas Cicconi Ferreira  17121393 || Derek, Freire Quaresma  18706986
    @version 1.1
    @see Classe Principal do Jogo
 */

public class Start 
{
    
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
    

