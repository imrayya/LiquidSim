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
package sim.force;

/**
 *
 * @author Kareem Horstink
 * @version 0.1
 */
public class Force2D extends Force {

    public Force2D(double[] vector) {
        super(vector);
    }

    @Override
    public Force multi(double t) {
        double[] newVector = new double[2];
        System.arraycopy(getVector(), 0, newVector, 0, newVector.length);
        for (int i = 0; i < getVector().length; i++) {
            newVector[i] *= t;
        }
        return new Force2D(newVector);
    }

    @Override
    public String toString() {
        return "Force - " + "x: " + getVector(0) + "; y: " + getVector(1);
    }

    public double angle() {
        return Math.asin(getVector(0) / getVector(1));
    }

    @Override
    public Force reflect(double e) {
        return new Force2D(new double[]{(getVector(0) * -1) * e, (getVector(1) * -1) * e});
    }

    @Override
    public Force add(Force f) {
        return new Force2D(new double[]{getVector(0) + f.getVector(0), getVector(1) + f.getVector(1)});
    }

}
