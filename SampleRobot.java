import ShefRobot.*;

/**
 *
 * @author sdn
 */
public class SampleRobot {

    /**
     * @param args the command line arguments
         */
    public static void main(String[] args) {

        //Create a robot object to use and connect to it
        Robot myRobot = new Robot();
        //The robot is made of components which are themselves objects.
        //Create references to them as useful shortcuts
        Motor leftMotor = myRobot.getLargeMotor(Motor.Port.B);
        Motor rightMotor = myRobot.getLargeMotor(Motor.Port.C);
        Speaker speaker = myRobot.getSpeaker();
        ColorSensor colorSensor = myRobot.getColorSensor(Sensor.Port.S1);
        System.out.println(colorSensor.getColor());
        if(colorSensor.getColor() == ColorSensor.Color.WHITE)
        {
          System.out.println("hell yeah");
          //Go Forwards
          leftMotor.setSpeed(150);
          rightMotor.setSpeed(150);
          leftMotor.forward();
          rightMotor.forward();
      }

        //Stop
        leftMotor.stop();
        rightMotor.stop();

        //Disconnect from the Robot
        myRobot.close();

    }

}
