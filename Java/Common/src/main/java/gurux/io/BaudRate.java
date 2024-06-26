//
// --------------------------------------------------------------------------
//  Gurux Ltd
//
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2.
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------


package gurux.io;

import android.util.SparseArray;

/**
 * Defines a list of commonly supported serial communication rates (baud rates).
 * 
 * @author Gurux Ltd.
 */
public enum BaudRate {

    /**
     * 921600 baud.
     */
    BAUD_RATE_921600 (921600),
    /**
     * 576000 baud.
     */
    BAUD_RATE_576000 (576000),
    /**
     * 460800 baud.
     */
    BAUD_RATE_460800 (460800),
    /**
     * 230400 baud.
     */
    BAUD_RATE_230400 (230400),
    /**
     * 115200 baud.
     */
    BAUD_RATE_115200 (115200),
    /**
     * 78600 baud.
     */
    BAUD_RATE_78600 (78600),
    /**
     * 57600 baud.
     */
    BAUD_RATE_57600 (57600),
    /**
     * 38400 baud.
     */
    BAUD_RATE_38400 (38400),
    /**
     * 19200 baud.
     */
    BAUD_RATE_19200(19200),
    /**
     * 14400 baud.
     */
    BAUD_RATE_14400(14400),
    /**
     * 9600 baud.
     */
    BAUD_RATE_9600(9600),
    /**
     * 4800 baud.
     */
    BAUD_RATE_4800(4800),

    /**
     * 2400 baud.
     */
    BAUD_RATE_2400(2400),

    /**
     * 1200 baud.
     */
    BAUD_RATE_1200(1200),

    /**
     * 600 baud.
     */
    BAUD_RATE_600(600),

    /**
     * 300 baud.
     */
    BAUD_RATE_300(300);

    /**
     * Integer value of enumerator.
     */
    private int intValue;
    /**
     * Collection of enumerator values.
     */
    private static SparseArray<BaudRate> mappings;

    /**
     * Returns collection of enumerator values.
     * 
     * @return Enumerator values.
     */
    private static SparseArray<BaudRate> getMappings() {
        if (mappings == null) {
            synchronized (BaudRate.class) {
                if (mappings == null) {
                    mappings = new SparseArray<BaudRate>();
                }
            }
        }
        return mappings;
    }

    /**
     * Constructor.
     * 
     * @param value
     *            Integer value of enumerator.
     */
    BaudRate(final int value) {
        intValue = value;
        getMappings().put(value, this);
    }

    /**
     * Get integer value for enumerator.
     * 
     * @return Enumerator integer value.
     */
    public int getValue() {
        return intValue;
    }

    /**
     * Returns enumerator value from an integer value.
     * 
     * @param value
     *            Integer value.
     * @return Enumeration value.
     */
    public static BaudRate forValue(final int value) {
        return getMappings().get(value);
    }
}