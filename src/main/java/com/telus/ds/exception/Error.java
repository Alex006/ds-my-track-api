package com.telus.ds.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Error  implements Serializable {
	  private static final long serialVersionUID = 1L;

	  private String code;
	  private String message;
	  
}
