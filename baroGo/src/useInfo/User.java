package useInfo;

import javafx.beans.property.SimpleStringProperty;

public class User {
	private SimpleStringProperty name;
	private SimpleStringProperty id;
	private SimpleStringProperty birth;
	
	public User(String name, String id, String birth)
	{
		this.name = new SimpleStringProperty(name);
		this.id = new SimpleStringProperty(id);
		this.birth = new SimpleStringProperty(birth);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getBirth() {
		return birth.get();
	}

	public void setBirth(String birth) {
		this.birth.set(birth);
	}
	
}
