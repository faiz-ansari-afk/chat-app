package com.chattingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;

public class Server extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    Server(){
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(14, 129, 115));
        p1.setBounds(0,0,400,50);
        add(p1);
        setUndecorated(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/chattingapplication/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5,10,30,30);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("com/chattingapplication/icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(45,5,40,40);
        p1.add(l2);
//
        JLabel l3 = new JLabel("Gaitonde Bhau");
        l3.setFont(new Font("SAN_SERIF",Font.BOLD,22));
        l3.setForeground(Color.WHITE);
        l3.setBounds(95,8,150,18);
        p1.add(l3);

        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF",Font.PLAIN,12));
        l4.setForeground(Color.lightGray);
        l4.setBounds(95,28,100,20);
        p1.add(l4);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("com/chattingapplication/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(280,10,30,30);
        p1.add(l5);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("com/chattingapplication/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 = new JLabel(i12);
        l6.setBounds(320,10,30,30);
        p1.add(l6);
//
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("com/chattingapplication/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 = new JLabel(i15);
        l7.setBounds(355,10,25,25);
        p1.add(l7);

        t1 = new JTextField();
        t1.setBounds(5,565,340,30);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        add(t1);

        b1 = new JButton(">");
        b1.setBounds(350,565,45,30);
        b1.setFont(new Font("SAN_SERIF",Font.BOLD,15));
        b1.addActionListener(this);
        add(b1);

        a1 = new JTextArea();
        a1.setBounds(5,55,390,505);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(400,600);
        setVisible(true);
        setLocation(100,100);
    }
    public void actionPerformed(ActionEvent e){
        try {
            String out = t1.getText();
            a1.setText(a1.getText() + "\n\t\t\t" + out);
            dataOutputStream.writeUTF(out);
            t1.setText("");
        }catch(Exception e1){}
    }
    public static void main(String[] args) {
        new Server().setVisible(true);
        String msgInput = "";
        try{
            skt = new ServerSocket(8001);
            s = skt.accept();
            dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(s.getOutputStream());
            msgInput = dataInputStream.readUTF();
            a1.setText(a1.getText()+"\n"+msgInput);
            skt.close();
            s.close();
        }catch (Exception e){}
    }
}