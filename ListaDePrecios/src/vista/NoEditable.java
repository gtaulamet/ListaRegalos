package vista;

import java.awt.TextField;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

public class NoEditable extends DefaultCellEditor {
	  public NoEditable(JTextField tf) {
		  super(tf);
	  }
	  @Override
	  public boolean isCellEditable(EventObject anEvent) {
		  return false;
	  }
	
}
