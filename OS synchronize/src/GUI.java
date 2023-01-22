import Threads.buffer;
import Threads.consumer;
import Threads.producer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    int N;
    int bufferSize;
    String outputFile;

    JButton button ;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField n;
    JTextField buff;
    JTextField out;

    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    JLabel label8;
    JLabel label9;
    JLabel label0;

    public GUI() {
        JFrame frame = new JFrame();

        button = new JButton("Start Producer");
        button.addActionListener(this);
        label1 = new JLabel("Number");
        label2 = new JLabel("Buffer Size");
        label3 = new JLabel("Output File");
        n = new JTextField(20);
        buff = new JTextField(20);
        out = new JTextField(20);

        label4 = new JLabel("The Largest Prime Number");
        label5 = new JLabel("Number");
        label6 = new JLabel("Number Of Elements Generated");
        label7 = new JLabel("Number");
        label8 = new JLabel("Time Elapsed Since The Start of Processing : ");
        label9 = new JLabel("Number ms");
        label0 = new JLabel("");



        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200, 300, 200,300));
        panel.setLayout(new GridLayout(0, 2));
        panel.add(label1);
        panel.add(n);
        panel.add(label2);
        panel.add(buff);
        panel.add(label3);
        panel.add(out);

        panel.add(label0);
        panel.add(button);

        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(" OS Assignment 1 ");
        frame.pack();
        frame.setVisible(true);
    }

    public static buffer buf ;
    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        N = Integer.parseInt(n.getText());
        bufferSize = Integer.parseInt(buff.getText());
        outputFile = out.getText();
        long start = System.nanoTime();

        buf = new buffer(bufferSize);

        producer P = new producer(buf, N);
        consumer C = new consumer(buf,outputFile);
        P.start();
        C.start();




        while (P.isAlive() || C.isAlive()) ;
        int Mn=buf.consume();
        int la =buf.consume();

        String large = Integer.toString(P.getLargest());
        String num = Integer.toString(P.getNumberPrimes());

        label5.setText(large);
        label7.setText(num);
        // some time passes
        long end = System.nanoTime();
        long elapsedTime = end - start;
        label9.setText(String.valueOf(elapsedTime) + " ms");



        //System.exit(0);
    }
}
