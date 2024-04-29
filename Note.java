import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Note {
  private int column;
  private int initialY;
  private int y;
  private boolean visible;
  private BufferedImage[] images;
  private int imageIndex;
  private Image image;

  public Note(int column_, int y) {
    column = column_;
    this.y = y;
    this.initialY = y;
    visible = true;

    images  = new BufferedImage[5];
    try {
      images[0] = ImageIO.read(new File("Alex Markova.jpeg")); 
      images[1] = ImageIO.read(new File("Amanda Zhang.jpeg")); 
      images[2] = ImageIO.read(new File("Arthur Cheong.jpeg")); 
      images[3] = ImageIO.read(new File("Sander Vonk.jpeg")); 
      images[4] = ImageIO.read(new File("Yhali Matot.jpeg")); 
    } catch (IOException e) {}

    imageIndex = (int)(Math.random()*5);

    image = images[imageIndex];
  }

  public void drawMe(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawImage(image, column*150, y, 150, 120, null); // x, y, width, height
  }

  public void setColumn(int column) {
    this.column = column;
  }
  
  public void reset() {
    // y = initialY;
    imageIndex = (int)(Math.random()*5);
    image = images[imageIndex];
    y= (int)(-800*Math.random());
  }

  public int getY() {
    return this.y;
  }

  public void incrementY(int increment) {
    this.y += increment;
  }

  public void setVisible(boolean visible_) {
    visible = visible_;
  }

  public boolean getVisible() {
    return visible;
  }
}
