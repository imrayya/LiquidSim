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
 */
public class Force3D extends Force {

    public Force3D(double[] vector) {
        super(vector);
    }

    @Override
    public Force multi(double t) {
        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public Force reflect(double e) {
        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public Force add(Force f) {
        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public Force reflect(double e, Angle a) {
        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

}
