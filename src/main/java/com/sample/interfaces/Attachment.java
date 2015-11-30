package com.sample.interfaces;

/**
 * The {@link com.ebay.quality.reporter.interfaces.Attachement Attachement} is a container for elements to be logged under each
 * step. The type of the
 * 
 * @author ehasenkamp
 * @param <T>
 */
public interface Attachment<T> {

  String getName();

  T getMessage();

  MediaType getType();

  Attachment<T> name(String name);

  Attachment<T> type(MediaType mediaType);

  Attachment<T> build();

  /**
   * Type of media that can be attached to an Allure report
   * 
   * @author ehasenkamp
   */
  public enum MediaType {
    TEXT_PLAIN,
    TEXT_HTML,
    TEXT_XML,
    TEXT_JSON,
    IMG_PNG,
    UNKNOWN;
  }

}
