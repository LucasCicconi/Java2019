package com.cubes2048.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/** 
    @version 1.1
    @see Classe para Exibir as informações na tela
 */

public class tile
{
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
    private Point irPara;
    private int x;
    private int y;
    
    private boolean animaçaoInicial = true;
    private double scaleFirst= 0.1;
    private BufferedImage imagemComeço;
    private boolean animaçaoCombinaçao ;
    private double scaleCombine = 1.2;
    private BufferedImage imagemCombinaçao;
    private boolean PodeCombinar = true;

    public tile(int value,int x, int y)
    {
        this.value = value;
        this.x = x;
        this.y = y;
        irPara = new Point(x,y);
        //como se fosse a caneta
        tileImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        imagemComeço = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        imagemCombinaçao = new BufferedImage(WIDTH * 2,HEIGHT *2,BufferedImage.TYPE_INT_ARGB);
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
            if(animaçaoInicial)
            {
                //Para o scale
                AffineTransform transform= new AffineTransform();
               //
                transform.translate(WIDTH / 2 - scaleFirst * WIDTH / 2 , HEIGHT / 2 - scaleFirst * HEIGHT / 2);
                transform.scale(scaleFirst,scaleFirst);
                Graphics2D g2d = (Graphics2D)imagemComeço.getGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setColor(new Color (0,0,0,0));
                g2d.fillRect(0,0,WIDTH,HEIGHT);
                g2d.drawImage(tileImage, transform,null);
                scaleFirst += 0.05;
                g2d.dispose();
                if(scaleFirst >= 1)animaçaoInicial = false;
            }
            else if(animaçaoCombinaçao)
            {
                AffineTransform transform= new AffineTransform();
                transform.translate(WIDTH / 2 - scaleCombine * WIDTH / 2 , HEIGHT / 2 - scaleCombine * HEIGHT / 2);
                transform.scale(scaleCombine,scaleCombine);
                Graphics2D g2d = (Graphics2D)imagemCombinaçao.getGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setColor(new Color (0,0,0,0));
                g2d.fillRect(0,0,WIDTH,HEIGHT);
                g2d.drawImage(tileImage, transform,null);
                scaleCombine -= 0.025;
                g2d.dispose();
                if(scaleCombine <= 1)animaçaoCombinaçao = false;
            
            
            }
        
        }
    
        public void render(Graphics2D g)
        {
            if(animaçaoInicial)
            {
                g.drawImage(imagemComeço,x,y,null);
            }
            else if(animaçaoCombinaçao)
            {
                g.drawImage(imagemCombinaçao,(int)(x + WIDTH/2 - scaleCombine * WIDTH),
                                             (int)(y + + HEIGHT/2 - scaleCombine * HEIGHT),null);
            }
            else
            {
                g.drawImage(tileImage,x,y,null);
            }
        }
        
    public int getValue()
    {
        return value;
    }
    
    public void setValue(int value)
    {
    this.value= value;
    drawImage();
    
    }
    
    public boolean PodeCombinar()
    {
        return PodeCombinar;
    }

    public void setPodeCombinar(boolean podeCombinar)
    {
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

    public boolean isAnimaçaoCombinaçao() {
        return animaçaoCombinaçao;
    }

    public void setAnimaçaoCombinaçao(boolean animaçaoCombinaçao) {
        this.animaçaoCombinaçao = animaçaoCombinaçao;
    }
 
    
}
