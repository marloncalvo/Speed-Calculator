package utils;

import javax.swing.JTextField;

public class AnswerFilter extends IntFilter {
	
	protected AnswerFilter(int numberSize, boolean showToolTip, JTextField textField) {
		super(numberSize, showToolTip, textField);;
	}
	
	@Override
	protected boolean test(String text) {
	  if(text.length() == 1 && text.equals("-"))
		  return true;
	  if(text.length() > super.getNumberSize() + textContainsNeg(text))
		  return false;
		  
	     try {
	        Integer.parseInt(text);
	        return true;
	     } catch (NumberFormatException e) {
	        return false;
	     }
   }
	
	private int textContainsNeg(String text) {
		return (text.contains("-") ? 1 : 0);
	}
	
}
