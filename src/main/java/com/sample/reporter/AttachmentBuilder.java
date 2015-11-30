package com.sample.reporter;

import com.sample.interfaces.Attachment;

public class AttachmentBuilder<T> implements Attachment<T> {

  private String name = "Attachment";
  private T message;
  private MediaType type = MediaType.TEXT_HTML;

  public static <T> Attachment<T> attach(T message) {
    return new AttachmentBuilder<T>(message);
  }

  private AttachmentBuilder(T message) {
    this.setMessage(message);

  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (name != "Attachement ") {
      sb.append(name).append(" ");
    }
    sb.append(message);
    return sb.toString();
  }

  @Override
  public Attachment<T> name(String name) {
    this.setName(name);
    return this;
  }

  @Override
  public Attachment<T> type(MediaType mediaType) {
    this.setType(mediaType);
    return this;
  }

  @Override
  public Attachment<T> build() {
    return this;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public T getMessage() {
    return message;
  }

  public void setMessage(T message) {
    this.message = message;
  }

  @Override
  public MediaType getType() {
    return type;
  }

  public void setType(MediaType type) {
    this.type = type;
  }

}