package com.github.lucianosantanabr.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageMQ {
	
	private String idMessage;
	private String message;
	private Date dateMessage;

}