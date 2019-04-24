package utils;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

public interface Filter {
	
	default void showTooltip(Component component)
	{
	    final ToolTipManager ttm = ToolTipManager.sharedInstance();
	    final int oldDelay = ttm.getInitialDelay();
	    ttm.setInitialDelay(0);
	    ttm.mouseMoved(new MouseEvent(component, 0, 0, 0,
	            0, 0, // X-Y of the mouse for the tool tip
	            0, false));
	    SwingUtilities.invokeLater(new Runnable()
	    {
	        @Override
	        public void run() 
	        {
	            ttm.setInitialDelay(oldDelay);
	        }
	    });
	}
}
