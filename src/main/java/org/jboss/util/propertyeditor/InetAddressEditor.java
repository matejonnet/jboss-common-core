/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors as indicated
 * by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.util.propertyeditor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.util.NestedRuntimeException;
import org.jboss.util.StringPropertyReplacer;

/**
 * A property editor for {@link java.net.InetAddress}.
 *
 * @version <tt>$Revision$</tt>
 * @author  <a href="mailto:Adrian.Brock@HappeningTimes.com">Adrian Brock</a>
 */
public class InetAddressEditor extends TextPropertyEditorSupport
{
   /**
    * Returns a InetAddress for the input object converted to a string.
    *
    * @return an InetAddress
    *
    * @throws NestedRuntimeException   An UnknownHostException occured.
    */
   public Object getValue()
   {
      try
      {
         String text = getAsText();
         if (text == null)
         {
            return null;
         }
         if (text.startsWith("/"))
         {
            // seems like localhost sometimes will look like:
            // /127.0.0.1 and the getByNames barfs on the slash - JGH
            text = text.substring(1);
         }
         return InetAddress.getByName(StringPropertyReplacer.replaceProperties(text));
      }
      catch (UnknownHostException e)
      {
         throw new NestedRuntimeException(e);
      }
   }
}
