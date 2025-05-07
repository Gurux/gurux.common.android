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

package gurux.common;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Common interface for all Media components.
 * <p>
 * Using this interface GXCommunication library enables communication with
 * different medias.
 * </p>
 */
public interface IGXAsyncMedia extends IGXMedia {
    /**
     * Opens the media asynchronously.
     *
     * @param cancel Cancel the open.
     * @throws Exception Occurred exception.
     */
    void openAsync(AtomicBoolean cancel) throws Exception;

    /**
     * Closes the active connection.
     *
     * @param cancel Cancel the open.
     * @throws Exception Occurred exception.
     * @see IGXAsyncMedia#openAsync open
     * @see IGXMedia#open open
     */
    void closeAsync(AtomicBoolean cancel) throws Exception;

    /**
     *  Sends data asynchronously. No response is expected from the receiver,
     *  regardless of whether the operation succeeds or fails.
     *
     * @param data     Data to send to the device.
     * @param receiver Media depend information of the receiver (optional).
     * @param cancel   Cancel the open.
     * @throws Exception Occurred exception.
     * @see IGXMedia#receive receive
     */
    void sendAsync(Object data, String receiver, AtomicBoolean cancel) throws Exception;

    /**
     * Continues waiting for the complete response packet after send,
     * if only a partial response has been received.
     *
     * @param <T>  Media type.
     * @param args Receive data arguments.
     * @return True, if the send operation was successful.
     * @see IGXMedia#send send
     * @see IGXMedia#getIsSynchronous getIsSynchronous
     */
    <T> boolean receiveAsync(ReceiveParameters<T> args, AtomicBoolean cancel);
}
