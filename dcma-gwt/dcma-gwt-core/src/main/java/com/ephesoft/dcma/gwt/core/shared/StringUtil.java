/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2012 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.gwt.core.shared;

/**
 * This class is utility class containing various string related APIs which can be accessed directly.
 * 
 * @author Ephesoft
 * @version 1.0
 */
public class StringUtil {

	private static final char SPACE = ' ';
	
	private static final String DOTS = "...";

	/**
	 * This method is used to trim the string to the size of the characters passed with dots appended or in the middle according to the
	 * value of boolean isDotAtEnd.
	 * 
	 * @param text {@link String} text to be trimmed
	 * @param textLength {@link Integer} number of characters to be displayed
	 * @param numberOfDots {@link Integer} number of dots to be displayed
	 * @param isDotAtEnd {@link Integer} position of dots in the output
	 * @return {@link String}
	 */
	public static String getTrimmedText(final String text, final int textLength, final int numberOfDots, final boolean isDotAtEnd) {
		int effectiveTextLength = textLength - numberOfDots;
		StringBuffer trimmedText = null;
		if (text.length() > effectiveTextLength) {
			trimmedText = new StringBuffer(text.substring(0, effectiveTextLength / 2));
			if (isDotAtEnd) {
				trimmedText.append(text.substring(effectiveTextLength / 2, effectiveTextLength));
				for (int numberOfDotsAdded = 0; numberOfDotsAdded < numberOfDots; numberOfDotsAdded++) {
					trimmedText.append('.');
				}
			} else {
				for (int numberOfDotsAdded = 0; numberOfDotsAdded < numberOfDots; numberOfDotsAdded++) {
					trimmedText.append('.');
				}
				trimmedText.append(text.substring(text.length() - effectiveTextLength / 2));
			}
		} else {
			trimmedText = new StringBuffer(text);
		}
		return trimmedText.toString();
	}

	/**
	 * API to check whether a string contains special characters. Only alphabets, digits and space characters are allowed.
	 * 
	 * @param text {@link String}
	 * @return boolean
	 */
	public static boolean checkForSpecialCharacter(final String text) {
		boolean containsSpecialCharacter = false;
		for (Character character : text.toCharArray()) {

			if (!Character.isLetterOrDigit(character) && !Character.valueOf(SPACE).equals(character)) {
				containsSpecialCharacter = true;
				break;
			}
		}
		return containsSpecialCharacter;
	}
	
	/**
	 * The <code>getDotAppendedSubString</code> method returns a substring of a string up to a 
	 * given limit appended with '...'.
	 * 
	 * @param characterLimit character limit
	 * @param string {@link String} string from which substring has to be returned
	 * @return {@link String} substring of a given string
	 */
	public static String getDotAppendedSubString(int characterLimit, String string) {
		String tempString = string;
		if (null != string && !string.isEmpty() && string.length() > characterLimit && characterLimit > DOTS.length()) {
			tempString = string.substring(0, characterLimit) + DOTS; 
		}
		return tempString;
	}
}
