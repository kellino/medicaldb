package org.ucl.medicaldb;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class PatientHandler {
	/* logger */
	private static final Logger log = Logger.getLogger(Class.class.getName());

	/**
	 * checks that a compulsory field contains some text, not just whitespace
	 * @param String input
	 * @return boolean
	 */

	boolean completedObligatoryField(String input) {
		if (StringUtils.isBlank(input)) {
			return false;
		}
		return true;
	}

	/**
	 * uses a simple regex to ensure that the patient id number is in the
	 * correct form
	 * 
	 * @param String
	 * @return boolean
	 */
	boolean isValid(String input) {
		Pattern pattern = Pattern.compile("[a-z]{2}[0-9]+");
		if (pattern.matcher(input).matches()) {
			return true;
		}
		return false;
	}

	/**
	 * uses a simple regex to check for "reasonable" name forms. It excludes
	 * some symbols which do not appear in any names, such as "?", but makes no
	 * attempt at more complex verification, nor does it check for
	 * capitalization, which can very greatly in usage.
	 * 
	 * @param String
	 * @return boolean
	 */
	boolean isValid(String input, String type) {
	        /* very simple regex, but names are extremely variable, so anything more
	         * sophisticated might exclude a legitimate, but unusual, name
	         */
		Pattern pattern = Pattern.compile("[0-9<>!\"$%\\+&][{}]");
		try {
			if (pattern.matcher(input).matches()) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			log.log(Level.INFO, "incorrect name string entered by user");
			return false;
		}
		return true;
	}

	boolean isValidDate(String DOB) {
		return true;
	}

	boolean isUniqueID(String id) {
		if (Database.idNumbers.add(id)) {
			return true;
		}
		return false;
	}

	boolean isValidURI(String uri) {
	    Pattern pattern = Pattern.compile("http[s]?://");
	    try {
			if (pattern.matcher(uri).matches()) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			log.log(Level.INFO, "incorrect uri string entered by user");
			return false;
		}
		return true;
	    }
	
}
