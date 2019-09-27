package com.cubes2048.game;

import java.awt.event.KeyEvent;

/** 
    @version 1.1
    @see Classe para pegar a tecla pressionada pelo usuario
 */

public class teclado {
    
    public static boolean[] pressed =new boolean[256];
    public static boolean[] anterior = new boolean[256];
    
    private teclado(){
    }

    public static void update(){

    for( int i=0;i<4;i++){
         if(i==0)   anterior[KeyEvent.VK_LEFT]  = pressed[KeyEvent.VK_LEFT];
         if(i==1)   anterior[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
         if(i==2)   anterior[KeyEvent.VK_UP]    = pressed[KeyEvent.VK_UP];
         if(i==3)   anterior[KeyEvent.VK_DOWN]  = pressed[KeyEvent.VK_DOWN];
        }
    }
    
    public static void KeyPressed(KeyEvent e){
        pressed[e.getKeyCode()] = true; 
    }

    public static void KeyReleased(KeyEvent e){
        pressed[e.getKeyCode()] = false;
    }
    
    public static boolean typed(int KeyEvent){
    return !pressed[KeyEvent] && anterior[KeyEvent];
    }
}
    
 
    
