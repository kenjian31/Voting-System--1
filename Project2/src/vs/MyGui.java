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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


import javax.swing.*;

import com.sun.tracing.dtrace.ArgsAttributes;

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
	public static int flag = 1; 
	
	private JLabel num0Lbl = new JLabel("Voting System");
	private JLabel num1Lbl = new JLabel("Seats ");
	private JLabel num2Lbl = new JLabel("Algorithm");
	private JButton browse= new JButton("Browse File");
	private JLabel num3Lbl = new JLabel("FileName");
	private JLabel num4Lbl = new JLabel("Shuffle (y/n)");
	private JButton send_button = new JButton("SendToRun");
	private JButton stop_button = new JButton("Stop");
	
	private JLabel saveLbl = new JLabel("Save File Location:");
	private JLabel nameLbl = new JLabel("Rename File: ");
	private JTextField num5TxtFld = new JTextField(6);
	private JTextField num6TxtFld = new JTextField(6);
	
	private JTextField num1TxtFld = new JTextField(6);
	private JTextField num3TxtFld = new JTextField(6);
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JButton inBtn  =new JButton("Import");
	private JButton geneBtn  =new JButton("GenerateAudit");
	private JButton restartBtn = new JButton("Restart");
	private JButton exitBtn  =new JButton("Exit");
	private JButton helpBtn  =new JButton("Help windows");
	private JButton sBtn  =new JButton("Save");
	private JTextArea area = new JTextArea("\t \tWelcome to Voting System\n",20,45);
	private JScrollPane scroll = new JScrollPane (area);
	private JTextArea area2 = new JTextArea(20,45);
	private JScrollPane scroll2 = new JScrollPane (area2);
	private String[] algStrings = { "Plurality", "STV" };
	private JComboBox algList = new JComboBox(algStrings);
	
    
	private String[] sfStrings = { "Yes", "No" };
	private JComboBox sfList = new JComboBox(sfStrings);
	//Create the combo box, select item at index 4.
	//Indices start at 0, so 4 specifies the pig.
	private VotingType vt;
	
	public MyGui(String title){
		super(title);
		this.setSize(1000,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//add button to the frame; 

	}
	private void addPanelsToFrame() {
		this.add("North",p3);
		this.add("South",p2);
		this.add("North",p1);
		this.add("East",scroll);
		this.add("West",scroll2);
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
		p1.add(browse);
		p1.add(num3Lbl);
		p1.add(num3TxtFld);
		p1.add(num4Lbl);
		p1.add(sfList);
		p2.add(saveLbl);
		p2.add(num5TxtFld);
		p2.add(nameLbl);
		p2.add(num6TxtFld);
		p2.add(sBtn);
		p2.add(send_button);
		p2.add(stop_button);
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
		} else if (e.getActionCommand() == "Browse File") {
			 JFileChooser fileChooser = new JFileChooser();
			 int returnValue = fileChooser.showOpenDialog(null);
			 if (returnValue == JFileChooser.APPROVE_OPTION) {
				 File selectedFile = fileChooser.getSelectedFile();
				 num3TxtFld.setText(selectedFile.getAbsolutePath());
			 }
		} else if (e.getActionCommand() == "SendToRun"){
			System.out.print("receiving new ballots\n");
			if(!area2.getText().equals("")) {
				if(flag == 1) {
					String current = null;
					try {
						current = new java.io.File( "." ).getCanonicalPath();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String screenFileLocation = current+"/src/vs/screen_input.cvs";
					FileWriter fileWriter = null;
					try {
						fileWriter = new FileWriter(screenFileLocation);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					PrintWriter printWriter = new PrintWriter(fileWriter);

					printWriter.printf(area2.getText());
					printWriter.close();
					num3TxtFld.setText(screenFileLocation);
				} else {
					area.append("Alread Stop Receiving New Input!! /n ");
				}
			} else {
				
				System.out.print("Please enter some ballots info to be received");
			}
		} else if(e.getActionCommand() == "Stop") {
			flag =0;
			area.append("stop receiving new ballots from screen\n");
			
		} else if (e.getActionCommand() == "Save"){
			if(!num5TxtFld.getText().equals("") && !num6TxtFld.getText().equals("") && vt!=null 
					&& algList.getSelectedItem() == "STV") {
				try {
					vt.WriteInvalidBallot(num5TxtFld.getText(), num6TxtFld.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			} else {
				area.append("Please check save file location, file name, algorithm and input file \n ");
				area.append("Note: file location should be the absolute path \n ");
			}
			
		}
		
	}
	
	private void connectListeners() {
		helpBtn.addActionListener(this);
		inBtn.addActionListener(this);
		geneBtn.addActionListener(this);
		restartBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		browse.addActionListener(this);
		stop_button.addActionListener(this);
		send_button.addActionListener(this);
		sBtn.addActionListener(this);
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
