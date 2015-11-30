package com.sample.interfaces;

/**
 * The {@link com.sample.interfaces.Report Report} is the class used to pass in
 * {@link com.sample.interfaces.Step Steps} for logging. A custom {@link Report} can be set to control how reports
 * are generated.
 * 
 * @author ehasenkamp
 */
public interface Report {

  void log(Step step);

}
