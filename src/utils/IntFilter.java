package utils;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class IntFilter extends DocumentFilter implements Filter {
	
	   public static final int DEFAULT = 0;
	   public static final int NEGATIVES = 1;
	   
	   private int numberSize;
	   private boolean showToolTip;
	   private JTextField textField;

	   public static void setIntFilter(JTextField textField, int numberSize, int filterType, boolean showToolTip) {
		   makeIntFilter(textField, numberSize, filterType, showToolTip);
	   }
	   
	   public static void setIntFilter(JTextField textField, int numberSize, int filterType) {
		   makeIntFilter(textField, numberSize, filterType, false);
	   }
	   
	   public static void setIntFilter(JTextField textField, int numberSize, boolean showToolTip) {
		   makeIntFilter(textField, numberSize, DEFAULT, showToolTip);
	   }
	   
	   public static void setIntFilter(JTextField textField, int numberSize) {
		   makeIntFilter(textField, numberSize, DEFAULT, false);
	   }
	   
	   private static void makeIntFilter(JTextField textField, int numberSize, int filterType, boolean showToolTip) {
		   PlainDocument doc = (PlainDocument) textField.getDocument();
		   switch(filterType) {
			   case DEFAULT:
				   doc.setDocumentFilter(new IntFilter(numberSize, showToolTip, textField));
				   break;
			   case NEGATIVES:
				   doc.setDocumentFilter(new AnswerFilter(numberSize, showToolTip, textField));
				   break;
		   }
	   }
	   
	   /**
	    * Filters by int less than specified amount
	    * @param numberSize
	    */
	   protected IntFilter(int numberSize, boolean showToolTip, JTextField textField) {
		   this.numberSize = numberSize;
		   this.showToolTip = showToolTip;
		   this.textField = textField;
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
	
	   protected boolean test(String text) {
		  if(Integer.valueOf(text) > numberSize)
			  return false;
		  
	      try {
	         Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
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
	   
	   protected int getNumberSize() {
		   return numberSize;
	   }
}
