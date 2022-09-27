package br.com.codar.utils;

import java.util.Scanner;

public class DataInput {
	
	private final Scanner input;
	
	public DataInput() {
		this.input = new Scanner(System.in);
	}
	
    public int entryNumber(){
       return this.input.nextInt();
    }

    public String entryString(){
        return this.input.nextLine();
    }
    
    public void closeInput(){
        this.input.close();
    }
}
