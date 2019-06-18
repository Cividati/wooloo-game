/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package namelessgame.Threads;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Attack extends Thread {
    
    private JLabel label;
    private JFrame frame;
    private int times;
    
    final static private int FACTOR = 20;
    final static private int TACKLES = 4;

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public static int getFactor() {
        return FACTOR;
    }

    public static int getTackles() {
        return TACKLES;
    }
    
    @Override
    public void run()
    {
        for(int k = 1; k <= times; k++)
        {
            int signal = 1;

            for(int i = 1; i <= getTackles() * 2; i++)
            {
                label.setLocation(label.getX() + (getFactor() * signal), label.getY());
                frame.repaint();

                if(i == getTackles())
                    signal *= -1;

                try { Thread.sleep (50); } catch (InterruptedException ex) {}
            }
        }
        
    }
}

