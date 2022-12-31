package com.solvd.reflection.vehicle;

public interface Movable {
    void moveForward();
    void moveBackward();
    void stop();

    /**
     * Moves the wheel to steer to a certain direction.
     * @param wheelPosition value from -10 to 10. A value of 0 indicates that the wheel is centered.
     */
    void steer(int wheelPosition);
}
