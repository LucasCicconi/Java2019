/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author 18706986
 */
public class tile {
    //public porque a classe do tabuleiro precisa saber essas variaveis
    public static final int WIDTH = 80;   
    public static final int HEIGHT = 80;
    public static final int SLIDE_SPEED = 20;
   
    public static final int ARC_WIDTH=15;
    public static final int ARC_HEIGHT=15;
    
    private int value;
    private BufferedImage tileImage;
    private Color background;
    private Color text;
    private Font font;
    private int x;
    private int y;
    
    public tile(int value,int x, int y)
    {
        this.value = value;
        this.x = x;
        this.y = y;
        //como se fosse a caneta
        tileImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        //vai desenhar no tile da imagem o plano de fundo e um numero
        drawImage();
    }
    private void drawImage()
    {
        // como se fosse a caneta
        Graphics2D g = (Graphics2D) tileImage.getGraphics();
        // setar varias cores e checar
                if(value ==2){
            background = new Color(0xe9e9e9);
            text= new Color(0x000000);
        }
        else if 
        {
                    
        }
    }
    
    
}
