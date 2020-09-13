package br.com.bp.customer.exception;

public class CustomerNotFoundException extends RuntimeException{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 6931406961267145556L;

		public CustomerNotFoundException(String messagem){
			super(messagem);
		}
		
		public CustomerNotFoundException(String messagem, Throwable cause){
			super(messagem, cause);
		}

}
