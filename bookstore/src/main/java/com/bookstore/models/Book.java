package com.bookstore.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Book implements Serializable{
	
	  private String title;
	  private String author;
	  private BigDecimal price;
	  
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	 public Book(String title, String author, BigDecimal price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	 
	 public Book() {
			this.title = "";
			this.author = "";
			this.price = BigDecimal.ZERO;
		}
	 
	 @Override
	    public boolean equals(Object o){
		if(! (o instanceof Book) ){
		    return false;
		}
		Book ob = (Book)o;
		return
		    this.title.equals(ob.title) &&
		    this.author.equals(ob.author) &&
		    this.price.compareTo(ob.price) == 0;
	    }
	    @Override
	    public int hashCode(){
		int result = 17;
		result = 31 * result + title.hashCode();
		result = 31 * result + author.hashCode();
		result = 31 * result + price.intValue();
		return result;
	    }
	    @Override
	    public String toString(){
		// For some reason, the prices must be in US number format...
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);	
		String formattedPrice = formatter.format(price.doubleValue());
		return new StringBuilder(title)
		    .append(";")
		    .append(author)
		    .append(";")
		    .append(formattedPrice)
		    .toString();
	    }

}
