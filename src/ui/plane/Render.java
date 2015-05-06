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
package ui.plane;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import sim.GlobalSetting;
import sim.Simulation;
import sim.partical.Partical2D;
import ui.RenderInterface;

/**
 *
 * @author Kareem Horstink
 * @version 0.1
 */
public class Render implements RenderInterface {

    private final Simulation SIM;

    public Render(Simulation sim) {
        this.SIM = sim;
    }

    @Override
    public void render(Graphics2D g) {
        Iterator<Partical2D> i = SIM.getCONTAINER().getIterator();
        while (i.hasNext()) {
            Partical2D next = i.next();
            g.fill(new Ellipse2D.Double(
                    next.getPosition().getX()-GlobalSetting.getParticalSize()/2,
                    next.getPosition().getY()-GlobalSetting.getParticalSize()/2,
                    GlobalSetting.getParticalSize(),
                    GlobalSetting.getParticalSize())
            );

            g.draw(new Line2D.Double(
                    next.getPosition().getX(),
                    next.getPosition().getY(),
                    next.getPosition().getX() + next.getForce().getVector(0),
                    next.getPosition().getY() + next.getForce().getVector(1)));
        }
    }

}
