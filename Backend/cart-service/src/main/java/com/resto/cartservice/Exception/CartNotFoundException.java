package com.resto.cartservice.Exception;

public class CartNotFoundException extends RuntimeException{
	public CartNotFoundException(String message)
	{
		super(message);
	}

}
