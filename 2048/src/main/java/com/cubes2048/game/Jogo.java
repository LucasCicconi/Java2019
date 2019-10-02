package com.cubes2048.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/** 
    @version 1.3
    @see Classe Principal do Jogo
 */

public class Jogo extends JPanel implements KeyListener,Runnable{

    private static final long serialVersionUID = 1L;
    
    //tamanho estático pro painel
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final Font main= new Font("Bebas Neue Regular",Font.PLAIN,28);
    //utilizando thread para aumentar fps e a animação correr livremente
    //antes e dps do prox procedimento (teclado)
    private Thread jogo;
    private boolean running;
    //redução de flick na imagem, toda imagem vai ser primeiro desenhada 
    //nessa "variavel" para dps ser usada como parametro no Jpainel
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private GameBoard board;
   
    private long TempoInicial;
    private long elapsed;
    private boolean set;

    public Jogo()
    {
        //liberar input do teclado
        setFocusable(true);
        //tamanho do frame
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //scanf na tecla
        addKeyListener(this);
        
        board = new GameBoard(WIDTH / 2 - GameBoard.BOARD_WIDTH/2,HEIGHT - GameBoard.BOARD_HEIGHT - 10);
    }
    
    
    private void update()
    {
        board.update();
        teclado.update();
      
    }
    
    private void render()
    {
        //Desenha um retangulo branco na tela
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        //render board
        board.render(g);
        g.dispose();
        
        //desenha o Jpainel onde vão ficar os quadradinhos
        Graphics2D g2d = (Graphics2D)getGraphics();
        g2d.drawImage(image, 0, 0,null);
        g2d.dispose();
    }
    
   

    @Override
    public void run() 
    {
        
        int fps=0,updates =0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerUpdate =1000000000.0/60; //1s
        
        //last updade time in nanoseconds
        double then =System.nanoTime();
        //quantos ipdates são precisos para n deixar nd pra traz no render
        double unprocessed = 0;
        
        while(running)
        {
         boolean shouldRender = false;  
         //contagem de qnts upgrades tem que fazer pelo tempo ja passado
            double now = System.nanoTime();
            unprocessed += (now - then)/nsPerUpdate;
            then = now;//resseta o 
        //updadte queue
 
            while(unprocessed>=1)
            {
            updates++;
            update();
            unprocessed --;
            shouldRender =true;
            }
        //render
            if(shouldRender)
            {
            fps++;
            render();
            shouldRender = false;
            }
            else 
            {
                try
                    {
                        Thread.sleep(1);
                    }
                 catch(Exception e)
                    {
                        //se der algum erro, exibir no console
                        e.printStackTrace();
                    }
            }
        }

        //FPS Timer
        if(System.currentTimeMillis()- fpsTimer >1000) //maior q 1s
        {
            System.out.printf("%d fps %d updates",fps,updates);
            System.out.println();
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }
    }
    
    
    public synchronized void start()
    {
        if(running)return;
        running = true;
        jogo = new Thread(this,"jogo");
        jogo.start();    
    }
    
    public synchronized void stop()
    {
        if(!running)return; 
        running = false;
        System.exit(0);
    }
    
    
     @Override
    public void keyTyped(KeyEvent e)
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        teclado.KeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        teclado.KeyReleased(e);
    }
    
    
}
