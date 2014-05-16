package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Champ {

	int x, dx, y, dy, nx2, nx, left;
	Image still, reverse;
	int sel;
	int[] score = new int[4];
	int lives;
	int level;
	
	boolean isMoving;
	boolean maxScore;
	
	int inc = 1; //for incrementing the walk pic
	
	ImageIcon standright0 = new ImageIcon("C:/Dig/standright0.png");
	ImageIcon standleft0 = new ImageIcon("C:/Dig/standleft0.png");
	ImageIcon attack0 = new ImageIcon("C:/Dig/attack0.png");
	ImageIcon walkleft0 = new ImageIcon("C:/Dig/walkleft0.gif");
	ImageIcon walkright0 = new ImageIcon("C:/Dig/walkright0.gif");
	ImageIcon dead0 = new ImageIcon("C:/Dig/dead0.gif");
	ImageIcon right01 = new ImageIcon("C:/Dig/right01.png");
	ImageIcon right02 = new ImageIcon("C:/Dig/right02.png");
	ImageIcon right03 = new ImageIcon("C:/Dig/right03.png");
	ImageIcon right04 = new ImageIcon("C:/Dig/right04.png");
	ImageIcon right05 = new ImageIcon("C:/Dig/right05.png");
	ImageIcon right06 = new ImageIcon("C:/Dig/right06.png");
	ImageIcon right07 = new ImageIcon("C:/Dig/right07.png");
	ImageIcon right08 = new ImageIcon("C:/Dig/right01.png");
	ImageIcon left01 = new ImageIcon("C:/Dig/left01.png");
	ImageIcon left02 = new ImageIcon("C:/Dig/left02.png");
	ImageIcon left03 = new ImageIcon("C:/Dig/left03.png");
	ImageIcon left04 = new ImageIcon("C:/Dig/left04.png");
	ImageIcon left05 = new ImageIcon("C:/Dig/left05.png");
	ImageIcon left06 = new ImageIcon("C:/Dig/left06.png");
	ImageIcon left07 = new ImageIcon("C:/Dig/left07.png");
	ImageIcon left08 = new ImageIcon("C:/Dig/left08.png");
	
	ImageIcon standright1 = new ImageIcon("C:/Dig/standright1.png");
	ImageIcon standleft1 = new ImageIcon("C:/Dig/standleft1.png");
	ImageIcon attack1 = new ImageIcon("C:/Dig/attack1.png");
	ImageIcon walkleft1 = new ImageIcon("C:/Dig/walkleft0.gif");
	ImageIcon walkright1 = new ImageIcon("C:/Dig/walkright0.gif");
	ImageIcon dead1 = new ImageIcon("C:/Dig/dead1.gif");
	ImageIcon right11 = new ImageIcon("C:/Dig/right11.png");
	ImageIcon right12 = new ImageIcon("C:/Dig/right12.png");
	ImageIcon right13 = new ImageIcon("C:/Dig/right13.png");
	ImageIcon right14 = new ImageIcon("C:/Dig/right14.png");
	ImageIcon right15 = new ImageIcon("C:/Dig/right15.png");
	ImageIcon right16 = new ImageIcon("C:/Dig/right16.png");
	ImageIcon right17 = new ImageIcon("C:/Dig/right17.png");
	ImageIcon right18 = new ImageIcon("C:/Dig/right11.png");
	ImageIcon left11 = new ImageIcon("C:/Dig/left11.png");
	ImageIcon left12 = new ImageIcon("C:/Dig/left12.png");
	ImageIcon left13 = new ImageIcon("C:/Dig/left13.png");
	ImageIcon left14 = new ImageIcon("C:/Dig/left14.png");
	ImageIcon left15 = new ImageIcon("C:/Dig/left15.png");
	ImageIcon left16 = new ImageIcon("C:/Dig/left16.png");
	ImageIcon left17 = new ImageIcon("C:/Dig/left17.png");
	ImageIcon left18 = new ImageIcon("C:/Dig/left11.png");
	
	
	public void move()
	{
		if(this.dx != -2)
		{
			if(this.left + this.dx <= 250)
				this.left += this.dx;
			else
			{
				this.x = this.x + this.dx;
				this.nx2 = this.nx2 + this.dx;
				this.nx = this.nx + this.dx;
			}
		}
		else
		{
			if(this.left + this.dx > 0)
				this.left = this.left + this.dx;
		}
	}
	
	public void attack()
	{
		if(this.sel == 0) this.still = attack0.getImage();
		else this.still = attack1.getImage();
	}
	
	public void endAttack()
	{
		if(this.sel == 0) this.still = standright0.getImage();
		else this.still = standright1.getImage();
	}
	
	public void givePoints()  //TODO: 5 for snails, 10 for worms
	{
		if(!maxScore && this.level == 1)
		{
			this.score[2] += 5;
			if(this.score[2] == 10) this.score[2] = 0;
			if(this.score[2] == 0) 
			{
				this.score[1] += 1;
				if(this.score[1] == 10) this.score[1] = 0;
				if(this.score[1] == 0)
					{
						this.score[0] += 1;
						if(this.score[0] == 10)
						{
							for(int i=0; i<4; i++) this.score[i] = 9;
							maxScore = true;
						}
					}
			}
		}
		if(!maxScore && this.level == 2)
		{
			this.score[1] += 1;
			if(this.score[1] == 10) this.score[1] = 0;
			if(this.score[1] == 0)
				{
					this.score[0] += 1;
					if(this.score[0] == 10)
					{
						for(int i=0; i<4; i++) this.score[i] = 9;
						maxScore = true;
					}
				}
		}
		if(!maxScore && this.level == 3)
		{
			this.score[1] += 2;
			if(this.score[1] == 10) this.score[1] = 0;
			if(this.score[1] == 11) this.score[1] = 1;
			if(this.score[1] == 0)
			{
				this.score[0] += 1;
				if(this.score[0] == 10)
				{
					for(int i=0; i<4; i++) this.score[i] = 9;
					maxScore = true;
				}
			}
			if(this.score[1] == 1)
			{
				this.score[0] += 1;
				if(this.score[0] == 10)
				{
					for(int i=0; i<4; i++) this.score[i] = 9;
					maxScore = true;
				}
			}
		}
	}
	
	public void die()
	{
		this.lives--;
		
		if(this.sel == 0) this.still = dead0.getImage();
		else this.still = dead1.getImage();
	}
	
	public void respawn()
	{
		this.x = 70;
		this.dx = 0;
		this.left = 50;
		this.y = 250;
		this.dy = 0;
		this.nx2 = 620;
		this.nx = 0;
		if(this.sel == 0) this.still = standright0.getImage();
		else this.still = standright1.getImage();
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getnX()
	{
		return this.nx;
	}
	
	public int getnX2()
	{
		return this.nx2;
	}
	
	public int getdx()
	{
		return this.dx;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getdY()
	{
		return this.dy;
	}
	
	public Image getImage()
	{
		return this.still;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
		{
			dx = -2;
			this.isMoving = true;
			switch(inc/2)
			{
			case 0:
				if(this.sel == 0) this.still = left01.getImage();
				else this.still = left11.getImage();
				break;
			case 1:
				if(this.sel == 0) this.still = left02.getImage();
				else this.still = left12.getImage();
				break;
			case 2:
				if(this.sel == 0) this.still = left03.getImage();
				else this.still = left13.getImage();
				break;
			case 3:
				if(this.sel == 0) this.still = left04.getImage();
				else this.still = left14.getImage();
				break;
			case 4:
				if(this.sel == 0) this.still = left05.getImage();
				else this.still = left15.getImage();
				break;
			case 5:
				if(this.sel == 0) this.still = left06.getImage();
				else this.still = left16.getImage();
				break;
			case 6:
				if(this.sel == 0) this.still = left07.getImage();
				else this.still = left17.getImage();
				break;
			case 7:
				if(this.sel == 0) this.still = left08.getImage();
				else this.still = left18.getImage();
				break;
			}
		}
		if(key == KeyEvent.VK_RIGHT)
		{
			dx = 2;
			this.isMoving = true;
			switch(inc/2)
			{
			case 0:
				if(this.sel == 0) this.still = right01.getImage();
				else this.still = right11.getImage();
				break;
			case 1:
				if(this.sel == 0) this.still = right02.getImage();
				else this.still = right12.getImage();
				break;
			case 2:
				if(this.sel == 0) this.still = right03.getImage();
				else this.still = right13.getImage();
				break;
			case 3:
				if(this.sel == 0) this.still = right04.getImage();
				else this.still = right14.getImage();
				break;
			case 4:
				if(this.sel == 0) this.still = right05.getImage();
				else this.still = right15.getImage();
				break;
			case 5:
				if(this.sel == 0) this.still = right06.getImage();
				else this.still = right16.getImage();
				break;
			case 6:
				if(this.sel == 0) this.still = right07.getImage();
				else this.still = right17.getImage();
				break;
			case 7:
				if(this.sel == 0) this.still = right08.getImage();
				else this.still = right18.getImage();
				break;
			}
		}
		if(key == KeyEvent.VK_UP)
		{
			dy = 2;
			this.isMoving = true;
		}
		if(key == KeyEvent.VK_SPACE)
		{
			if(this.sel == 0) this.still = attack0.getImage();
			else this.still = attack1.getImage();
		}
		
		inc++;
		if(inc == 14) inc = 1;
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
		{
			dx = 0;
			this.isMoving = false;
			if(this.sel == 0) this.still = standleft0.getImage();
			else this.still = standleft1.getImage();
		}
		if(key == KeyEvent.VK_RIGHT)
		{
			dx = 0;
			this.isMoving = false;
			if(this.sel == 0) this.still = standright0.getImage();
			else this.still = standright1.getImage();
		}
		if(key == KeyEvent.VK_UP)
		{
			this.isMoving = false;
			dy = 0;
		}
		if(key == KeyEvent.VK_SPACE)
		{

			if(this.sel == 0) this.still = standright0.getImage();
			else this.still = standright1.getImage();

		}
		
		inc = 1;
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
}

class Shovel extends Champ
{
	public Shovel()
	{
		this.x = 70;
		this.left = 50;
		this.y = 250;
		this.nx2 = 620;
		this.nx = 0;
		this.sel = 0;
		for(int i=0; i<4; i++) this.score[i] = 0;
		this.lives = 2;
		this.still = standright0.getImage();
		this.isMoving = false;
		this.maxScore = false;
		this.level = 1;
	}
}

class Pick extends Champ
{
	public Pick()
	{
		this.x = 70;
		this.left = 50;
		this.y = 250;
		this.nx2 = 620;
		this.nx = 0;
		this.sel = 1;
		for(int i=0; i<4; i++) this.score[i] = 0;
		this.lives = 2;
		this.still = standright1.getImage();
		this.isMoving = false;
		this.maxScore = false;
		this.level = 1;
	}
}
