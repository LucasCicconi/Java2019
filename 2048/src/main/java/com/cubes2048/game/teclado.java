/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import java.awt.event.KeyEvent;

/**
 *
 * @author 18706986
 */
public class teclado {
    
    public static boolean[] pressed =new boolean[256];
    public static boolean[] anterior = new boolean[256];
    
    private teclado()
    {
    }
    public static void update()
    {
    for( int i=0;i<4;i++)
        {
         if(i==0)anterior[KeyEvent.VK_LEFT] =pressed[KeyEvent.VK_LEFT];
         if(i==1)anterior[KeyEvent.VK_RIGHT] =pressed[KeyEvent.VK_RIGHT];
         if(i==2)anterior[KeyEvent.VK_UP] =pressed[KeyEvent.VK_UP];
         if(i==3)anterior[KeyEvent.VK_DOWN] =pressed[KeyEvent.VK_DOWN];
        }
    }
    public static void KeyPressed(KeyEvent e)
    {
        pressed[e.getKeyCode()] = true;
        
    }
    public static void KeyReleased(KeyEvent e)
    {
        pressed[e.getKeyCode()] = false;
    }
    
    public static boolean typed(int KeyEvent){
        //Rever video 4 6:28.
        return !pressed[KeyEvent] && anterior[KeyEvent];
    
    }
    
 
    
