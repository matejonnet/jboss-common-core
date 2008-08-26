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
package org.jboss.util.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashMap;
import java.util.Map;

/**
 * An ObjectInputStream subclass used by the MarshalledValue class to
 * ensure the classes and proxies are loaded using the thread context
 * class loader.
 *
 * @author Scott.Stark@jboss.org
 * @version $Revision: 37406 $
 */
public class MarshalledValueInputStream
      extends ObjectInputStream
{
   private static final Map<String, Class> primClasses = new HashMap<String, Class>(8, 1.0F);

   static
   {
      primClasses.put("boolean", boolean.class);
      primClasses.put("byte", byte.class);
      primClasses.put("char", char.class);
      primClasses.put("short", short.class);
      primClasses.put("int", int.class);
      primClasses.put("long", long.class);
      primClasses.put("float", float.class);
      primClasses.put("double", double.class);
      primClasses.put("void", void.class);
   }

   /**
    * Creates a new instance of MarshalledValueOutputStream
    *
    * @param is
    * @throws IOException
    */
   public MarshalledValueInputStream(InputStream is) throws IOException
   {
      super(is);
   }

   /**
    * Use the thread context class loader to resolve the class
    *
    * @throws java.io.IOException Any exception thrown by the underlying OutputStream.
    */
   protected Class<?> resolveClass(ObjectStreamClass v)
         throws IOException, ClassNotFoundException
   {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      String className = v.getName();
      try
      {
         // JDK 6, by default, only supports array types (ex. [[B)  using Class.forName()
         return Class.forName(className, false, loader);
      }
      catch (ClassNotFoundException cnfe)
      {
         Class cl = primClasses.get(className);
         if (cl == null)
            throw cnfe;
         else
            return cl;
      }
   }

   protected Class<?> resolveProxyClass(String[] interfaces)
         throws IOException, ClassNotFoundException
   {
      // Load the interfaces from the thread context class loader
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      Class<?>[] ifaceClasses = new Class[interfaces.length];
      for (int i = 0; i < interfaces.length; i++)
      {
         ifaceClasses[i] = loader.loadClass(interfaces[i]);
      }

      return java.lang.reflect.Proxy.getProxyClass(loader, ifaceClasses);
   }
}
