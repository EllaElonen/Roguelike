import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map {
    //komment to test

    protected boolean shown;
    private int x, y;
    private JButton push1;
    private JButton push2;
    private JLabel label1;
    private JLabel label2;
    private int count1;
    private int count2;
    ImageIcon picture;

    public Map(int width, int height, String Title){

        picture = new ImageIcon(getClass().getResource("Map.jpeg"));
        int x = picture.getIconWidth();
        int y = picture.getIconHeight();

        push1 = new JButton("Push me");
        push1.addActionListener(new ButtonListener1());
        label1 = new JLabel("Button 1 pushes: " + count1);
        label1.setBackground(Color.orange);
        label1.setForeground(Color.black);

        push2 = new JButton("Push me too");
        push2.addActionListener(new ButtonListener2());
        label2 = new JLabel("Button 2 pushes: " + count2);
        label2.setBackground(Color.orange);
        label2.setForeground(Color.black);

        //add(push1);
        //add(label1);
        //add(push2);
        //add(label2);
        }

    class ButtonListener1 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        label1.setText("Text");
        }
    }

    class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label2.setText("Text");
        }
    }

    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(picture.getImage(), 0, 0, null);
        if(shown) {
            g.setColor(Color.black);
            g.fillOval(x-1000, y -1000, 1,1);
            //setBackground(Color.orange);
        }
    }
}
