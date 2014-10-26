package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * 
 * @author Eirika Sawh This class handles the actual position of the Turtle, but
 *         not updating the image.
 */
public class TurtleHandler {

	private Turtle mainTurtle = new Turtle();
	private ImageUpdater mainImageUpdater;

	public TurtleHandler(ImageUpdater imageUpdater) {
		mainImageUpdater = imageUpdater;
	}

	
	public double getOrientation(){
	    return mainTurtle.getOrientation();
	}
	
	public Point2D getTurtleLocation(){
	    return mainTurtle.getPoint();
	}
	
	public Point2D getTurtleLocationInCanvas(){
		Point2D turtlePoint = mainTurtle.getPoint();
		Point2D canvasSize = mainImageUpdater.getTurtleCanvasSize();
		return new Point2D(turtlePoint.getX() % canvasSize.getX(), turtlePoint.getY() % canvasSize.getY());
	}
	
	/**
	 * 
	 * @param translocation
	 *            the double amount of how many pixels to move forwards.
	 *            Positive = move forwards, negative = move backwards
	 */
	public void updateTurtleLocation(double translocation) {
		Point2D from = mainTurtle.getPoint();
		mainTurtle.updateLocation(translocation);
		Point2D to = mainTurtle.getPoint();
		moveAndDraw(from, to);
	}

	/**
	 * 
	 * @param newLocation
	 *            the location to instantly move to.
	 */
	public void updateTurtleAbsoluteLocation(Point2D newLocation) {
			Point2D from = mainTurtle.getPoint();
			mainTurtle.updateAbsoluteLocation(newLocation);
			Point2D to = mainTurtle.getPoint();
			moveAndDraw(from, to);
	}

	/**
	 * 
	 * @param from
	 *            The start point of the line
	 * @param to
	 *            The end point of the line, also where the Turtle image should
	 *            be moved to
	 */
	private void moveAndDraw(Point2D from, Point2D to) {
		mainImageUpdater.updateTurtleImage(to, mainTurtle.getImage());
		if(mainTurtle.getPenPosition() == 1)
			mainImageUpdater.drawLine(from, to);
	};

	/**
	 * 
	 * @param rotation
	 *            the amount of degrees to rotate the Turtle (clockwise)
	 */
	public void updateTurtleOrientation(double rotation) {
		mainTurtle.updateOrientation(rotation);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	}

	/**
	 * 
	 * @param newAngle
	 *            the orientation angle to immediately change to.
	 */
	public void updateTurtleAbsoluteOrientation(double newAngle) {
		mainTurtle.updateAbsoluteOrientation(newAngle);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	};

	/**
	 * 
	 * @param show 1 if should show the turtle, 0 if should hide the turtle
	 */
	public void showTurtle(int show){
		mainTurtle.show(show);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(), mainTurtle.getImage());
	}
	
	public void updateImage(Image newImage){
		mainTurtle.updateImage(newImage);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),  mainTurtle.getImage());
	}

	
	public void clearLines(){
		mainImageUpdater.clearLines();
	}
	
	public void setPenPosition(int penPosition){
		mainTurtle.setPenPosition(penPosition);
	}
	
}
