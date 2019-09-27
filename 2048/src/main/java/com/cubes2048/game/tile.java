package com.cubes2048.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/** 
    @version 1.1
    @see Classe para Exibir as informações na tela
 */

public class tile { //public porque a classe do tabuleiro precisa saber essas variaveis

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
    private Point irPara;
    private int x;
    private int y;
    
    private boolean PodeCombinar = true;

    public tile(int value,int x, int y){
        this.value = value;
        this.x = x;
        this.y = y;
        irPara = new Point(x,y);
        //como se fosse a caneta
        tileImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        //vai desenhar no tile da imagem o plano de fundo e um numero
        drawImage();
    }

    private void drawImage(){   // como se fosse a caneta

        Graphics2D g = (Graphics2D) tileImage.getGraphics();

        if(value ==2){  // setar varias cores e checar
        background = new Color(0xe9e9e9);
        text= new Color(0x000000);
        }
        
        else if(value ==4){ //cada numero tem sua propria cor pré determinada
          background = new Color(0xe6daab);
          text= new Color(0xffffff);
        }
        
        else if(value ==8){
           background = new Color(0xf79d3d);
           text= new Color(0xffffff);
        }
        
         else if(value ==16){
          background = new Color(0xf28007);
          text= new Color(0xffffff);
        }
         
        else if(value ==32){
           background = new Color(0xf55e3b);
           text= new Color(0xffffff);
        }
        
         else if(value ==64){
          background = new Color(0xff0000);
          text= new Color(0xffffff);
        }
         
        else if(value ==128){
           background = new Color(0xe9de84);
           text= new Color(0xffffff);
        }
        
        else if(value ==256){
          background = new Color(0xf6e873);
          text= new Color(0xffffff);
        }
        
        else if(value ==512){
           background = new Color(0xf5e455);
           text= new Color(0xffffff);
        }
        
        else if(value ==1024){
          background = new Color(0xf17e12c);
          text= new Color(0xffffff);
        }
        
        else if(value ==2048){
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

        
        if(value<=64){  //
            font = Jogo.main.deriveFont(36f);
        }

        else{
            font= Jogo.main;
        }

            g.setFont(font);
            int auxImpX = WIDTH / 2 -auxiliarImpressao.getMessageWidth(""+value,font,g)/2;//pega o centro do eixo x da mensagem e move para o lado metade do tamanho da mensagem 
            int auxImpY = HEIGHT / 2 + auxiliarImpressao.getMessageHeight(""+value, font, g)/2; //pega o centro do eixo y da mensagem e move para cima da metade do tamanho da mensagem

            g.drawString(""+value,auxImpX,auxImpY); //é + pq começa em baixo do lado esquerdo
            g.dispose();
         }
    
        public void update(){
    
        }
    
        public void render(Graphics2D g){
            g.drawImage(tileImage,x,y,null);
        }

    public int getValue(){
        return value;
    }
    
    public void setValue(int value){
    this.value= value;
    drawImage();
    }
    
    public boolean PodeCombinar(){
        return PodeCombinar;
    }

    public void setPodeCombinar(boolean podeCombinar){
        this.PodeCombinar = podeCombinar;
    }

    public Point getIrPara() {
        return irPara;
    }

    public void setIrPara(Point irPara) {
        this.irPara = irPara;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
 
}
