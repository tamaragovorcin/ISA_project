package com.isaproject.isaproject.psw;

import java.util.Date;

public class Message {
	 private String text;
	    private Date timestamp;

	    public Message() {
	    }

	    public Message(String text, Date timestamp) {
	        this.text = text;
	        this.timestamp = timestamp;
	    }

	    public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	        this.text = text;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(Date timestamp) {
	        this.timestamp = timestamp;
	    }

	    @Override
	    public String toString() {
	        return "MessageDto{" +
	                "text='" + text + '\'' +
	                ", timestamp=" + timestamp +
	                '}';
	    }
}
