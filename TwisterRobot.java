import ShefRobot.*;

public class TwisterRobot {
  Robot shefRobot = new Robot();
  Motor leftMotor = shefRobot.getLargeMotor(Motor.Port.D);
  Motor rightMotor = shefRobot.getLargeMotor(Motor.Port.A);
  Speaker speaker = shefRobot.getSpeaker();
  ColorSensor colorSensor = shefRobot.getColorSensor(Sensor.Port.S1);
  //GyroSensor gyroSensor = shefRobot.getGyroSensor();
  
  public void setSpeed(int speed)
  {
  	leftMotor.setSpeed(speed);
  	rightMotor.setSpeed(speed);
  }

  public void moveForward()
  {
  	leftMotor.forward();
  	rightMotor.forward();
  }

  public void moveBackward()
  {
     leftMotor.backward();
     rightMotor.backward();
  }

  public void stop()
  {
  	leftMotor.stop();
  	rightMotor.stop();
  }

  public void findGrid()
  {
  	setSpeed(100);
  	System.out.println("hello i am white");
  	while(colorSensor.getColor() == ColorSensor.Color.WHITE)
      moveForward();
  }
/*
  public void navGrid() 
  { 
    setSpeed(100);
    if(colorSensor.getColor()!=ColorSensor.Color.WHITE) {
      System.out.println("hello i am black");
      moveForward();
      navGrid();
    }
    else {
     	System.out.println("hello i am white 2");
     	rightMotor.stop();
     	leftMotor.forward();
     	navGrid();
    }
  }
  */
  
   public void navGrid() 
  { 
    setSpeed(100);
    if(colorSensor.getColor()!=ColorSensor.Color.WHITE) {
      System.out.println("hello i am black");
		 if(colorSensor.getColor()==ColorSensor.Color.BLACK) {
		  moveForward();
		  navGrid();
		 }
		 /*else {
		  leftMotor.stop(); //break from method
		  rightMotor.forward();
		  navGrid();
     }*/
    }
    else {
     	System.out.println("hello i am white 2");
     	moveBackward();
     	navGrid();
    }
  }
  
  public void navColour() 
  {
  	setSpeed(100);
    if(colorSensor.getColor()!= ColorSensor.Color.WHITE) {
    	if (colorSensor.getColor()!= ColorSensor.Color.BLACK){
    		System.out.println("hello i am on a colour circle");
    		moveForward();
    		navColour();
		 }
		 else {
		 	 System.out.println("hello i have found black");
		 	 navGrid();
		 }
	}
	else {
		System.out.println("hello i am white 3");
		rightMotor.stop();
		leftMotor.forward();
		navColour();
		}	
  }  
  
  
 /* 
   public void navGrid() 
  { 
   setSpeed(50);
   if(colorSensor.getColor()!=ColorSensor.Color.WHITE){
    moveForward(); 
   }
   else if (colorSensor.getColor()==ColorSensor.Color.BLUE){
   	System.out.println(colorSensor.getColor());
   	stop();
   }
   	
   else {
   	System.out.println(colorSensor.getColor());
   	rotateRight(100);
   	moveForward();
   	navGrid();
	}
  }*/
 
  public void rotateLeft(int rotationSpeed)
  {
  	rightMotor.setSpeed(rotationSpeed);
  	rightMotor.forward();
  }
  
  public void rotateRight(int rotationSpeed)
  {
  	leftMotor.setSpeed(rotationSpeed);
  	leftMotor.forward();
  }
  
  public static void main(String[] args)
  {
  	TwisterRobot robo = new TwisterRobot();
  	robo.findGrid();
  	robo.navGrid();
  	robo.navColour();
  	robo.stop();
  }
}

