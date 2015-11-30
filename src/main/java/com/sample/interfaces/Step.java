package com.sample.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The {@link Step} is the element which is logged to the final report. It can
 * contain a list of {@link com.sample.interfaces.Attachment
 * Attachments<T>} that will be also logged in the report under the current
 * step. Steps can be used to capture screen images as well;
 * 
 * @author ehasenkamp
 */
public interface Step {

	// final step returning the step object
	Step build();

	// if screen capture of webelement is desired
	Step captureWebElement(WebDriver driver, By... by);

	// if screencaputre of entire page is desired
	Step screenshot(WebDriver driver);

	// add a child attachment
	Step add(Attachment<?> attachment);

	// getter for webelement capture
	boolean captureWebElement();

	// get for screen capture flag
	boolean captureScreen();

	// getter to have access to the driver
	WebDriver getDriver();

	// get the list of attachments
	List<Attachment<?>> getAttachements();

	// get the by element
	List<By> getBy();

	// get the step name
	String getName();

	String getDecorator();

	Step decorator(String decorator);

}
