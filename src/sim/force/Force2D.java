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

import sim.angle.Angle;

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

    public Angle angle() {
        if (Math.signum(getVector(0)) == 1 && Math.signum(getVector(1)) == 1) {
            return new Angle(Math.atan(getVector(1) / getVector(0)));
        }

        if (Math.signum(getVector(0)) == -1 && Math.signum(getVector(1)) == 1) {
            return new Angle(Math.atan(getVector(1) / getVector(0)) + 2 * Math.PI);
        }

        if (Math.signum(getVector(0)) == -1 && Math.signum(getVector(1)) == -1) {
            return new Angle(Math.atan(getVector(1) / getVector(0)) + Math.PI);
        }

        if (Math.signum(getVector(0)) == 1 && Math.signum(getVector(1)) == -1) {
            return new Angle(Math.atan(getVector(1) / getVector(0)) + Math.PI);
        }
        return new Angle(0);
    }

    @Override
    public Force reflect(double e) {
        return new Force2D(new double[]{(getVector(0) * -1) * e, (getVector(1) * -1) * e});
    }

    @Override
    public Force add(Force f) {
        return new Force2D(new double[]{getVector(0) + f.getVector(0), getVector(1) + f.getVector(1)});
    }

    public static void main(String[] args) {
//        System.out.println(Math.sin(Math.PI/2));
        Force2D f = new Force2D(new double[]{1, 0});
        System.out.println(f);
        f = (Force2D) f.reflect(1, f.angle());
        System.out.println(f);
    }

    @Override
    public Force reflect(double e, Angle a) {
        double h = Math.sqrt(Math.pow(getVector(0), 2) + Math.pow(getVector(1), 2));
        System.out.println(h);
        return new Force2D(new double[]{
            h * Math.sin(2 * Math.PI - a.getX()) * e,
            h * Math.cos(2 * Math.PI - a.getX()) * e});

    }

}
