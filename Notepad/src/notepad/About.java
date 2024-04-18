package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    About(){
        setBounds(400,100,600,500);
        setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
        Image img2 = img.getImage().getScaledInstance(300,60,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel header = new JLabel(img3);
        header.setBounds(70,40,400,80);
        add(header);

        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        Image img5 = img4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);
        JLabel icon = new JLabel(img6);
        icon.setBounds(70,180,50,50);
        add(icon);

        JLabel text = new JLabel("<html>Arun Bhangdiya <br></br>Version 1.0.0(Bulild in Java)<br></br>Microsoft Office. All right reserved.</html> ");
        text.setBounds(150, 50, 500, 300);
        text.setFont(new Font("SAN SERIF", Font.PLAIN, 16));
        add(text);

        JButton b1 = new JButton("Ok");
        b1.addActionListener(this);
        b1.setBounds(150, 350, 120, 25);
        add(b1);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new About();
    }
}
