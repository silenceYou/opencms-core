/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/gwt/client/ui/tree/Attic/CmsLazyTreeItem.java,v $
 * Date   : $Date: 2010/03/31 12:15:23 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.gwt.client.ui.tree;

import org.opencms.gwt.client.Messages;
import org.opencms.gwt.client.ui.CmsListItem;
import org.opencms.gwt.client.ui.css.I_CmsImageBundle;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Tree item for lazily loaded list trees.<p>
 * 
 * @author Georg Westenberger
 * @author Michael Moossen
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 8.0.0
 */
public class CmsLazyTreeItem extends CmsTreeItem {

    /** Enum for indicating the load state of a tree item. */
    public enum LoadState {

        /** children have been loaded. */
        LOADED,

        /** loading children. */
        LOADING,

        /** children haven't been loaded. */
        UNLOADED;
    }

    /**
     * Helper tree item which displays a "loading" message.<p>
     */
    protected class LoadingItem extends CmsListItem {

        /**
         * Constructs a new instance.<p>
         */
        public LoadingItem() {

            super();
            add(new Label(Messages.get().key(Messages.GUI_LOADING_0)));
        }
    }

    /** The load state of this tree item. */
    private LoadState m_loadState = LoadState.UNLOADED;

    /**
     * Constructs a new lazy tree item.<p>
     * 
     * @param widgets the widgets to insert into the new tree item
     */
    public CmsLazyTreeItem(Widget... widgets) {

        super(true, widgets);
        addChild(new LoadingItem());
    }

    /**
     * Gets the load state of the tree item.<p>
     * 
     * @return a load state
     */
    public LoadState getLoadState() {

        return m_loadState;
    }

    /**
     * This method should be called when the item's children have finished loading.<p>
     */
    public void onFinishLoading() {

        m_opener.getUpFace().setImage(getPlusImage());
        m_opener.getDownFace().setImage(getMinusImage());
        m_loadState = LoadState.LOADED;
    }

    /**
     * This method is called when the tree item's children start being loaded.<p>
     */
    public void onStartLoading() {

        m_loadState = LoadState.LOADING;
        m_opener.getUpFace().setImage(getLoadingImage());
        m_opener.getDownFace().setImage(getLoadingImage());
    }

    /**
     * Returns the loading image.<p>
     * 
     * @return the loading image
     */
    protected Image getLoadingImage() {

        Image image = new Image(I_CmsImageBundle.INSTANCE.loading());
        image.setPixelSize(11, 11);
        return image;
    }
}