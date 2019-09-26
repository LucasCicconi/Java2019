/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes2048.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author 18706986
 */
public class GameBoard {
    
    public static final int ROWS = 4;
    public static final int COLS = 4;
    
    private final int startingTiles = 2;
    private tile[][] board;
    private boolean dead;
    private boolean won;
    private BufferedImage gameBoard;
    private BufferedImage finalBoard;
    private int x;
    private int y;
    
    private static int SPACING = 10;
    public static int BOARD_WIDTH = (COLS +1)* SPACING +COLS*tile.WIDTH;
    public static int BOARD_HEIGHT =(ROWS+1)*SPACING+ROWS*tile.HEIGHT;
    
    private boolean hasStarted;
 
    public GameBoard(int x, int y)
    {
        this.x = x;
        this.y = y;
        board = new tile[ROWS][COLS];
        gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        createBoardImage(); 
        start();
        
    }
    
    private void createBoardImage(){
        Graphics2D g = (Graphics2D)gameBoard.getGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        g.setColor(Color.lightGray);
        
        for(int row=0;row<ROWS;row++)
        {
            
            for(int col = 0; col<COLS;col++)
            {
                int x= SPACING + SPACING * col +tile.WIDTH * col;
                int y= SPACING + SPACING * row +tile.HEIGHT * row;
                g.fillRoundRect(x,y,tile.WIDTH,tile.HEIGHT,tile.ARC_WIDTH,tile.ARC_HEIGHT);
                
                
            }
        }
    }
    private void start()
    {

        for(int i=0;i< startingTiles; i++)
        {
            spawnRandom();
        }
        
    }   
    private void spawnRandom()
    {
     Random random = new Random();
     boolean notValid = true;
        
     while(notValid)
     {
         int location = random.nextInt(ROWS * COLS);
         int row = location/ROWS;
         int col= location % COLS;
         tile current = board[row][col];
         if(current == null)
         {
             int value = random.nextInt(10)< 9 ? 2:4;
             tile tile = new tile(value, getTileX(col),getTileY(row));
             board[row][col] = tile;
             notValid = false;
         }
     }
        
    }
    
        public int getTileX(int col)
        {
        return SPACING + col* tile.WIDTH + col*SPACING;
        }
        
        public int getTileY(int row)
        {
        return SPACING + row* tile.WIDTH + row*SPACING;
        }
        
            

    public void render (Graphics2D g) 
    {
        Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
        g2d.drawImage(gameBoard,0,0,null);
        
        //draw tiles
        for(int row = 0; row<ROWS;row++)
        {
            for(int col=0;col < COLS;col++)
            {
                tile current = board[row][col];
                if(current == null)continue;
                current.render(g2d);
            }
            
        }
        
        g.drawImage(finalBoard,x,150,null);
        g2d.dispose();
        
    }
    public void update()
    {
        checkKeys();
        
        for(int row = 0; row<ROWS;row++)
        {
            for(int col=0;col<COLS;col++)
            {
                tile current= board[row][col];
                if(current == null)continue;
                current.update();
                //reseta a posiçao
                if(current.getValue() == 2048)
                {
                won = true;
                }
            }
        }
    }
    
    private void checkKeys()
    {
        if(teclado.typed(KeyEvent.VK_LEFT))
        {
        //manda os quadrados para a esquerda
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_RIGHT))
        {
        //manda os quadrados para a direita
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_UP))
        {
        //manda os quadrados para cima
            if(!hasStarted)hasStarted = true;
        }
        if(teclado.typed(KeyEvent.VK_DOWN))
        {
        //manda os quadrados para baixo
            if(!hasStarted)hasStarted = true;
        }
        
    }
}

