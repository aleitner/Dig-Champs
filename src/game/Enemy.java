package game;

import java.awt.*;

import javax.swing.ImageIcon;

public class Enemy {

	int x, dx = -2;
	int hp;
	boolean alive;
	
	public Enemy()
	{
		this.x = 600;
		this.alive = false;
		this.hp = 1;
	}
	
	public void spawn(int p)
	{
		this.alive = true;
		this.x = 600;
		this.hp = p;
	}
	
	public void die()
	{
		this.alive = false;
	}
	
	public void move(int p)
	{
		this.x = this.x + this.dx - p;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getdx()
	{
		return this.dx;
	}
	
	public boolean isAlive()
	{
		return this.alive;
	}
}
