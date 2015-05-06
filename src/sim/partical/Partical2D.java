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

import sim.GlobalSetting;
import sim.force.Force2D;
import sim.position.Position;

/**
 *
 * @author Kareem Horstink
 */
public class Partical2D extends Partical {
    
    public Partical2D(Position p) {
        setPosition(p);
        setPredicted(new Position());
        
    }
    
    @Override
    public void setPredicted() {
        something();
        Position tmp = new Position();
        tmp.setX(getPosition().getX() + getForce().multi(GlobalSetting.getDeltaT().doubleValue() / 1000).getVector(0));
        tmp.setY(getPosition().getY() + getForce().multi(GlobalSetting.getDeltaT().doubleValue() / 1000).getVector(1));
        setPredicted(tmp);
    }
    
    @Override
    public void setPosition(Position position) {
        if (position.getX() < 0 || position.getX() > GlobalSetting.getWidth() || position.getY() < 0 || position.getY() > GlobalSetting.getHeight()) {
            setKill(true);
        } else {
            super.setPosition(position);
        }
    }
    
    private void something() {
        Force2D f = (Force2D) getForce();
        
        Gravity:
        {
            f.setVector(1, f.getVector(1) + (getMass() * (-GlobalSetting.getGravity() * (GlobalSetting.getDeltaT().doubleValue() / 1000))));
        }
        setForce(f);
    }
    
}
