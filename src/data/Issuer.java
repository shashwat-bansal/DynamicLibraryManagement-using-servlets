package data;

import java.util.LinkedHashMap;
import java.util.Map;

public class Issuer implements Library {

	private int id;
	private String name;
	private String book;
	private String issueDate;
	private String dueDate;
	
	public Issuer() {}
	
	public Issuer(int id, String name, String book, String issueDate, String dueDate) {
		this.id=id;
		this.name=name;
		this.book=book;
		this.issueDate=issueDate;
		this.dueDate=dueDate;
	}

	public String getissueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDuedate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}
	
	public String toString() {
		return (getId()+"\t"+getName()+"\t"+getBook()
		+"\t"+getissueDate()+"\t"+getDueDate());
	}
	
	public Map<String, String> tomap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("id",Integer.toString(id));
		map.put("name", name);
		map.put("book", book);
		map.put("issdate",issueDate);
		map.put("duedate",dueDate);
		return map;
	}

}