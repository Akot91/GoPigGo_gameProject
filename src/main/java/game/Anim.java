package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Anim extends JPanel implements ActionListener{
    
    private int actualImage = 0;
    private int numberOfImages = 0;
    private ImageIcon[] images;
    private int animationDelay = 0;
    private Timer timer;
    private boolean stop = false;
    
    public Anim(String source, int numberOfImages, int animationDelay){
        
        this.numberOfImages = numberOfImages;
        this.animationDelay = animationDelay;

        setAnimation(source, numberOfImages, animationDelay);
        timer.start();
    }
    
    private void setAnimation(String source, int numberOfImages, int delay){

        images = new ImageIcon[numberOfImages];

        for (int i = 0; i < numberOfImages; i++){
            images[i] = new ImageIcon(source + i + ".png");
        }

        timer = new Timer(delay, this);
    }
    
    public ImageIcon getImage(){
        return images[actualImage];
    }
    
    public void setImage(String src){
        images[0] = new ImageIcon(src);
    }
    
    public boolean isAnimationEnd(){
        if (actualImage == numberOfImages - 1){
            stop = true;
            return true;
        }
        else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(!stop){
            if(!GameStatus.isGamePaused())
                actualImage++;

            if (actualImage >= images.length){
                actualImage = 0;
            }  
        }
    }
        
}
