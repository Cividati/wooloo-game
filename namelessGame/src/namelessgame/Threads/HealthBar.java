/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package namelessgame.Threads;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Henrique Barcia Lang
 */
public class HealthBar extends Thread {
    private JProgressBar hpBar;
    private JLabel hpLabel;
    
    private int maxHp;
    private int diffHp;
    private int timePerHealthPoint;

    public JProgressBar getHpBar() {
        return hpBar;
    }

    public void setHpBar(JProgressBar hpBar) {
        this.hpBar = hpBar;
    }

    public JLabel getHpLabel() {
        return hpLabel;
    }

    public void setHpLabel(JLabel hpLabel) {
        this.hpLabel = hpLabel;
    }

    public int getTimePerHealthPoint() {
        return timePerHealthPoint;
    }

    public void setTimePerHealthPoint(int timePerHealthPoint) {
        this.timePerHealthPoint = timePerHealthPoint;
    }

    public int getMaxHealth() {
        return maxHp;
    }

    public void setMaxHealth(int maxHp) {
        this.maxHp = maxHp;
    }  

    public int getDiffHealth() {
        return diffHp;
    }

    public void setDiffHealth(int diffHp) {
        this.diffHp = diffHp;
    } 
    
    @Override
    public void run()
    {
        while(diffHp != 0)
        {
            int hpLabelValue = Integer.parseInt(hpLabel.getText());
            
            if(diffHp > 0)
            {
                // init hp > final hp
                // tomou dmg
                
                hpLabelValue--;
                diffHp--;
                
                
            }
            else
            {
                // init hp < final hp
                // healou
                
                hpLabelValue++;
                diffHp++;
            }
            
            hpLabel.setText(Integer.toString(hpLabelValue));
            
            // value progress bar %
            // total_hp - 100
            // hpLabelValue - x
            hpBar.setValue((hpLabelValue * 100) / maxHp);
            
            try { Thread.sleep (timePerHealthPoint); } catch (InterruptedException ex) {}
        }
    }
    
}

