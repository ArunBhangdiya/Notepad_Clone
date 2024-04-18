package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea textarea;
    String text;

    Notepad() {
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newdoc);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(open);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(save);

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(print);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        file.add(exit);

        menubar.add(file);

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copy);

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(paste);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cut);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(selectAll);
        menubar.add(edit);

        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(this);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        aboutMenu.add(about);
        menubar.add(aboutMenu);

        setJMenuBar(menubar);

        textarea = new JTextArea();
        textarea.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        add(textarea);

        JScrollPane pane = new JScrollPane(textarea);
        add(pane);
        pane.setBorder(BorderFactory.createEmptyBorder());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            textarea.setText("");
        } else if (e.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(filter);
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textarea.read(reader, null);
            } catch (Exception error) {
                error.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");

            int action = saveas.showSaveDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                textarea.write(outFile);
            } catch (Exception error) {
                error.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Print")) {
            try {
                textarea.print();
            } catch (Exception error) {
                error.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Copy")) {
            text = textarea.getSelectedText();
        }
        else if (e.getActionCommand().equals("Paste")){
            textarea.insert(text,textarea.getCaretPosition());
        }else if (e.getActionCommand().equals("Cut")) {
            text = textarea.getSelectedText();
            textarea.replaceRange("",textarea.getSelectionStart(),textarea.getSelectionEnd());
        }else if (e.getActionCommand().equals("Select All")) {
            textarea.selectAll();
        } else if (e.getActionCommand().equals("About")) {
            new About().setVisible(true);
        }
    }
}
