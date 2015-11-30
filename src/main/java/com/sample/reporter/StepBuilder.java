package com.sample.reporter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.interfaces.Attachment;
import com.sample.interfaces.Step;

public class StepBuilder implements Step {

	private WebDriver driver;
	private String name;
	private String decorator;
	private List<By> by = new ArrayList<By>();
	private boolean captureElement = false;
	private boolean captureScreen = false;
	private List<Attachment<?>> attachments = new LinkedList<Attachment<?>>();

	public static StepBuilder name(String name) {
		return new StepBuilder(name);
	}

	private StepBuilder(String stepName) {
		this.name = stepName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		if (!by.isEmpty()) {
			sb.append(" ").append(by.toString());
		}
		if (!attachments.isEmpty()) {
			for (Attachment<?> attachment : attachments) {
				sb.append("<br>").append(attachment);
			}
		}
		return sb.toString();
	}

	@Override
	public Step add(Attachment<?> attachment) {
		getAttachements().add(attachment);
		return this;
	}

	/**
	 * capture an element on the page, not just the entire page
	 * 
	 * @param by
	 * @return
	 */
	@Override
	public Step captureWebElement(WebDriver driver, By... by) {
		this.captureElement = true;
		this.driver = driver;
		for (By _by : by) {
			this.by.add(_by);
		}
		return this;
	}

	/**
	 * capture a screenshot with logging
	 * 
	 * @return
	 */
	@Override
	public Step screenshot(WebDriver driver) {
		this.captureScreen = true;
		this.driver = driver;
		return this;
	}

	/**
	 * final step to log a step
	 */
	@Override
	public Step build() {
		Properties props = System.getProperties();
		props.put("allure.report.remove.attachments", ".*\\.png");
		return this;
	}

	@Override
	public Step decorator(String decorator) {
		this.decorator = decorator;
		return this;
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<By> getBy() {
		return by;
	}

	@Override
	public boolean captureWebElement() {
		return captureElement;
	}

	@Override
	public boolean captureScreen() {
		return captureScreen;
	}

	@Override
	public List<Attachment<?>> getAttachements() {
		return attachments;
	}

	@Override
	public String getDecorator() {
		if(this.decorator==null)
			return "%s";
		return decorator;
	}

}