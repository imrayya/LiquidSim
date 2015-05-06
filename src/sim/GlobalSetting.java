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
package sim;

/**
 * Holds all the information that the simulation will contain
 *
 * @author Kareem Horstink
 * @version 0.2
 */
public class GlobalSetting {

    private static Double deltaT;
    private static double gravity = -9.8;

    /**
     * Get the value of deltaT
     *
     * @return the value of deltaT
     */
    public static Double getDeltaT() {
        return deltaT;
    }

    /**
     * Set the value of deltaT
     *
     * @param deltaT new value of deltaT
     */
    public static void setDeltaT(Double deltaT) {
        GlobalSetting.deltaT = deltaT;
    }

    /**
     * Get the value of gravity
     *
     * @return the value of gravity
     */
    public static double getGravity() {
        return gravity;
    }

    /**
     * Set the value of gravity
     *
     * @param gravity new value of gravity
     */
    protected static void setGravity(double gravity) {
        GlobalSetting.gravity = gravity;
    }

}
