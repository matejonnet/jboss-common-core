/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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
package org.jboss.test.util.test;

import junit.framework.TestCase;
import org.jboss.test.util.support.A;
import org.jboss.test.util.support.B;

/**
 * Test JBossObject.
 * 
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class JBossObjectTestCase extends TestCase
{
   public void testLogger() throws Exception
   {
      A a1 = new A();
      A a2 = new A();

      assertSame(a1.getLog(), a2.getLog());

      B b = new B();

      assertNotSame(b.getLog(), a1.getLog());
   }
}
