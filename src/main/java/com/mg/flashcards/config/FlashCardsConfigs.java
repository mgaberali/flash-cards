package com.mg.flashcards.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlashCardsConfigs {


    @Value("${activate.mail.message}")
    String message;

    @Value("${activate.mail.message.footer}")
    String messageFooter;
    
    @Value("${running.host}")
    String hostName;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageFooter() {
		return messageFooter;
	}

	public void setMessageFooter(String messageFooter) {
		this.messageFooter = messageFooter;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
    
    
}
