/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 * if any, must include the following acknowledgment:
 * "This product includes software developed by the
 * Apache Software Foundation (http://www.apache.org/)."
 * Alternately, this acknowledgment may appear in the software itself,
 * if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 * "Apache JMeter" must not be used to endorse or promote products
 * derived from this software without prior written permission. For
 * written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 * "Apache JMeter", nor may "Apache" appear in their name, without
 * prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.jmeter.gui.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.tree.JMeterTreeNode;

/**
 *  Title: JMeter Description: Copyright: Copyright (c) 2000 Company: Apache
 *
 *@author     Michael Stover
 *@created    October 5, 2001
 *@version    1.0
 */

public class Remove implements Command
{

	private static Set commands = new HashSet();

	/**
	 *  Constructor for the Remove object
	 */
	public Remove()
	{
	}

	/**
	 *  Gets the ActionNames attribute of the Remove object
	 *
	 *@return    The ActionNames value
	 */
	public Set getActionNames()
	{
		return commands;
	}

	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void doAction(ActionEvent e)
	{
		ActionRouter.getInstance().actionPerformed(new ActionEvent(e.getSource(),
				e.getID(),CheckDirty.REMOVE));
		GuiPackage guiPackage = GuiPackage.getInstance();
		JMeterTreeNode[] nodes = guiPackage.getTreeListener().getSelectedNodes();
		guiPackage.getTreeListener().removedSelectedNode();
		for (int i = nodes.length - 1; i >= 0; i--)
		{
				removeNode(nodes[i]);
		}
		guiPackage.getTreeListener().getJTree().setSelectionRow(1);

	}

    public static void removeNode(JMeterTreeNode node)
    {
        GuiPackage.getInstance().getTreeModel().removeNodeFromParent(node);
        GuiPackage.getInstance().removeNode(node.createTestElement());
    }
	static
	{
		commands.add("remove");
	}
}
