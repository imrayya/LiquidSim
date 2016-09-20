# LiquidSim
A simple java implementation of a liquid simulation

In a very early alpha form

Currently only simple particle physic in 2 day has been implemented

The simulation loop consist of:
- Particle:
  - Predict next tick's position
- Particle:
  - Colliders:
    - Check if collision will occur
      - Handle collision
- Particle:
  - Particle:
    - Check if collision will occur
      - Handle collision
- Particle:
  - Move particle based on external forces
  - Check if out of bounds
    - Kill particle
- Particle:
  - Reset external forces

  

Notes: 
- Angles are based on a unit circle (east or right being 0 and 360 degrees while up or north is 90 degrees)
- The origin is the bottom left corner

