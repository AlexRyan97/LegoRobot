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
  public void stop()
  {
    leftMotor.stop();
    rightMotor.stop();
  }
  public void findGrid()
  {
    setSpeed(100);
    System.out.println(colorSensor.getColor());
    while(colorSensor.getColor() == ColorSensor.Color.WHITE)
      moveForward();
  }
  public void navGrid()
  {
    if(colorSensor.getColor()==ColorSensor.Color.GREEN)
      System.exit(0);
    if(colorSensor.getColor()!=ColorSensor.Color.WHITE)
      moveForward();
    else
      rotateLeft(100);
    navGrid();
  }
  public void rotateLeft(int rotationSpeed)
  {
    leftMotor.setSpeed(rotationSpeed);
    leftMotor.forward();
  }

  public static void main(String[] args)
  {
    TwisterRobot robo = new TwisterRobot();
    robo.findGrid();
    robo.navGrid();
    robo.stop();
  }
}
