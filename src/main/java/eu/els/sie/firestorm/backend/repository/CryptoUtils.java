package eu.els.sie.firestorm.backend.repository;
import org.apache.commons.lang3.RandomStringUtils;


public  class CryptoUtils {

public static String generateSting() {	
	int length = 10;
    boolean useLetters = true;
    boolean useNumbers = false;
    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	
    return generatedString;
  }
}
