package sim.shape;

import sim.shape.position.Position2D;

/**
 * Represents a 2D rectangle that isn't rotated and only has right angles inside
 *
 * @author Kareem Horstink
 */
public class Rect2D {

    private double y = 0;
    private double width = 0;
    private double height = 0;
    private double x = 0;

    public Rect2D() {
    }

    public Rect2D(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    public Position2D getCenter() {
        return new Position2D(x + width / 2, y + height / 2);
    }

    /**
     * Gets the lines that make up the rectangle
     *
     * @return The lines that make up the rectangle
     */
    public Line2D[] getLines() {
        Line2D[] array = new Line2D[4];
        array[0] = new Line2D(new Position2D(x, y), new Position2D(x, y + height));
        array[1] = new Line2D(new Position2D(x, y), new Position2D(x + width, y));
        array[2] = new Line2D(new Position2D(x + width, y + height), new Position2D(x, y + height));
        array[3] = new Line2D(new Position2D(x + width, y + height), new Position2D(x + width, y));
        return array;
    }

    public Line2D[] renderLines(double canvasHeight) {
        Rect2D tmp = new Rect2D(x, canvasHeight - y, -height, width);
        return tmp.getLines();
    }

    public boolean contains(double x1, double y1) {
        return x1 > min(x, x + width) && max(x, x + width) > x1
                && y1 > min(y, y + height) && max(y, y + height) > y1;
    }

    public boolean contains(Position2D point) {
        return contains(point.getX(), point.getY());
    }
    
    

    /**
     * Encapsulation of Math.Max
     *
     * @param x
     * @param y
     * @return
     */
    private double max(double x, double y) {
        return Math.max(x, y);
    }

    /**
     * Encapsulation of Math.min
     *
     * @param x
     * @param y
     * @return
     */
    private double min(double x, double y) {
        return Math.min(x, y);
    }
}
