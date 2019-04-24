package utils;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class StringFilter extends DocumentFilter implements Filter {
	
	   private JTextField textField;
	   private boolean showToolTip;
	   
	   public static void setStringFilter(JTextField textField, boolean showToolTip) {
		   makeStringFilter(textField, showToolTip);
	   }
	   
	   public static void setStringFilter(JTextField textField) {
		   makeStringFilter(textField, false);
	   }
	   
	   private static void makeStringFilter(JTextField textField, boolean showToolTip) {
		   PlainDocument doc3 = (PlainDocument) textField.getDocument();
		      doc3.setDocumentFilter(new StringFilter(textField, showToolTip));
	   }
	   
	   private StringFilter(JTextField textField, boolean showToolTip) {
		   this.textField = textField;
		   this.showToolTip = showToolTip;
	   }
	
	   @Override
	   public void insertString(FilterBypass fb, int offset, String string,
	         AttributeSet attr) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.insert(offset, string);
	      
	      if (test(sb.toString())) {
	         super.insertString(fb, offset, string, attr);
	      } else {
	    	  doShowToolTip(textField);
	      }
	   }

	   private boolean test(String text) {
		   for (char c : text.toCharArray()) {
			    if (Character.isWhitespace(c)) {
			       return false;
			    }
			}
		   
		   return true;
	   }

	   @Override
	   public void replace(FilterBypass fb, int offset, int length, String text,
	         AttributeSet attrs) throws BadLocationException {

	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.replace(offset, offset + length, text);

	      if (test(sb.toString())) {
	         super.replace(fb, offset, length, text, attrs);
	      } else {
	    	  doShowToolTip(textField);
	      }

	   }
	   
	   @Override
	   public void remove(FilterBypass fb, int offset, int length)
	         throws BadLocationException {
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.delete(offset, offset + length);
	      if(sb.toString().length() == 0) { 
	    	  super.replace(fb, offset, length, "", null); 
	      }
	      else if (test(sb.toString())) {
	         super.remove(fb, offset, length);
	      } else {
	    	  doShowToolTip(textField);
	      }
	   }
	   
	   private void doShowToolTip(JTextField textField) {
		   if(showToolTip)
	        	 showTooltip(textField);
	   }
	}
