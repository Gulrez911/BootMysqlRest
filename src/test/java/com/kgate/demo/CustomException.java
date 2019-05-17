package com.kgate.demo;

class InvalidProductException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidProductException(String cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

public class CustomException {

	void productCheck(int weight) throws Exception {
		if (weight > 100) {
			throw new Exception("Product Invalid");
		}
	}

	public static void main(String[] args) {
		String s = "12,15,18";
		String[] values = s.split(",");
		int x = Integer.parseInt(values[0]);
		int y = Integer.parseInt(values[1]);
		int z = Integer.parseInt(values[2]);
		System.out.println(x + " " + y + " " + z);
		System.out.println(values[2]);

		System.out.println("Hello ");
		CustomException obj = new CustomException();
		try {
			obj.productCheck(600);
		} catch (Exception ex) {
			System.out.println("Caught the exception");
			System.out.println(ex.getMessage());
		}
	}
}
