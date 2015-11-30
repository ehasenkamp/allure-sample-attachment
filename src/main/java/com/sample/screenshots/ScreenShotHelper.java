package com.sample.screenshots;

import com.sample.interfaces.Step;
import com.sample.interfaces.Attachment.MediaType;
import com.sample.reporter.AttachmentBuilder;

public class ScreenShotHelper {

	private static final String HTML = "Html";
	private static final String SCREENSHOT = "Screenshot";
	private static final String ENDPOINT = "Endpoint";

	public static void getElementCapture(Step step) {
		if (step.captureWebElement() && step.getBy() != null) {
			byte[] elem = new ScreenShots.Capture(step.getDriver()).by(
					step.getBy()).getBytes();
			step.getAttachements().add(
					AttachmentBuilder.attach(elem).type(MediaType.IMG_PNG)
							.build());
		}
	}

	public static void getScreenShot(Step step) {
		if (step.captureScreen()) {
			byte[] screenshot = new ScreenShots.Capture(step.getDriver())
					.getBytes();
			String anchor = "<a href='" + step.getDriver().getCurrentUrl()
					+ "'/>" + step.getDriver().getCurrentUrl() + "</a>";
			step.getAttachements().add(
					AttachmentBuilder.attach(anchor).name(ENDPOINT)
							.type(MediaType.TEXT_HTML).build());
			step.getAttachements()
					.add(AttachmentBuilder
							.attach(step.getDriver().getPageSource().getBytes())
							.name(HTML).type(MediaType.TEXT_HTML).build());
			step.getAttachements().add(
					AttachmentBuilder.attach(screenshot).name(SCREENSHOT)
							.type(MediaType.IMG_PNG).build());
		}
	}

}
