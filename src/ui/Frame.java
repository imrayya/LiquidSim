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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import sim.GlobalSetting;
import sim.Simulation;

/**
 *
 * @author Kareem Horstink
 * @version 0.1
 */
public class Frame extends JFrame implements Observer {

    protected final Panel panel;

    public Frame(Simulation sim) throws NoModeSelectedException {
        this.panel = new Panel(sim);
        this.add(panel);
        sim.addObserver(this);

        setSize(GlobalSetting.getWidth(), GlobalSetting.getHeight());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }

}
