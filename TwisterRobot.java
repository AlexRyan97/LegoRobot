import ShefRobot.*;

public class TwisterRobot {
	Robot shefRobot = new Robot();
	Motor leftMotor = shefRobot.getLargeMotor(Motor.Port.D);
	Motor rightMotor = shefRobot.getLargeMotor(Motor.Port.A);
	Speaker speaker = shefRobot.getSpeaker();
	ColorSensor colorSensor = shefRobot.getColorSensor(Sensor.Port.S1);
	Boolean rotation = true;

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
	/*
	public void findGrid()
	{
		setSpeed(100);
		System.out.println(colorSensor.getColor());
		// Move forward while outsite the grid
		while(colorSensor.getColor() == ColorSensor.Color.WHITE)
			moveForward();
		stop();
		// After finding the grid, check if it enters on the black line or
		// on a color.
		if(colorSensor.getColor() == ColorSensor.Color.BLACK)
			// If it's a black line turn 90 degrees to match the line and move forward
			{
				
				moveBackward();
				shefRobot.sleep(200);
				stop();
				rotateLeft(335);
				stop();
				calibrate();
			}
		//else
			//moveColor();
	}*/
	public void findGridX() {
		setSpeed(100);
		System.out.println(colorSensor.getColor());
		// Move forward while outsite the grid
		while(colorSensor.getColor() == ColorSensor.Color.WHITE)
			moveForward();
		stop();
		// After finding the grid, check if it enters on the black line or
		// on a color.
		if(colorSensor.getColor() == ColorSensor.Color.BLACK || colorSensor.getColor() != ColorSensor.Color.BROWN)
			// If it's a black line turn 90 degrees to match the line and move forward
			{
				System.out.println("ready for the turn");
				moveBackward();
				shefRobot.sleep(200);
				stop();
				rotateLeft(330);
				stop();
				calibrateX();
			}
		else {
			System.out.println("colour found: " + colorSensor.getColor());
			moveColor();
			}
	}
	
	public void calibrateX()
	{
		moveForward();
		sleep(1);
		stop();
		if(colorSensor.getColor() == ColorSensor.Color.WHITE) {
			if(rotation == true)
			{	
				moveBackward();
				sleep(1);
				rotateLeft(20);
				rotation = false;
			}
			else
			{
				moveBackward();
				sleep(1);
				rotateRight(20);
				rotation = true;
			}
		}
		else if(colorSensor.getColor() != ColorSensor.Color.BLACK)
			{
				moveColor();
			}
			
		calibrateX();
	}
	public void calibrate()
	{
		moveForward();
		sleep(1);
		if(colorSensor.getColor() == ColorSensor.Color.BLACK)
			calibrate();
		else
			if(colorSensor.getColor() == ColorSensor.Color.WHITE)
				rotate1();

	}
	public void rotate1()
	{
		rotateLeft(100);
		if(colorSensor.getColor() == ColorSensor.Color.BLACK)
			calibrate();
		else
		{
			rotateRight(100);
			if(colorSensor.getColor() == ColorSensor.Color.BLACK)
				calibrate();
			else
				rotate1();
		}
	}
	/*public void calibrateSide()
	{
		moveBackward();
		shefRobot.sleep(200);
		stop();
		rotateLeft();
		stop();
		while(colorSensor.getColor() == ColorSensor.Color.BLACK)
			moveForward();
		stop();
		moveColor();
	}*/
	public void moveColor()
	{
		
		if(colorSensor.getColor() != ColorSensor.Color.BLACK && colorSensor.getColor() != ColorSensor.Color.WHITE)
		{	
			System.out.println("On a colour " + colorSensor.getColor());
			moveForward();
			stop();
			moveColor();
		}
		else
			if(colorSensor.getColor() == ColorSensor.Color.BLACK)
			{
				System.out.println("On a ---- " + colorSensor.getColor());
				calibrateX();
			}
			else 
			{
				while(colorSensor.getColor() == ColorSensor.Color.WHITE)
				{
					System.out.println("On a -- " + colorSensor.getColor());
					//moveBackward();
					rotateRight(50);
					moveForward();
					sleep(1);
					
				}
				if(colorSensor.getColor() == ColorSensor.Color.BLACK)
				{
					System.out.println("On a --- " + colorSensor.getColor());
					moveBackward();
					shefRobot.sleep(200);
					stop();
					rotateLeft(335);
					stop();
					calibrateX();
				}
			}
			//moveColor();	
	}
	public void rotateLeft(int nr)
	{
		rightMotor.rotate(nr);
	}
	public void rotateRight(int nr)
	{
		leftMotor.rotate(nr);
	}
	public void sleep(int numberOfSeconds)
	{
		shefRobot.sleep(numberOfSeconds*1000);
	}

	public static void main(String[] args)
	{
		TwisterRobot robo = new TwisterRobot();
		robo.sleep(1);
		robo.findGridX();
		robo.stop();
	}
}
