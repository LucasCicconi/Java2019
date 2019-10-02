package com.cubes2048.game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/** 
    @version 1.1
    @see Formatação para centralizar os numeros nos quadrados
 */

public class auxiliarImpressao
{
 
    private auxiliarImpressao(){};
    
    //Para centralizar as coisas no quadradin
    public static int getMessageWidth(String message,Font font,Graphics2D g)
    {
   g.setFont(font);
   Rectangle2D bounds = g.getFontMetrics().getStringBounds(message,g);
   return(int)bounds.getWidth();
    }
    //
    public static int getMessageHeight(String message,Font font,Graphics2D g)
    {
   g.setFont(font);
   if(message.length()==0) return 0;
   TextLayout tl = new TextLayout(message,font,g.getFontRenderContext());
   return(int)tl.getBounds().getHeight();
    }
}
