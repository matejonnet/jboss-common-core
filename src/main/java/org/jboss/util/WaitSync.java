/*
  * JBoss, Home of Professional Open Source
  * Copyright 2005, JBoss Inc., and individual contributors as indicated
  * by the @authors tag. See the copyright.txt in the distribution for a
  * full listing of individual contributors.
  *
  * This is free software; you can redistribute it and/or modify it
  * under the terms of the GNU Lesser General Public License as
  * published by the Free Software Foundation; either version 2.1 of
  * the License, or (at your option) any later version.
  *
  * This software is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this software; if not, write to the Free
  * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
  */
package org.jboss.util;

/**
 * Interface that gives wait - notify primitives to implementors.
 *
 * @see Semaphore
 * 
 * @author <a href="mailto:simone.bordet@compaq.com">Simone Bordet</a>
 * @version $Revision$
 */
public interface WaitSync
   extends Sync
{
   /**
    * Pone in wait status this sync, until {@link #doNotify} is called to wake it up.
    * 
    * @see #doNotify
    * @throws InterruptedException 
    */
   void doWait() throws InterruptedException;
   
   /**
    * Wakes up this sync that has been posed in wait status by a {@link #doWait} call.
    * If this sync is not waiting, invoking this method should have no effect.
    * @see #doWait
    * @throws InterruptedException 
    */
   void doNotify() throws InterruptedException;
}
