import ShefRobot.*;

public class TwisterRobot {
	Robot shefRobot = new Robot();
	Motor leftMotor = shefRobot.getLargeMotor(Motor.Port.D);
	Motor rightMotor = shefRobot.getLargeMotor(Motor.Port.A);
	Speaker speaker = shefRobot.getSpeaker();
	ColorSensor colorSensor = shefRobot.getColorSensor(Sensor.Port.S1);

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
		System.out.println(colorSensor.getColor());
		// Move forward while outsite the grid
		while(colorSensor.getColor() == ColorSensor.Color.WHITE)
			moveForward();
		stop();
		// After finding the grid, check if it enters on the black line or
		// on a color.
		if(colorSensor.getColor() == ColorSensor.Color.BLACK)
			// If it's a black line turn 90 degrees to match the line and move forward
			calibrateSide();
		//else
			//moveColor();
	}
	public void calibrateGrid()
	{
		moveBackward();
		sleep(1000);
		stop();
		rotateLeft();
		while(colorSensor.getColor() == ColorSensor.Color.BLACK)
			moveForward();
		stop();

	}
	public void rotateLeft()
	{
		rightMotor.rotateTo(90);
	}
	public void rotateRight()
	{
		leftMotor.rotateTo(90);
	}


	public static void main(String[] args)
	{
		TwisterRobot robo = new TwisterRobot();
		robo.findGrid();
		robo.stop();
	}
}
