package com.vmoiseenko.Assignment7;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import acm.util.RandomGenerator;
public class Program extends GraphicsProgram 
{
	//set the constants for the objects
	private final int FORMWIDTH=400;
	private final int FORMHEIGHT=600;
	private final int BALLDIAMETER=10;
	private final int PADDLEWIDTH=60;
	private final int PADDLEHEIGHT=8;
	private final int PADDLEOFFSET=FORMHEIGHT-60;
	//set the class variables for ball vectors
	private double ballVX=3;
	private double ballVY;
	//declare the ball and paddle objects
	private GRect paddle;
	private GOval ball;
	
	//declare constants for brick
	private final int BRICKSPACE=4;
	private final int NUMBEROFBRICKS=10;
	private final int BRICKWIDTH=FORMWIDTH/NUMBEROFBRICKS-BRICKSPACE;
	private final int BRICKHEIGHT=8;
	private final int BRICKSTARTPOSITIONX=0;
	private final int BRICKSTARTPOSITIONY = 60;
	
	
	
	public void run(){
		setSize(FORMWIDTH, FORMHEIGHT);
		createPaddle();
		addKeyListeners();
		createBall();
		createBricks();
		waitForClick();
		moveBall();
		
	}
	
	private void createPaddle(){
		paddle = new GRect (FORMWIDTH/2-PADDLEWIDTH/2,PADDLEOFFSET, PADDLEWIDTH, PADDLEHEIGHT);
		paddle.setFillColor(Color.black);
		paddle.setFilled(true);
		add(paddle);
		}
	
	public void keyPressed(KeyEvent e){
		double x = paddle.getX();
		double y=paddle.getY();
		//in the switch I try to limit the movement of the
		//paddle to the form width but it doesn't work
		//something to troubleshoot in class
		switch (e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
		if(x!=FORMWIDTH){
		paddle.move(PADDLEWIDTH,0);
		}
		else
		{
		paddle.setLocation(FORMWIDTH-PADDLEWIDTH, PADDLEOFFSET);
		}
		break;
		case KeyEvent.VK_LEFT:
		if(x!=0){
		paddle.move(-PADDLEWIDTH,0);
		}
		else
		{
		paddle.setLocation(x+PADDLEWIDTH, PADDLEOFFSET);
		}
		break;
		default:
		break;
		}
		}
	
	private void createBall(){
		RandomGenerator rand = new RandomGenerator();
		ballVY=rand.nextDouble(1.0, 3.0);
		//position the ball in the center of the form
		ball=new GOval(FORMWIDTH/2-BALLDIAMETER/2,
		FORMHEIGHT/2-BALLDIAMETER/2,
		BALLDIAMETER,BALLDIAMETER);
		ball.setFillColor(Color.BLUE);
		ball.setFilled(true);
		add(ball);
		}
	
		private void moveBall(){
		boolean keepGoing=true;
		while(keepGoing){
				
		//this makes it so when the ball hits the edges
		//it reverse direction
		if(ball.getX()>=FORMWIDTH-BALLDIAMETER || ball.getX() <=0){
		ballVX=-ballVX;
		}
		//same for top and bottom
		if (ball.getY()>=FORMHEIGHT-BALLDIAMETER || ball.getY() <=0){
		ballVY = -ballVY;
		}
		//this checks for the location of the paddle. If it and the ball's
		//coordinates are the same, it bounces off the paddle
		if (getElementAt(ball.getX() + BALLDIAMETER, ball.getY()+BALLDIAMETER)== paddle){
		ballVY=-ballVY;
		}
		//if the ball goes below the paddle we exit the loop
		//and end the game
		if (ball.getY() > paddle.getY()){
		keepGoing=false;
		}
		//move the ball
		ball.move(ballVX, ballVY);
		//slight pause between positions
		//(actually pretty slow)
		pause(25);
		}
		}
		
		
		public void createBricks()
		{
			setSize(FORMWIDTH, FORMHEIGHT);
			int x=BRICKSTARTPOSITIONX;
			int y=BRICKSTARTPOSITIONY;

			brick = new GRect[NUMBEROFBRICKS][NUMBEROFBRICKS]; 
			for (int r=1; r < 11; r++){
				Color c = pickColor(r);
				for (int i=1;i<11;i++){
				GRect brick = new GRect(x,y,BRICKWIDTH,BRICKHEIGHT);
				brick.setFillColor(c);
				brick.setFilled(true);
				add(brick);
				x+=BRICKWIDTH + 4;
				}
				x=0;
				y+=BRICKHEIGHT + 4;
				}
		}
		
		
		private Color pickColor(int row)
		{
		Color c=Color.red;
		switch (row)
		{
		case 1:
		case 2:
		c=Color.red;
		break;
		case 3:
		case 4:
		c=Color.orange;
		break;
		case 5:
		case 6:
		c=Color.yellow;
		break;
		case 7:
		case 8:
		c=Color.green;
		break;
		case 9:
		case 10:
		c=Color.blue;
		break;
		default:
		c=Color.gray;
		break;
		}
		return c;
		}
}
