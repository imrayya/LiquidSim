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
package sim.collider;

import java.util.ArrayList;
import sim.shape.angle.Angle;
import sim.force.Force2D;
import sim.partical.Partical;
import sim.shape.Line2D;
import sim.shape.Rect2D;
import sim.shape.position.Position2D;

/**
 *
 * @author Kareem Horstink
 */
public class Collider2D_Rect extends Collider {

    private int x;

    private int y;

    private int w;

    private int h;

    public Collider2D_Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Collider2D_Rect(int x, int y, int w, int h, double e) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setElasticity(e);
    }

    public Rect2D getRect() {
        return new Rect2D(x, y, h, w);
    }

    @Override
    public boolean contains(Position2D p) {
        return new Rect2D(x, y, h, w).contains(p);
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the value of w
     *
     * @return the value of w
     */
    public int getW() {
        return w;
    }

    /**
     * Set the value of w
     *
     * @param w new value of w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Get the value of h
     *
     * @return the value of h
     */
    public int getH() {
        return h;
    }

    /**
     * Set the value of h
     *
     * @param h new value of h
     */
    public void setH(int h) {
        this.h = h;
    }

    @Override
    public Angle getAngle(Partical p) {
//        return f.angle().copy();
        Force2D f = (Force2D) p.getForce();

        //Gets the shape of the collider
        Rect2D box = getRect();

        //Creates a line based on the path of p (predicted)
        Position2D currentPos = p.getPosition();
        Position2D predictedPos = p.getPredicted();
        Line2D currentPredictionPath = new Line2D(currentPos, predictedPos);

        //May intersect more than one line
        ArrayList<Line2D> intersecting = new ArrayList();
        for (Line2D line : box.getLines()) {
            if (line.isIntersecting(currentPredictionPath)) {
                intersecting.add(line);
            }
        }

        //Find the line that closest to the current position of the particle
        double max = Double.MIN_VALUE;
        Line2D closest = intersecting.get(0);
        for (Line2D line2D : intersecting) {
            if (max < line2D.distance(currentPos)) {
                max = line2D.distance(currentPos);
                closest = line2D;
            }
        }
        //Check which boundary of the box the particle hits
        //Based on how Rect2D.getLine() is made
        Line2D[] rect = box.getLines();
        if (rect[0].equals(closest)) {
            return top(f.angle());
        }
        if (rect[1].equals(closest)) {
            return left(f.angle());
        }
        if (rect[2].equals(closest)) {
            return right(f.angle());
        }
        return bottom(f.angle());

    }

    private Angle left(Angle orginal) {
        if (orginal.quadrant() == 2) {
            return new Angle(Math.PI * 3 / 2d + Math.PI / 2d - orginal.getX());
        }
        return new Angle(Math.PI * 3 / 2d - orginal.getX() + Math.PI / 2d);
    }

    private Angle bottom(Angle orginal) {
        if (orginal.quadrant() == 3) {
            return new Angle(2 * Math.PI - (orginal.getX() - Math.PI));
        }
        return new Angle((2 * Math.PI - orginal.getX()) + Math.PI);
    }

    private Angle top(Angle orginal) {
        if (orginal.quadrant() == 2) {
            return new Angle(Math.PI / 2d - (orginal.getX() - Math.PI / 2d));
        }
        return new Angle(Math.PI / 2 + (Math.PI / 2 - orginal.getX()));
    }

    private Angle right(Angle orginal) {
        if (orginal.quadrant() == 1) {
            return new Angle(2 * Math.PI - orginal.getX());
        }
        return new Angle(2 * Math.PI - orginal.getX());
    }
}
