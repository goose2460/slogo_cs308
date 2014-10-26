package commands;

import javafx.geometry.Point2D;
import MovementAndImageAPI.*;
import MovementAndImageAPI.src.TurtleHandler;

public class MovementCommand extends CommandsTurtle { 
    public enum CommandType {ROTATE, GO_FORWARD, GO_BACK, SET_POSITION, CLEAR_SCREEN, SET_HEADING, SET_TOWARDS};
    private CommandType command;
    private double[] value = {0.0};
    private static double RADIAL_CONVERT = 180/Math.PI;
    
    public MovementCommand(TurtleHandler turtleHandler, CommandType command, double[] value) {
        super(turtleHandler);
        this.command = command;
        this.value = value;
    }
            
    @Override
    public void execute() {
        switch(command) {
            case ROTATE:
                turtleHandler.updateTurtleOrientation(value[0]);
                break;
            case GO_FORWARD:
                turtleHandler.updateTurtleLocation(value[0]);
                break;
            case GO_BACK:
                turtleHandler.updateTurtleLocation(-value[0]);
                break;
            case SET_POSITION:
                turtleHandler.updateTurtleAbsoluteLocation(new Point2D(value[0],value[1]));
                break;
            case CLEAR_SCREEN:
                turtleHandler.clearLines();
                turtleHandler.setPenPosition(0);
                turtleHandler.updateTurtleAbsoluteLocation(new Point2D(0,0));
                turtleHandler.setPenPosition(1);
                break;
            case SET_HEADING:
                turtleHandler.updateTurtleAbsoluteOrientation(value[0]);
                break;
            case SET_TOWARDS:
                Point2D turtlePos = turtleHandler.getTurtleLocationInCanvas();
                Point2D towardsPos = new Point2D(value[0],value[1]);
                double deltaY = towardsPos.getY() - turtlePos.getY();
                double deltaX = towardsPos.getX() - turtlePos.getX();
                double angle = Math.atan2(deltaY, deltaX) * RADIAL_CONVERT;
                turtleHandler.updateTurtleAbsoluteOrientation(angle);
                break;
        }
    }
}
