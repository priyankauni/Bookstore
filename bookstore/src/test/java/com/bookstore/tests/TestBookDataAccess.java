package com.bookstore.tests;

import java.io.File;
import java.io.IOException;

import com.bookstore.admin.BookDataAccess;

public class TestBookDataAccess {
	private static final String FILE_URL = "https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt";
    public static void main(String[] args){
	System.out.println("Running test for getting the file.");
	System.out.print("Creating the client ...");
	BookDataAccess client = new BookDataAccess();
	try{
	    client.getFile(FILE_URL);
	}catch(IOException e){
	    e.printStackTrace();
	    assert false: "Exception getting file: " + e.getMessage();
	}
	System.out.println("Done!");
	System.out.print("Checking that the file exists...");
	File bookFile = new File(BookDataAccess.INVENTORY_FILE);
	assert bookFile.exists() : "File Does not exist.";
	System.out.println("Done!");
	System.out.print("Checking that the file is not empty...");
	assert bookFile.length() != 0 : "File Exists but is empty.";
	System.out.println("Done!");
	System.out.println("All tests passed!");
    }



}
