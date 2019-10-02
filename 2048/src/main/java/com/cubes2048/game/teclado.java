package com.cubes2048.game;

import java.awt.event.KeyEvent;

/** 
    @version 1.1
    @see Classe para pegar a tecla pressionada pelo usuario
 */

public class teclado {
    
    public static boolean[] pressed =new boolean[256];
    public static boolean[] anterior = new boolean[256];
    
    private teclado()
    {
    }

    public static void update()
    {

    for( int i=0;i<8;i++)
        {
         if(i==0)   anterior[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
         if(i==1)   anterior[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
         if(i==2)   anterior[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
         if(i==3)   anterior[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
         if(i==4)   anterior[KeyEvent.VK_A]  = pressed[KeyEvent.VK_A];
         if(i==5)   anterior[KeyEvent.VK_D] = pressed[KeyEvent.VK_D];
         if(i==6)   anterior[KeyEvent.VK_W]    = pressed[KeyEvent.VK_W];
         if(i==7)   anterior[KeyEvent.VK_S]  = pressed[KeyEvent.VK_S];
         if(i==8)   anterior[KeyEvent.VK_X]  = pressed[KeyEvent.Vk_X];
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
    
    public static boolean typed(int KeyEvent)
    {
       
        return !pressed[KeyEvent] && anterior[KeyEvent];
    
    }
}
    
 
    
