/*
 * Copyright (C) 2015 Kareem Horstink
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package sim.shape;

import java.awt.Graphics2D;
import java.util.Objects;
import sim.shape.angle.Angle;
import sim.shape.position.Position2D;

/**
 * A simple line implementation
 *
 * @author Kareem Horstink
 */
public class Line2D {

    private Position2D point1 = new Position2D();
    private Position2D point2 = new Position2D();

    /**
     * Default constructor that creates a line with 0 length and is on the
     * origin
     */
    public Line2D() {
    }

    /**
     * Creates a line that in position wanted
     *
     * @param point1 The first point of the line
     * @param point2 The second point of the line
     */
    public Line2D(Position2D point1, Position2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * Get the value of point1
     *
     * @return the value of point1
     */
    public Position2D getPoint1() {
        return point1;
    }

    /**
     * Set the value of point1
     *
     * @param point1 new value of point1
     */
    public void setPoint1(Position2D point1) {
        this.point1 = point1;
    }

    /**
     * Get the value of point2
     *
     * @return the value of point2
     */
    public Position2D getPoint2() {
        return point2;
    }

    /**
     * Set the value of point2
     *
     * @param point2 new value of point2
     */
    public void setPoint2(Position2D point2) {
        this.point2 = point2;
    }

    /**
     * Gets the absolute height of the line in the x axis
     *
     * @return The absolute height of the line
     */
    public double getXheight() {
        return Math.abs(point1.getX() - point2.getX());
    }

    /**
     * Gets the absolute height of the line in the y axis
     *
     * @return The absolute height of the line
     */
    public double getYheight() {
        return Math.abs(point1.getY() - point2.getY());
    }

    /**
     * Get the length of the line (absolute)
     *
     * @return The absolute length of the line
     */
    public double getLength() {
        //a^2 + b^2 = c^2 
        return Math.sqrt(Math.pow(getXheight(), 2) + Math.pow(getYheight(), 2));
    }

    /**
     * Gets the angle of a line assuming point1 is higher and to the right of
     * point2 (point1 lies on the first quadrant [unit circle] of point 2)
     *
     * @return The angle of the line based on the unit circle
     */
    public Angle getAngleSimple() {
        return new Angle(this);
    }

    public Angle getAngleComplicated() {
        double PI = Math.PI;
        Angle angle = getAngleSimple();
        switch (quadrant()) {
            case 1:
                break;
            case 2:
                angle.setX(PI - angle.getX());
                break;
            case 3:
                angle.setX(PI + angle.getX());
                break;
            case 4:
                angle.setX(2 * PI - angle.getX());
                break;
        }
        return angle;
    }

    public void draw(Graphics2D g) {
        g.drawLine((int) point1.getX(), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
    }

    public byte quadrant() {
        if (point1.isHigherThan(point2) && point2.isLefterThan(point1)) {
            return 1;
        } else if (point1.isHigherThan(point2)) {
            return 2;
        } else if (point1.isLefterThan(point2)) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Checks wither 2 lines intersects
     *
     * @param line The other line to check
     * @return True if they intersect
     */
    public boolean isIntersecting(Line2D line) {
        Position2D p1 = point1;
        Position2D p2 = point2;
        Position2D q1 = line.point1;
        Position2D q2 = line.point2;
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        return o1 != o2 && o3 != o4;
    }

    private static int orientation(Position2D p, Position2D q, Position2D r) {
        //copied from http://stackoverflow.com/questions/25830932/how-to-find-if-two-line-segments-intersect-or-not-in-java
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0.0) {
            return 0; // colinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    public double distance(Position2D point) {
        //Copied from http://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();
        double x3 = point.getX();
        double y3 = point.getY();

        double px = x2 - x1;
        double py = y2 - y1;
        double temp = (px * px) + (py * py);
        double u = ((x3 - x1) * px + (y3 - y1) * py) / (temp);
        if (u > 1) {
            u = 1;
        } else if (u < 0) {
            u = 0;
        }
        double x = x1 + u * px;
        double y = y1 + u * py;

        double dx = x - x3;
        double dy = y - y3;
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.point1);
        hash = 89 * hash + Objects.hashCode(this.point2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Line2D other = (Line2D) obj;
        if (!Objects.equals(this.point1, other.point1)) {
            return false;
        }
        if (!Objects.equals(this.point2, other.point2)) {
            return false;
        }
        return true;
    }

}
