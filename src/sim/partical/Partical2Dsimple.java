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

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import sim.GlobalSetting;
import sim.collider.Collider;
import sim.force.Force;
import sim.force.Force2D;
import sim.position.Position;

/**
 *
 * @author Kareem Horstink
 */
public class Partical2Dsimple extends Partical {
    
    public Partical2Dsimple(Position p) {
        this.setPosition(p);
        setPredicted(new Position());
        resetExternalForce();
    }
    
    @Override
    public void setPredicted() {
        predictForce();
        Position tmp = new Position();
        tmp.setX(getPosition().getX() + getForce().multi(GlobalSetting.getDeltaT().doubleValue() / 1000).getVector(0));
        tmp.setY(getPosition().getY() + getForce().multi(GlobalSetting.getDeltaT().doubleValue() / 1000).getVector(1));
        setPredicted(tmp);
    }
    
    @Override
    public void setPosition(Position position) {
        if (position.getX() < 0 || position.getX() > GlobalSetting.getWidth()
                || position.getY() < 0 || position.getY() > GlobalSetting.getHeight()) {
            setKill(true);
        } else {
            super.setPosition(position);
        }
    }
    
    private void predictForce() {
        Force2D f = (Force2D) getForce();
        
        Gravity:
        {
            f.setVector(1, f.getVector(1) + (getMass() * (-GlobalSetting.getGravity() * (GlobalSetting.getDeltaT().doubleValue() / 1000))));
        }
        
        ExternalForce:
        {
            
            f = (Force2D) (f.add(getExternalForce()));
        }
        setForce(f);
    }
    
    @Override
    public void handleCollision(Collider c) {
        setForce(getForce().reflect(c.getElasticity()));
    }
    
    @Override
    public void handleCollision(Partical p) {
        double x1 = ((getMass() - p.getMass()) / (getMass() + p.getMass())) * getForce().getVector(0)
                + ((2 * p.getMass()) / (getMass() + p.getMass())) * p.getForce().getVector(0);
        double y1 = ((getMass() - p.getMass()) / (getMass() + p.getMass())) * getForce().getVector(1)
                + ((2 * p.getMass()) / (getMass() + p.getMass())) * p.getForce().getVector(1);
        
        double x2 = ((2 * getMass()) / getMass() + p.getMass()) * getForce().getVector(0)
                + ((p.getMass() - getMass()) / (getMass() + p.getMass())) * p.getForce().getVector(0);
        double y2 = ((2 * getMass()) / getMass() + p.getMass()) * getForce().getVector(1)
                + ((p.getMass() - getMass()) / (getMass() + p.getMass())) * p.getForce().getVector(1);
        
        setExternalForce(new Force2D(new double[]{x1, y1}));
        p.setExternalForce(new Force2D(new double[]{x2, y2}));
    }
    
    @Override
    public boolean detectCollision(Partical p) {
        return new Ellipse2D.Double(
                getPosition().getX() - GlobalSetting.getParticalSize() / 2,
                getPosition().getY() - GlobalSetting.getParticalSize() / 2,
                GlobalSetting.getParticalSize(),
                GlobalSetting.getParticalSize()).intersects(new Rectangle2D.Double(
                                p.getPosition().getX() - GlobalSetting.getParticalSize() / 2,
                                p.getPosition().getY() - GlobalSetting.getParticalSize() / 2,
                                GlobalSetting.getParticalSize(),
                                GlobalSetting.getParticalSize()));
    }
    
    @Override
    public void resetExternalForce() {
        setExternalForceR(new Force2D(new double[]{0, 0}));
    }
    
}
