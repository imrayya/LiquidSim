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
package ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import sim.GlobalSetting;
import sim.Simulation;

/**
 *
 * @author Kareem Horstink
 */
public class Panel extends JPanel {

    RenderInterface drawer;

    public Panel(Simulation sim) throws NoModeSelectedException {
        switch (GlobalSetting.getCalculationMode()) {
            case GlobalSetting.PLANER:
                drawer = new ui.plane.Render(sim);
                break;
            case GlobalSetting.SPACE:
                drawer = new ui.space.Render(sim);
                break;
            default:
                throw new NoModeSelectedException();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawer.render(g2);
    }

}
