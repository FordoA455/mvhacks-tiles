import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.Rectangle2D;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements KeyListener, ActionListener {
  private ArrayList<Boolean> keysPressed = new ArrayList<>();
  private final int width = 1200;
  private final int height = 700;
  private int score;
  private int lives = 10;
  private Note[] notes = new Note[8];
  private int gameState = 0; // 0 = start, 1 = play, 2 = lose
  private final String music = "Arthur_Rickroll.wav";
  private JButton startButton;
  private JLabel title;

  public Screen() {
    setLayout(null);
    

    score = 0;
    
    for (int i = 0; i < 8; i++) {
      keysPressed.add(false);      
    }

    for (int i = 0; i < notes.length; i++) {
      int randomY = (int)(-1800*Math.random());
      notes[i] = new Note(i, randomY); 
    }

    startButton = new JButton("START");
    startButton.setBounds(550,325,100,50);
    startButton.addActionListener(this);
    add(startButton);

    title = new JLabel();
    title.setFont(new Font("Arial", Font.BOLD, 30));
    title.setForeground(Color.WHITE);
    title.setBounds(500, 250, 350, 30);
    title.setText("MVHacksTiles");
    this.add(title);    

    addKeyListener(this);
    this.setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (lives == 0) {
      gameState = 2;
    }

    if (gameState == 0) {
      title.setVisible(true);
      g.setColor(new Color(79, 70, 74));
      g.fillRect(0, 0, 1366, 768);
      g.setColor(Color.WHITE);
      g.setFont(new Font("SansSerif", 18, Font.BOLD));
      g.drawString("MVHacks Tiles (features the directors)", 300, 200);
      g.setFont(new Font("SansSerif", 14, Font.PLAIN));
      g.drawString("enjoy the interesting background music selection", 200, 250);
      g.drawString("basically piano tiles but you press 1234 and 7890", 195, 300);
    } else if (gameState == 1) {
      title.setVisible(false);
      // System.out.println(keysPressed.get(0));
      Graphics2D g2 = (Graphics2D) g;
      Rectangle2D rect = new Rectangle2D.Float();
      rect.setRect(0, 0, width, height);
      GradientPaint gp = new GradientPaint(0, 0,
      new Color(252, 197, 197), width, height, 
      new Color(79, 188, 255));
      g2.setPaint(gp);
      g2.fill(rect);


      g.setColor(Color.BLACK);
      for (int i = 1; i < 8; i++) {
        g.drawLine((width/8)*i, 0, (width/8)*i, 700);
        g.drawLine((width/8)*i+1, 0, (width/8)*i+1, 700); // thicker line
      }

      g.drawLine(0, 580, width, 580);
      g.drawLine(0, 581, width, 581); // thicker line

      for (int i = 0; i < notes.length; i++) {
        if (keysPressed.get(i) == true) {
          if ((notes[i].getY() <= 580) && (notes[i].getY() >= 460)) {
            notes[i].setVisible(false); 
          }
        }
        notes[i].incrementY(10);
        if ((notes[i].getY()) >= 700) {
          lives--;
          notes[i].reset();
        }
        notes[i].drawMe(g);
      }

      g.setColor(Color.WHITE);
      g.fillRect(500, 0, 200, 20);
      g.setColor(Color.black);
      g.drawString("Score:"+score, 510, 10);

      // 
    } else if (gameState == 2) {
      // g.setColor(Color.BLACK);
      // g.setFont(new Font("SansSerif", 18, Font.BOLD));
      // g.drawString("You lost.", 300, 200);
      // g.setFont(new Font("SansSerif", 14, Font.PLAIN));
      // g.drawString("thanks for playing!", 200, 250);
      // g.drawString("hopefully it was funny", 195, 300);
      title.setForeground(Color.BLACK);
      title.setText("You lost.");
      title.setVisible(true);
    }

    // try {
    //   Thread.sleep(15);
    // } catch (InterruptedException e) {
    //   e.printStackTrace();
    // }
    
    // repaint(); //repeat
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    // if (e.getKeyCode() == KeyEvent.VK_1) {
    //   keysPressed.set(0, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_2) {
    //   keysPressed.set(1, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_3) {
    //   keysPressed.set(2, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_4) {
    //   keysPressed.set(3, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_7) {
    //   keysPressed.set(4, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_8) {
    //   keysPressed.set(5, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_9) {
    //   keysPressed.set(6, true);
    // } else if (e.getKeyCode() == KeyEvent.VK_0) {
    //   keysPressed.set(7, true);
    // }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // if (e.getKeyCode() == KeyEvent.VK_1) {
    //   keysPressed.set(0, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_2) {
    //   keysPressed.set(1, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_3) {
    //   keysPressed.set(2, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_4) {
    //   keysPressed.set(3, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_7) {
    //   keysPressed.set(4, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_8) {
    //   keysPressed.set(5, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_9) {
    //   keysPressed.set(6, false);
    // } else if (e.getKeyCode() == KeyEvent.VK_0) {
    //   keysPressed.set(7, false);
    // }
    // repaint();
    // System.out.println(e.getKeyCode());
    if (e.getKeyCode() == KeyEvent.VK_1) {
      System.out.println(notes[0].getY());
      if (notes[0].getY() <= 580 && notes[0].getY() >= 400) {
        score += 100;
        System.out.println("reset");
        notes[0].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_2) {
      if (notes[1].getY() <= 580 && notes[1].getY() >= 400) {
        score += 100;
        notes[1].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_3) {
      if (notes[2].getY() <= 580 && notes[2].getY() >= 400) {
        score += 100;
        notes[2].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_4) {
      if (notes[3].getY() <= 580 && notes[3].getY() >= 400) {
        score += 100;
        notes[3].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_7) {
      if (notes[4].getY() <= 580 && notes[4].getY() >= 400) {
        score += 100;
        notes[4].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_8) {
      if (notes[5].getY() <= 580 && notes[5].getY() >= 400) {
        score += 100;
        notes[5].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_9) {
      if (notes[6].getY() <= 580 && notes[6].getY() >= 400) {
        score += 100;
        notes[6].reset();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_0) {
      if (notes[7].getY() <= 580 && notes[7].getY() >= 400) {
        score += 100;
        notes[7].reset();
      }
    }
  }

  public void animate() {
    while (true) {
      try {
        Thread.sleep(40);

      } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
      }
      repaint();
    }
  }

  public static synchronized void playSound(final String url) {
    new Thread(new Runnable() {
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }).start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
      gameState = 1;
      startButton.setVisible(false);
      playSound(music);
    }
  }
}