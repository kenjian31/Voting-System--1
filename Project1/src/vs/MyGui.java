/*
 * MyGui.java
 * Copyright (c) 2020, CSCI5801 Team5. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package vs;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.*;
/**
 * interface
 * @author Pengyin Chen 
 * @author Shuai Hao
 *
 */
public class MyGui extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel num0Lbl = new JLabel("Voting System");
	private JLabel num1Lbl = new JLabel("Seats ");
	private JLabel num2Lbl = new JLabel("Algorithm");
	private JLabel num3Lbl = new JLabel("FileName");
	private JLabel num4Lbl = new JLabel("Shuffle (y/n)");
	
	private JTextField num1TxtFld = new JTextField(12);
	private JTextField num3TxtFld = new JTextField(12);
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JButton inBtn  =new JButton("Import");
	private JButton geneBtn  =new JButton("GenerateAudit");
	private JButton restartBtn = new JButton("Restart");
	private JButton exitBtn  =new JButton("Exit");
	private JButton helpBtn  =new JButton("Help windows");
	
	private    JTextArea area = new JTextArea("\t\t\t \tWelcome to Voting System\n",50,60);
	private JScrollPane scroll = new JScrollPane (area);
	
	private String[] algStrings = { "Plurality", "STV" };
	private JComboBox algList = new JComboBox(algStrings);
	
    
	private String[] sfStrings = { "Yes", "No" };
	private JComboBox sfList = new JComboBox(sfStrings);
	//Create the combo box, select item at index 4.
	//Indices start at 0, so 4 specifies the pig.
	private VotingType vt;
	
	public MyGui(String title){
		super(title);
		this.setSize(1000,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add button to the frame; 

	}
	private void addPanelsToFrame() {
		this.add("North",p3);
		this.add("South",p2);
		this.add("North",p1);
		this.add("Center",scroll);
	}
	private void createPanels() {
		scroll.setBounds(10,60,780,500);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p1.setLayout(new FlowLayout());
		//p1.setBounds(0, 0, 200, 300);
		p3.add(num0Lbl);
		p1.add(num1Lbl);
		p1.add(num1TxtFld);
		p1.add(num2Lbl);
		p1.add(algList);
		p1.add(num3Lbl);
		p1.add(num3TxtFld);
		p1.add(num4Lbl);
		p1.add(sfList);
		p2.add(inBtn);
		p2.add(geneBtn);
		p2.add(restartBtn);
		p2.add(exitBtn);
		p2.add(helpBtn);

	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Help windows") {
		JOptionPane.showMessageDialog(helpBtn,
		        "Info of how to use this program!!!\n\n1. Inpput information\n"
		        + "Seats should be greater than 0\n"
		        + "Choose your algorithm\n"
		        + "Input file name\n"
		        + "Shuffle Option(y/n)\n"
		        + "\n2. Button Command\n"
		        + "Import allow you to import files"
		        + "Generate Audit will run the algorithm with the input\n"
		        + "You can see the detail on the screen\n"
		        + "Restart allow you to restart the program and clear the screen\n"
		        + "Exit will terminate the program and close the interface\n",
		        "Info",
				JOptionPane.INFORMATION_MESSAGE);
		
		} else if (e.getActionCommand() == "Import") {
			if (!num3TxtFld.getText().equals("") && !num1TxtFld.getText().equals("")) {
				if(algList.getSelectedItem() == "STV") {
					int seats = Integer.parseInt(num1TxtFld.getText());
					vt = new DroopQuota(seats, num3TxtFld.getText());
					try {
						vt.ReadFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					int seats = Integer.parseInt(num1TxtFld.getText());
					vt = new Plurality(seats,num3TxtFld.getText());
					try {
						vt.ReadFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				area.append("========================\n");
				area.append("Import File Finished\n");
		    } else {
		    	area.append("========================\n");
				area.append("Import File Failed\n");
				area.append("Please Check Your Seats and File Name input\n");
		    }
		} else if (e.getActionCommand() == "GenerateAudit") {
			if(vt != null) {
				area.append("========================\n");
				area.append("Genrate Audit.....\n");
				area.append("See Details below\n");
				if(sfList.getSelectedItem() == "Yes") {
					vt.shuffle_flag =1;
				} else {
					vt.shuffle_flag =0;
				}
				try {
					vt.GenerateAudit();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				area.append("========================\n");
				area.append("Genrate Audit Failed\n");
				area.append("Please Import File First\n");
			}
			
		} else if (e.getActionCommand() == "Restart") {
			num1TxtFld.setText("");
			num3TxtFld.setText("");
			area.setText("\t\t\t Welcome to Voting System\n");
		} else if(e.getActionCommand() == "Exit") {
			System.exit(0);
		}
		
	}
	
	private void connectListeners() {
		helpBtn.addActionListener(this);
		inBtn.addActionListener(this);
		geneBtn.addActionListener(this);
		restartBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		PrintStream out = new PrintStream( new TextAreaOutputStream( area ) );

		// redirect standard output stream to the TextAreaOutputStream
		System.setOut( out );

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr( out );
		
		
	}
	public static void main(String[] args){
		MyGui gui = new MyGui ("Voting System");
		
		gui.setLayout(new BorderLayout());
		
		
		gui.createPanels();
		gui.addPanelsToFrame();
		gui.connectListeners();
		
		gui.setVisible(true);
		
	}
	
}
