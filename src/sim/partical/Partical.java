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
package sim.partical;

import java.util.List;
import sim.Position;
import sim.force.Force;

/**
 *
 * @author Kareem Horstink
 */
public abstract class Partical {

    private double mass = 1;

    private double density = 1;

    private double temperature = 297.15;

    private double boilingPoint = 373.15;

    private double freezingPoint = 273.15;

    private Force force;

    private Force externalForce;

    private List<Partical> neighbors;

    public abstract void setPredicted();

    public abstract Position getPredicted();

    /**
     * Get the value of boilingPoint
     *
     * @return the value of boilingPoint
     */
    public double getBoilingPoint() {
        return boilingPoint;
    }

    /**
     * Set the value of boilingPoint
     *
     * @param boilingPoint new value of boilingPoint
     */
    public void setBoilingPoint(double boilingPoint) {
        this.boilingPoint = boilingPoint;
    }

    /**
     * Get the value of freezingPoint
     *
     * @return the value of freezingPoint
     */
    public double getFreezingPoint() {
        return freezingPoint;
    }

    /**
     * Set the value of freezingPoint
     *
     * @param freezingPoint new value of freezingPoint
     */
    public void setFreezingPoint(double freezingPoint) {
        this.freezingPoint = freezingPoint;
    }

    /**
     * Get the value of neighbors
     *
     * @return the value of neighbors
     */
    public List<Partical> getNeighbors() {
        return neighbors;
    }

    /**
     * Set the value of neighbors
     *
     * @param neighbors new value of neighbors
     */
    public void setNeighbors(List<Partical> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Get the value of mass
     *
     * @return the value of mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * Set the value of mass
     *
     * @param mass new value of mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Get the value of density
     *
     * @return the value of density
     */
    public double getDensity() {
        return density;
    }

    /**
     * Set the value of density
     *
     * @param density new value of density
     */
    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * Get the value of temperature
     *
     * @return the value of temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Set the value of temperature
     *
     * @param temperature new value of temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Get the value of externalForce
     *
     * @return the value of externalForce
     */
    public Force getExternalForce() {
        return externalForce;
    }

    /**
     * Set the value of externalForce
     *
     * @param externalForce new value of externalForce
     */
    public void setExternalForce(Force externalForce) {
        this.externalForce = externalForce;
    }

    /**
     * Get the value of force
     *
     * @return the value of force
     */
    public Force getForce() {
        return force;
    }

    /**
     * Set the value of force
     *
     * @param force new value of force
     */
    public void setForce(Force force) {
        this.force = force;
    }

}
