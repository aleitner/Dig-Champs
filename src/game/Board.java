package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable {

	Champ[] champ = new Champ[2];
	Enemy enemy;
	public Image bg1;
	public Image pselect;
	public Image title;
	public Image arrow;
	public Image snailImg;
	public Image wormImg;
	public Image enemyImg;
	public Image start0;
	public Image start1;
	public Image life0;
	public Image life1;
	public Image font0, font1, font2, font3, font4, font5, font6, font7, font8, font9;
	public Image score00, score01, score02, score03, score10, score11, score12, score13;
	public Image gameover;
	
	Timer time;
	int v = 180;
	Thread animator;
	
	boolean a = false;
	boolean done2 = false;
	
	int sel = 0; //0 = player 1, 1 = player 2
	int state = 0; //0 = title screen1 = character select, 2 = game, 3 = death sequence, 4 = game over
	int level = 0;
	long attackTime = 0;
	long snailTime = System.currentTimeMillis();
	
	int y; //for dying
	boolean up = true; //also for dying
	boolean showstart1 = true; //show the "player 1 start" stuff?
	boolean showstart2 = false;
	int deathinc=0;
	
	public Board()
	{		
		champ[0] = new Shovel();
		champ[1] = new Pick();
		enemy = new Enemy();
		addKeyListener(new AL());
		setFocusable(true);
		
		ImageIcon i = new ImageIcon("C:/Dig/bg1.png");
		bg1 = i.getImage();
		i = new ImageIcon("C:/Dig/pselect.png");
		pselect = i.getImage();
		i = new ImageIcon("C:/Dig/title.gif");
		title = i.getImage();
		i = new ImageIcon("C:/Dig/arrow.png");
		arrow = i.getImage();
		i = new ImageIcon("C:/Dig/start0.png");
		start0 = i.getImage();
		i = new ImageIcon("C:/Dig/start1.png");
		start1 = i.getImage();
		i = new ImageIcon("C:/Dig/life0.png");
		life0 = i.getImage();
		i = new ImageIcon("C:/Dig/life1.png");
		life1 = i.getImage();
		i = new ImageIcon("C:/Dig/snail.png");
		snailImg = i.getImage();
		i = new ImageIcon("C:/Dig/worm.png");
		wormImg = i.getImage();
		i = new ImageIcon("C:/Dig/font0.png");
		font0 = i.getImage();
		i = new ImageIcon("C:/Dig/font1.png");
		font1 = i.getImage();
		i = new ImageIcon("C:/Dig/font2.png");
		font2 = i.getImage();
		i = new ImageIcon("C:/Dig/font3.png");
		font3 = i.getImage();
		i = new ImageIcon("C:/Dig/font4.png");
		font4 = i.getImage();
		i = new ImageIcon("C:/Dig/font5.png");
		font5 = i.getImage();
		i = new ImageIcon("C:/Dig/font6.png");
		font6 = i.getImage();
		i = new ImageIcon("C:/Dig/font7.png");
		font7 = i.getImage();
		i = new ImageIcon("C:/Dig/font8.png");
		font8 = i.getImage();
		i = new ImageIcon("C:/Dig/font9.png");
		font9 = i.getImage();
		i = new ImageIcon("C:/Dig/gameover.png");
		gameover = i.getImage();
		
		time = new Timer(5,this);
		time.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(state == 0)
		{
		}
		else if(state == 1)
		{
		}
		else if(state == 2)
		{
			champ[sel].move();
			if(enemy.isAlive())
				enemy.move(champ[sel].level-1);
			long snailTime2 = System.currentTimeMillis();
			if(snailTime2 - snailTime > 5000 && !enemy.isAlive())
			{
				snailTime = System.currentTimeMillis();
				enemy.spawn(champ[sel].level);
			}
			repaint();
		}
		else if(state == 3)
		{
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		switch(champ[sel].level)
		{
			case 1: enemyImg = snailImg; break;
			case 2: enemyImg = wormImg; break;
			case 3: enemyImg = wormImg; break;
		}
		
		if(state == 0) //title screen
		{
			g2d.drawImage(title, 0, 0, null);
			repaint();
		}
		else if(state == 1) //player select
		{
			g2d.drawImage(pselect, 0, 0, null);
			g2d.drawImage(arrow, 160+(260*sel), 375, null);
		}
		else if(state == 2) //in game
		{
			if(champ[sel].getdY() == 2 && done2 == false)
			{
				done2 = true;
				animator = new Thread(this);
				animator.start();
			}
			
			if((champ[sel].getX() - 240) % 1600 == 0) //240 = playerX when loop
				champ[sel].nx = 0;
			if((champ[sel].getX() - 1040) % 1600 == 0) //240 + 800 (bg length)
				champ[sel].nx2 = 0;
			
			g2d.drawImage(bg1, 620 - champ[sel].getnX2(), 0, null);
			if(champ[sel].getX() > 240)
				g2d.drawImage(bg1, 620 - champ[sel].getnX(), 0, null);
			g2d.drawImage(champ[sel].getImage(), champ[sel].left, v+(sel*20), null);
			
			if(champ[sel].getdx() == -1)
			{
				g2d.drawImage(bg1, 620-champ[sel].getnX2(), 0, null);
				g2d.drawImage(champ[sel].getImage(), champ[sel].left, v+(sel*20), null);
			}
			
			//show the "player 1 start" stuff
			if(showstart2)
			{				
				wait(1000);
				showstart2 = false;
			}			
			if(showstart1)
			{
				if(sel == 0)
					g2d.drawImage(start0, 240, 140, null);
				else
					g2d.drawImage(start1, 240, 140, null);
				
				showstart1 = false;
				showstart2 = true;
			}
			
			//show the lives
			if(champ[0].lives == 2)
			{
				g2d.drawImage(life0, 50, 370, null);
				g2d.drawImage(life0, 105, 370, null);
			}
			else if(champ[0].lives == 1)
			{
				g2d.drawImage(life0, 50, 370, null);
			}
			else
			{
			}
			if(champ[1].lives == 2)
			{
				g2d.drawImage(life1, 500, 380, null);
				g2d.drawImage(life1, 555, 380, null);
			}
			else if(champ[1].lives == 1)
			{
				g2d.drawImage(life1, 500, 380, null);
			}
			else
			{
			}
			
			//show the scores
			switch(champ[0].score[0])
			{
			case 0: score00 = font0; break;
			case 1: score00 = font1; break;
			case 2: score00 = font2; break;
			case 3: score00 = font3; break;
			case 4: score00 = font4; break;
			case 5: score00 = font5; break;
			case 6: score00 = font6; break;
			case 7: score00 = font7; break;
			case 8: score00 = font8; break;
			case 9: score00 = font9; break;
			}
			switch(champ[0].score[1])
			{
			case 0: score01 = font0; break;
			case 1: score01 = font1; break;
			case 2: score01 = font2; break;
			case 3: score01 = font3; break;
			case 4: score01 = font4; break;
			case 5: score01 = font5; break;
			case 6: score01 = font6; break;
			case 7: score01 = font7; break;
			case 8: score01 = font8; break;
			case 9: score01 = font9; break;
			}
			switch(champ[0].score[2])
			{
			case 0: score02 = font0; break;
			case 1: score02 = font1; break;
			case 2: score02 = font2; break;
			case 3: score02 = font3; break;
			case 4: score02 = font4; break;
			case 5: score02 = font5; break;
			case 6: score02 = font6; break;
			case 7: score02 = font7; break;
			case 8: score02 = font8; break;
			case 9: score02= font9; break;
			}
			switch(champ[0].score[3])
			{
			case 0: score03 = font0; break;
			case 1: score03 = font1; break;
			case 2: score03 = font2; break;
			case 3: score03 = font3; break;
			case 4: score03 = font4; break;
			case 5: score03 = font5; break;
			case 6: score03 = font6; break;
			case 7: score03 = font7; break;
			case 8: score03 = font8; break;
			case 9: score03 = font9; break;
			}
			switch(champ[1].score[0])
			{
			case 0: score10 = font0; break;
			case 1: score10 = font1; break;
			case 2: score10 = font2; break;
			case 3: score10 = font3; break;
			case 4: score10 = font4; break;
			case 5: score10 = font5; break;
			case 6: score10 = font6; break;
			case 7: score10 = font7; break;
			case 8: score10 = font8; break;
			case 9: score10 = font9; break;
			}
			switch(champ[1].score[1])
			{
			case 0: score11 = font0; break;
			case 1: score11 = font1; break;
			case 2: score11 = font2; break;
			case 3: score11 = font3; break;
			case 4: score11 = font4; break;
			case 5: score11 = font5; break;
			case 6: score11 = font6; break;
			case 7: score11 = font7; break;
			case 8: score11 = font8; break;
			case 9: score11 = font9; break;
			}
			switch(champ[1].score[2])
			{
			case 0: score12 = font0; break;
			case 1: score12 = font1; break;
			case 2: score12 = font2; break;
			case 3: score12 = font3; break;
			case 4: score12 = font4; break;
			case 5: score12 = font5; break;
			case 6: score12 = font6; break;
			case 7: score12 = font7; break;
			case 8: score12 = font8; break;
			case 9: score12 = font9; break;
			}
			switch(champ[1].score[3])
			{
			case 0: score13 = font0; break;
			case 1: score13 = font1; break;
			case 2: score13 = font2; break;
			case 3: score13 = font3; break;
			case 4: score13 = font4; break;
			case 5: score13 = font5; break;
			case 6: score13 = font6; break;
			case 7: score13 = font7; break;
			case 8: score13 = font8; break;
			case 9: score13 = font9; break;
			}
			
			g2d.drawImage(score00, 50, 20, null);
			g2d.drawImage(score01, 70, 20, null);
			g2d.drawImage(score02, 90, 20, null);
			g2d.drawImage(score03, 110, 20, null);
			g2d.drawImage(score10, 500, 20, null);
			g2d.drawImage(score11, 520, 20, null);
			g2d.drawImage(score12, 540, 20, null);
			g2d.drawImage(score13, 560, 20, null);
			
			//check for a level increase
			if(champ[sel].getX() > 1000 && champ[sel].level == 1) champ[sel].level = 2;
			if(champ[sel].getX() > 2500 && champ[sel].level == 2) champ[sel].level = 3;
			
			if(enemy.isAlive())
			{
				g2d.drawImage(enemyImg, enemy.getX(), 240, null);
				
				if(enemy.getX()-132 < champ[sel].left && v >= 150)
				{
					champ[sel].die();
					state = 3;
					y = 50;
					repaint();
				}
			}
		}
		else if(state == 3)
		{
			deathinc++;
			done = true;
			if(up) //move the image up
			{
				if(v > y) v = v - 5;
				else
				{
					up = false;
					y = 480;
				}
				g2d.drawImage(bg1, 620 - champ[sel].getnX2(), 0, null);
				if(champ[sel].getX() > 240)
					g2d.drawImage(bg1, 620 - champ[sel].getnX(), 0, null);
				
				//show the lives
				if(champ[0].lives == 2)
				{
					g2d.drawImage(life0, 50, 370, null);
					g2d.drawImage(life0, 105, 370, null);
				}
				else if(champ[0].lives == 1)
				{
					g2d.drawImage(life0, 50, 370, null);
				}
				else
				{
				}
				if(champ[1].lives == 2)
				{
					g2d.drawImage(life1, 500, 380, null);
					g2d.drawImage(life1, 555, 380, null);
				}
				else if(champ[1].lives == 1)
				{
					g2d.drawImage(life1, 500, 380, null);
				}
				else
				{
				}
				
				//show the scores
				switch(champ[0].score[0])
				{
				case 0: score00 = font0; break;
				case 1: score00 = font1; break;
				case 2: score00 = font2; break;
				case 3: score00 = font3; break;
				case 4: score00 = font4; break;
				case 5: score00 = font5; break;
				case 6: score00 = font6; break;
				case 7: score00 = font7; break;
				case 8: score00 = font8; break;
				case 9: score00 = font9; break;
				}
				switch(champ[0].score[1])
				{
				case 0: score01 = font0; break;
				case 1: score01 = font1; break;
				case 2: score01 = font2; break;
				case 3: score01 = font3; break;
				case 4: score01 = font4; break;
				case 5: score01 = font5; break;
				case 6: score01 = font6; break;
				case 7: score01 = font7; break;
				case 8: score01 = font8; break;
				case 9: score01 = font9; break;
				}
				switch(champ[0].score[2])
				{
				case 0: score02 = font0; break;
				case 1: score02 = font1; break;
				case 2: score02 = font2; break;
				case 3: score02 = font3; break;
				case 4: score02 = font4; break;
				case 5: score02 = font5; break;
				case 6: score02 = font6; break;
				case 7: score02 = font7; break;
				case 8: score02 = font8; break;
				case 9: score02= font9; break;
				}
				switch(champ[0].score[3])
				{
				case 0: score03 = font0; break;
				case 1: score03 = font1; break;
				case 2: score03 = font2; break;
				case 3: score03 = font3; break;
				case 4: score03 = font4; break;
				case 5: score03 = font5; break;
				case 6: score03 = font6; break;
				case 7: score03 = font7; break;
				case 8: score03 = font8; break;
				case 9: score03 = font9; break;
				}
				switch(champ[1].score[0])
				{
				case 0: score10 = font0; break;
				case 1: score10 = font1; break;
				case 2: score10 = font2; break;
				case 3: score10 = font3; break;
				case 4: score10 = font4; break;
				case 5: score10 = font5; break;
				case 6: score10 = font6; break;
				case 7: score10 = font7; break;
				case 8: score10 = font8; break;
				case 9: score10 = font9; break;
				}
				switch(champ[1].score[1])
				{
				case 0: score11 = font0; break;
				case 1: score11 = font1; break;
				case 2: score11 = font2; break;
				case 3: score11 = font3; break;
				case 4: score11 = font4; break;
				case 5: score11 = font5; break;
				case 6: score11 = font6; break;
				case 7: score11 = font7; break;
				case 8: score11 = font8; break;
				case 9: score11 = font9; break;
				}
				switch(champ[1].score[2])
				{
				case 0: score12 = font0; break;
				case 1: score12 = font1; break;
				case 2: score12 = font2; break;
				case 3: score12 = font3; break;
				case 4: score12 = font4; break;
				case 5: score12 = font5; break;
				case 6: score12 = font6; break;
				case 7: score12 = font7; break;
				case 8: score12 = font8; break;
				case 9: score12 = font9; break;
				}
				switch(champ[1].score[3])
				{
				case 0: score13 = font0; break;
				case 1: score13 = font1; break;
				case 2: score13 = font2; break;
				case 3: score13 = font3; break;
				case 4: score13 = font4; break;
				case 5: score13 = font5; break;
				case 6: score13 = font6; break;
				case 7: score13 = font7; break;
				case 8: score13 = font8; break;
				case 9: score13 = font9; break;
				}
				
				g2d.drawImage(score00, 50, 20, null);
				g2d.drawImage(score01, 70, 20, null);
				g2d.drawImage(score02, 90, 20, null);
				g2d.drawImage(score03, 110, 20, null);
				g2d.drawImage(score10, 500, 20, null);
				g2d.drawImage(score11, 520, 20, null);
				g2d.drawImage(score12, 540, 20, null);
				g2d.drawImage(score13, 560, 20, null);
				
				g2d.drawImage(champ[sel].getImage(), champ[sel].left+deathinc, v, null);
				
				wait(5);
				repaint();
			}
			else //move it down
			{
				if(v < y) v = v + 5;
				else
				{
					up = true;
					showstart1 = true;
					state = 2;
					v = 180;
					deathinc=0;
					enemy.die();
					snailTime = System.currentTimeMillis();
					
					champ[sel].respawn();
					sel = (sel * -1) + 1;
					
					repaint();					
				}
				g2d.drawImage(bg1, 620 - champ[sel].getnX2(), 0, null);
				if(champ[sel].getX() > 240)
					g2d.drawImage(bg1, 620 - champ[sel].getnX(), 0, null);
				
				//show the lives
				if(champ[0].lives == 2)
				{
					g2d.drawImage(life0, 50, 370, null);
					g2d.drawImage(life0, 105, 370, null);
				}
				else if(champ[0].lives == 1)
				{
					g2d.drawImage(life0, 50, 370, null);
				}
				else
				{
				}
				if(champ[1].lives == 2)
				{
					g2d.drawImage(life1, 500, 380, null);
					g2d.drawImage(life1, 555, 380, null);
				}
				else if(champ[1].lives == 1)
				{
					g2d.drawImage(life1, 500, 380, null);
				}
				else
				{
				}
				
				//show the scores
				switch(champ[0].score[0])
				{
				case 0: score00 = font0; break;
				case 1: score00 = font1; break;
				case 2: score00 = font2; break;
				case 3: score00 = font3; break;
				case 4: score00 = font4; break;
				case 5: score00 = font5; break;
				case 6: score00 = font6; break;
				case 7: score00 = font7; break;
				case 8: score00 = font8; break;
				case 9: score00 = font9; break;
				}
				switch(champ[0].score[1])
				{
				case 0: score01 = font0; break;
				case 1: score01 = font1; break;
				case 2: score01 = font2; break;
				case 3: score01 = font3; break;
				case 4: score01 = font4; break;
				case 5: score01 = font5; break;
				case 6: score01 = font6; break;
				case 7: score01 = font7; break;
				case 8: score01 = font8; break;
				case 9: score01 = font9; break;
				}
				switch(champ[0].score[2])
				{
				case 0: score02 = font0; break;
				case 1: score02 = font1; break;
				case 2: score02 = font2; break;
				case 3: score02 = font3; break;
				case 4: score02 = font4; break;
				case 5: score02 = font5; break;
				case 6: score02 = font6; break;
				case 7: score02 = font7; break;
				case 8: score02 = font8; break;
				case 9: score02= font9; break;
				}
				switch(champ[0].score[3])
				{
				case 0: score03 = font0; break;
				case 1: score03 = font1; break;
				case 2: score03 = font2; break;
				case 3: score03 = font3; break;
				case 4: score03 = font4; break;
				case 5: score03 = font5; break;
				case 6: score03 = font6; break;
				case 7: score03 = font7; break;
				case 8: score03 = font8; break;
				case 9: score03 = font9; break;
				}
				switch(champ[1].score[0])
				{
				case 0: score10 = font0; break;
				case 1: score10 = font1; break;
				case 2: score10 = font2; break;
				case 3: score10 = font3; break;
				case 4: score10 = font4; break;
				case 5: score10 = font5; break;
				case 6: score10 = font6; break;
				case 7: score10 = font7; break;
				case 8: score10 = font8; break;
				case 9: score10 = font9; break;
				}
				switch(champ[1].score[1])
				{
				case 0: score11 = font0; break;
				case 1: score11 = font1; break;
				case 2: score11 = font2; break;
				case 3: score11 = font3; break;
				case 4: score11 = font4; break;
				case 5: score11 = font5; break;
				case 6: score11 = font6; break;
				case 7: score11 = font7; break;
				case 8: score11 = font8; break;
				case 9: score11 = font9; break;
				}
				switch(champ[1].score[2])
				{
				case 0: score12 = font0; break;
				case 1: score12 = font1; break;
				case 2: score12 = font2; break;
				case 3: score12 = font3; break;
				case 4: score12 = font4; break;
				case 5: score12 = font5; break;
				case 6: score12 = font6; break;
				case 7: score12 = font7; break;
				case 8: score12 = font8; break;
				case 9: score12 = font9; break;
				}
				switch(champ[1].score[3])
				{
				case 0: score13 = font0; break;
				case 1: score13 = font1; break;
				case 2: score13 = font2; break;
				case 3: score13 = font3; break;
				case 4: score13 = font4; break;
				case 5: score13 = font5; break;
				case 6: score13 = font6; break;
				case 7: score13 = font7; break;
				case 8: score13 = font8; break;
				case 9: score13 = font9; break;
				}
				
				g2d.drawImage(score00, 50, 20, null);
				g2d.drawImage(score01, 70, 20, null);
				g2d.drawImage(score02, 90, 20, null);
				g2d.drawImage(score03, 110, 20, null);
				g2d.drawImage(score10, 500, 20, null);
				g2d.drawImage(score11, 520, 20, null);
				g2d.drawImage(score12, 540, 20, null);
				g2d.drawImage(score13, 560, 20, null);
				
				g2d.drawImage(champ[sel].getImage(), champ[sel].left+deathinc, v, null);
				
				if(champ[0].lives == 0 && champ[1].lives == 0) state = 4;
				
				wait(5);
				repaint();
			}
		}
		else if(state == 4)
		{
			g2d.drawImage(bg1, 620 - champ[sel].getnX2(), 0, null);
			if(champ[sel].getX() > 240)
				g2d.drawImage(bg1, 620 - champ[sel].getnX(), 0, null);
			
			switch(champ[0].score[0])
			{
			case 0: score00 = font0; break;
			case 1: score00 = font1; break;
			case 2: score00 = font2; break;
			case 3: score00 = font3; break;
			case 4: score00 = font4; break;
			case 5: score00 = font5; break;
			case 6: score00 = font6; break;
			case 7: score00 = font7; break;
			case 8: score00 = font8; break;
			case 9: score00 = font9; break;
			}
			switch(champ[0].score[1])
			{
			case 0: score01 = font0; break;
			case 1: score01 = font1; break;
			case 2: score01 = font2; break;
			case 3: score01 = font3; break;
			case 4: score01 = font4; break;
			case 5: score01 = font5; break;
			case 6: score01 = font6; break;
			case 7: score01 = font7; break;
			case 8: score01 = font8; break;
			case 9: score01 = font9; break;
			}
			switch(champ[0].score[2])
			{
			case 0: score02 = font0; break;
			case 1: score02 = font1; break;
			case 2: score02 = font2; break;
			case 3: score02 = font3; break;
			case 4: score02 = font4; break;
			case 5: score02 = font5; break;
			case 6: score02 = font6; break;
			case 7: score02 = font7; break;
			case 8: score02 = font8; break;
			case 9: score02= font9; break;
			}
			switch(champ[0].score[3])
			{
			case 0: score03 = font0; break;
			case 1: score03 = font1; break;
			case 2: score03 = font2; break;
			case 3: score03 = font3; break;
			case 4: score03 = font4; break;
			case 5: score03 = font5; break;
			case 6: score03 = font6; break;
			case 7: score03 = font7; break;
			case 8: score03 = font8; break;
			case 9: score03 = font9; break;
			}
			switch(champ[1].score[0])
			{
			case 0: score10 = font0; break;
			case 1: score10 = font1; break;
			case 2: score10 = font2; break;
			case 3: score10 = font3; break;
			case 4: score10 = font4; break;
			case 5: score10 = font5; break;
			case 6: score10 = font6; break;
			case 7: score10 = font7; break;
			case 8: score10 = font8; break;
			case 9: score10 = font9; break;
			}
			switch(champ[1].score[1])
			{
			case 0: score11 = font0; break;
			case 1: score11 = font1; break;
			case 2: score11 = font2; break;
			case 3: score11 = font3; break;
			case 4: score11 = font4; break;
			case 5: score11 = font5; break;
			case 6: score11 = font6; break;
			case 7: score11 = font7; break;
			case 8: score11 = font8; break;
			case 9: score11 = font9; break;
			}
			switch(champ[1].score[2])
			{
			case 0: score12 = font0; break;
			case 1: score12 = font1; break;
			case 2: score12 = font2; break;
			case 3: score12 = font3; break;
			case 4: score12 = font4; break;
			case 5: score12 = font5; break;
			case 6: score12 = font6; break;
			case 7: score12 = font7; break;
			case 8: score12 = font8; break;
			case 9: score12 = font9; break;
			}
			switch(champ[1].score[3])
			{
			case 0: score13 = font0; break;
			case 1: score13 = font1; break;
			case 2: score13 = font2; break;
			case 3: score13 = font3; break;
			case 4: score13 = font4; break;
			case 5: score13 = font5; break;
			case 6: score13 = font6; break;
			case 7: score13 = font7; break;
			case 8: score13 = font8; break;
			case 9: score13 = font9; break;
			}
			
			g2d.drawImage(score00, 50, 20, null);
			g2d.drawImage(score01, 70, 20, null);
			g2d.drawImage(score02, 90, 20, null);
			g2d.drawImage(score03, 110, 20, null);
			g2d.drawImage(score10, 500, 20, null);
			g2d.drawImage(score11, 520, 20, null);
			g2d.drawImage(score12, 540, 20, null);
			g2d.drawImage(score13, 560, 20, null);
			
			g2d.drawImage(gameover, 160, 80, null);
		}
	}
	
	public void update(Graphics g)
	{
		Graphics offgc;
		Image offscreen = null;
		
		offscreen = createImage(640,480);
		offgc = offscreen.getGraphics();
		
		offgc.setColor(getBackground());
		offgc.fillRect(0,0,640,480);
		offgc.setColor(getForeground());
		
		paint(offgc);
		
		g.drawImage(offscreen,0,0,this);
	}
	
	private class AL extends KeyAdapter
	{		
		public void keyReleased(KeyEvent e)
		{
			if(state == 1)
			{
			}
			else if(state == 2)
			{
				champ[sel].keyReleased(e);
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					champ[sel].endAttack();
				}
			}
		}
		
		public void keyPressed(KeyEvent e)
		{
			if(state == 0)
			{
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					state = 1;
					repaint();
				}
			}
			else if(state == 1)
			{
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					sel = 0;
					repaint();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					sel = 1;
					repaint();
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					state = 2;
				}
			}
			else if(state == 2)
			{
				champ[sel].keyPressed(e);
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					if(attackTime == 0)
					{
						champ[sel].attack();
						if(enemy.getX() - champ[sel].left < 180 && enemy.isAlive())
						{
							enemy.die();
							champ[sel].givePoints();
							snailTime = System.currentTimeMillis();

						}
						attackTime = System.currentTimeMillis();
					}
					else
					{
						long attackTime2 = System.currentTimeMillis();
						if(attackTime2 - attackTime >= 1000)
						{
							champ[sel].attack();
							if(enemy.getX() - champ[sel].left < 180 && enemy.isAlive())
							{
								enemy.die();
								champ[sel].givePoints();
								snailTime = System.currentTimeMillis();
							}
							attackTime = System.currentTimeMillis();
						}
					}
				}
			}
		}
	}
	
	boolean h = false;
	boolean done = false;
	
	public void cycle()
	{
		if(h == false)
			v = v - 6;
		if(v == 60)
			h = true;
		if(h == true && v <= 180)
		{
			v = v + 6;
			if(v == 180)
				done = true;
		}
	}
	
	public void wait (int n)
	{
		long t0,t1;
		t0=System.currentTimeMillis();
		do
		{
			t1=System.currentTimeMillis();
		} while (t1-t0<n);
	}
	
	public void run()
	{
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		while(done == false)
		{
			cycle();
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 10 - timeDiff;
			
			if(sleep < 0)
				sleep = 2;
			try
			{
				Thread.sleep(sleep);
			}
			catch(InterruptedException e)
			{
				System.out.println("interrupted");
			}
			
			beforeTime = System.currentTimeMillis();
		}
		done = false;
		h = false;
		done2 = false;
	}
}
