package game;

import javax.swing.*;

public class Frame {

	public Frame()
	{
		JFrame frame = new JFrame();
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dig Champs");
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		ImageIcon i = new ImageIcon("C:/Dig/life0.png");
		frame.setIconImage(i.getImage());
	}
	
	public static void main(String[] args)
	{
		new Frame();
	}
}
