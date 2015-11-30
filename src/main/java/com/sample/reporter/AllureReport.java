package com.sample.reporter;

import com.sample.interfaces.Attachment;
import com.sample.interfaces.Report;
import com.sample.interfaces.Step;
import com.sample.screenshots.ScreenShotHelper;

/**
 * 
 * @author ehasenkamp
 */
public class AllureReport implements Report {

	@Override
	public void log(Step step) {
		ScreenShotHelper.getElementCapture(step);
		ScreenShotHelper.getScreenShot(step);
		String stepName = step.getName().replaceAll("\\<[^>]*>", "");
		logStep(stepName, step);
		// null out the step object for memory cleanup
		step = null;
	}

	@ru.yandex.qatools.allure.annotations.Step("{0}")
	private void logStep(String stepName, Step attach) {
		for (Attachment<?> entry : attach.getAttachements()) {

			switch (entry.getType()) {
			case TEXT_PLAIN: {
				plain(entry.getName(), entry.getMessage());
				break;
			}
			case TEXT_HTML: {
				html(entry.getName(), entry.getMessage());
				break;
			}
			case TEXT_XML: {
				xml(entry.getName(), entry.getMessage());
				break;
			}
			case IMG_PNG: {
				png(entry.getName(), entry.getMessage());
				break;
			}
			default: {
				attach(entry.getName(), entry.getMessage());
				break;
			}
			}
		}
	}

	@ru.yandex.qatools.allure.annotations.Attachment(value = "{0}")
	private <T> T attach(String name, T bytes) {
		return bytes;
	}

	@ru.yandex.qatools.allure.annotations.Attachment(value = "{0}", type = "text/html")
	private <T> T html(String name, T bytes) {
		return bytes;
	}

	@ru.yandex.qatools.allure.annotations.Attachment(value = "{0}", type = "text/plain")
	private <T> T plain(String name, T bytes) {
		return bytes;
	}

	@ru.yandex.qatools.allure.annotations.Attachment(value = "{0}", type = "text/xml")
	private <T> T xml(String name, T bytes) {
		return bytes;
	}

	@ru.yandex.qatools.allure.annotations.Attachment(value = "{0}", type = "image/png")
	private <T> T png(String name, T bytes) {
		return bytes;
	}

}
