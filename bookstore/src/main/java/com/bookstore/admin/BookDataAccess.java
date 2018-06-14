package com.bookstore.admin;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BookDataAccess {

	private static final String SEP = File.separator;
	/**
	 * The String containing the location for where to store the file.C:\Users\sjoshi3\Desktop\Online-Bookstore\bookstore\src\main\resources
	 */
	public static final String INVENTORY_FILE = "C:" + SEP + "Users" + SEP + "sjoshi3" + SEP + "Desktop" + SEP + "Online-Bookstore" + SEP
			+ "bookstore" + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "BookData.txt";

	public static void main(String[] args) {
		BookDataAccess client = new BookDataAccess();
		try {
			client.getFile(args[0]);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Fetches the file with the inventory, from the URL given as argument.
	 * 
	 * @param addr
	 *            The URL as a String
	 * @throws IOException
	 *             if the file cannot be fetched or stored.
	 */
	public void getFile(String addr) throws IOException {
		URL url = new URL(addr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		Files.copy(connection.getInputStream(), Paths.get(INVENTORY_FILE), StandardCopyOption.REPLACE_EXISTING);
		connection.disconnect();
	}

}
