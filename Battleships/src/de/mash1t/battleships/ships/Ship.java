/*
 * The MIT License
 *
 * Copyright 2015 Manuel Schmid.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.mash1t.battleships.ships;

import de.mash1t.battleships.gui.field.Field;
import de.mash1t.battleships.gui.field.FieldState;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for a ship
 *
 * @author Manuel Schmid
 */
public class Ship {

    protected ShipState shipState = ShipState.ToBeSet;
    protected final ShipSize shipSize;
    protected boolean isShipTurned = false;
    protected final List<Field> fieldList = new ArrayList<>();

    /**
     * Constructor
     *
     * @param shipSize size of the ship set in enum
     */
    public Ship(ShipSize shipSize) {
        this.shipSize = shipSize;
    }

    /**
     * Getter for the ship size
     *
     * @return size of the ship (element of enum, not integer)
     */
    public ShipSize getShipSize() {
        return shipSize;
    }

    /**
     * Turns the ship 90 degrees
     */
    public void turn() {
        isShipTurned = !isShipTurned;
    }

    /**
     * Getter for turning state
     *
     * @return is ship turned
     */
    public boolean isTurned() {
        return isShipTurned;
    }

    /**
     * Checks if all fields are destroyed and sets ship state
     *
     * @return is ship destroyed
     */
    public boolean isDestroyed() {
        boolean isDestroyed = true;
        for (Field field : fieldList) {
            if (field.getFieldState() != FieldState.Hit) {
                isDestroyed = false;
                break;
            }
        }
        
        if(isDestroyed){
            shipState = ShipState.Destroyed;
            return true;
        }
        return false;
    }

    /**
     * Assigning fields to ship
     *
     * @param fields
     * @return is ship turned
     */
    public boolean assignFieldsToShip(Field[] fields) {
        if (fields.length == this.shipSize.size()) {
            // Add fields to ship
            for (Field field : fields) {
                fieldList.add(field);
            }
            this.shipState = ShipState.Healthy;
            return true;
        }
        return false;
    }
}
