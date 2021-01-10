package com.isaproject.isaproject.psw.model;

import java.util.Date;

public class Message {
		 private String text;
		 private String dateAction;
	    private Date timestamp;

	    public Message() {
	    }

	    public Message(String text, Date timestamp) {
	        this.text = text;
	        this.timestamp = timestamp;
	    }

	
	    

		public Message(String text, Date timestamp, String dateAction) {
			super();
			this.text = text;
			this.dateAction = dateAction;
			this.timestamp = timestamp;
		}

		public String getDateAction() {
			return dateAction;
		}

		public void setDateAction(String dateAction) {
			this.dateAction = dateAction;
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
