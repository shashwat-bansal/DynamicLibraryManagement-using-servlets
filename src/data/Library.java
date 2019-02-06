package data;

import java.util.Map;

public interface Library{
	int getId();
	void setId(int id);
	String getName();
	void setName(String name);
	public Map<String, String> tomap();
}
