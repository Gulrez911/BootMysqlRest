package com.kgate.demo;

class UserException extends Exception {

	public UserException(String cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

public class UserDefinedException {

	void product(int price) throws UserException {
		if (price > 100) {
			throw new UserException("Over Price");
		} else {
			System.out.println("Price is " + price);
		}
	}

	public static void main(String[] args)  {
		System.out.println("test:");

		UserDefinedException obj = new UserDefinedException();
		try{
			obj.product(20);
		}catch (UserException e) {
			// TODO: handle exception
			System.out.println("Exception catched");
			System.out.println(e.getMessage());
		}
	}
}
