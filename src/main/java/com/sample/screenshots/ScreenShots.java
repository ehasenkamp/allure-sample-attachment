package com.sample.screenshots;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.CoordsProvider;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

/**
 * Builder class to capture screenshots<br>
 * Usage:
 * 
 * <pre>
 * <code>new ScreenShots().Capture(WebDriver).getBytes();</code>
 * </pre>
 * 
 * @author ehasenkamp
 */
public class ScreenShots {

	private final Screenshot screenshot;

	private ScreenShots(Capture capture) {
		screenshot = (!capture.by.isEmpty()) ? getElementScreenShot(capture)
				: getScreenshot(capture.driver);
	}

	Screenshot getScreenshot(WebDriver driver) {
		return new AShot().takeScreenshot(driver);
	}

	private Screenshot getElementScreenShot(Capture capture) {
		List<WebElement> elem = new ArrayList<WebElement>();
		for (By by : capture.by) {
			elem.addAll(capture.driver.findElements(by));
		}
		return new AShot()
				.imageCropper(new IndentCropper().addIndentFilter(new BlurFilter()))
				.coordsProvider(capture.coordsProvider)
				.takeScreenshot(capture.driver, elem);
	}

	private byte[] getBytes() {
		return getByteArrayFromScreenShot();
	}

	private byte[] getByteArrayFromScreenShot() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			// ImageIO.write(screenshot.getImage(), "png", new
			// File("c:\\out.png"));
			ImageIO.write(screenshot.getImage(), "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageInByte;
	}

	/**
	 * Capture class defining what to take a screen capture of
	 * 
	 * @author ehasenkamp
	 */
	public static class Capture {

		List<By> by = new ArrayList<By>();
		WebDriver driver;
		CoordsProvider coordsProvider = new WebDriverCoordsProvider();

		public Capture(WebDriver driver) {
			this.driver = driver;
		}

		/**
		 * @param by
		 *            {@link org.openqa.selenium.By By} element to take a screen
		 *            capture of
		 * @return {@link com.sample.screenshots.ScreenShots.Capture}
		 */
		public Capture by(By by) {
			this.by.add(by);
			return this;
		}

		/**
		 * @param by
		 *            {@link org.openqa.selenium.By By} element to take a screen
		 *            capture of
		 * @return {@link com.sample.screenshots.ScreenShots.Capture}
		 */
		public Capture by(List<By> by) {
			this.by.addAll(by);
			return this;
		}

		/**
		 * @param coordsProvider
		 *            for aShot to take screen shots of elements on a page
		 *            options:<br>
		 *            {@link ru.yandex.qatools.ashot.coordinate.WebDriverCoordsProvider
		 *            WebDriverCoordsProvider} <b>default</b><br>
		 *            {@link ru.yandex.qatools.ashot.coordinate.JqueryCoordsProvider
		 *            JqueryCoordsProvider}
		 * @return @return
		 *         {@link com.sample.screenshots.ScreenShots.Capture}
		 */
		public Capture coordsProvider(CoordsProvider coordsProvider) {
			this.coordsProvider = coordsProvider;
			return this;
		}

		/**
		 * get byte array of the screenshot for logging with Allure
		 */
		public byte[] getBytes() {
			return new ScreenShots(this).getBytes();
		}

	}

}
