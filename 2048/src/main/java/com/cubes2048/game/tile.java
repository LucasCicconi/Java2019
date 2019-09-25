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
        if(value ==2)
        {
        background = new Color(0xe9e9e9);
        text= new Color(0x000000);
        }
        
        else if(value ==4) 
        {
          background = new Color(0xe6daab);
          text= new Color(0xffffff);
        }
        
        else if(value ==8)
        {
           background = new Color(0xf79d3d);
           text= new Color(0xffffff);
        }
        
         else if(value ==16) 
        {
          background = new Color(0xf28007);
          text= new Color(0xffffff);
        }
         
        else if(value ==32)
        {
           background = new Color(0xf55e3b);
           text= new Color(0xffffff);
        }
        
         else if(value ==64) 
        {
          background = new Color(0xff0000);
          text= new Color(0xffffff);
        }
         
        else if(value ==128)
        {
           background = new Color(0xe9de84);
           text= new Color(0xffffff);
        }
        
        else if(value ==256) 
        {
          background = new Color(0xf6e873);
          text= new Color(0xffffff);
        }
        
        else if(value ==512)
        {
           background = new Color(0xf5e455);
           text= new Color(0xffffff);
        }
        
        else if(value ==1024) 
        {
          background = new Color(0xf17e12c);
          text= new Color(0xffffff);
        }
        
        else if(value ==2048)
        {
           background = new Color(0xffe400);
           text= new Color(0xffffff);
        }
        else{
            background = Color.black;
            text= Color.white;
        }
        //desenha o retangulo do jogo na tela
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(background);
        g.fillRoundRect(0,0,WIDTH,HEIGHT,ARC_WIDTH,ARC_HEIGHT);
        
        g.setColor(text);
        //
        if(value<=64){
            font = Jogo.main.deriveFont(36f);
        }
        else{
            font= Jogo.main;
        }
            g.setFont(font);
            //pega o centro do eixo x da mensagem e move para o lado metade do tamanho da mensagem 
            int auxImpX = WIDTH / 2 -auxiliarImpressao.getMessageWidth(""+value,font,g)/2;
            //pega '''''''''''''''''y''''''''''''''''''''''''''cima''''''''''''''''''''''''''''''
            int auxImpY = HEIGHT / 2 + auxiliarImpressao.getMessageHeight(""+value, font, g)/2;//é + pq começa em baixo do lado esquerdo
            g.drawString(""+value,auxImpX,auxImpY);
            g.dispose();
         }
    
        public void update()
        {
        
        }
    
        public void render(Graphics2D g)
        {
            g.drawImage(tileImage,x,y,null);
            
        }
    public int getValue()
    {
        return value;
    }
}
