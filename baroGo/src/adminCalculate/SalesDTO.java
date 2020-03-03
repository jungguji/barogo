package adminCalculate;

import javafx.beans.property.SimpleStringProperty;

public class Sales {
	private SimpleStringProperty name;
	private SimpleStringProperty count;
	private SimpleStringProperty price;
	
	public Sales(String name, String count, String price)
	{
		this.name = new SimpleStringProperty(name);
		this.count = new SimpleStringProperty(count);
		this.price = new SimpleStringProperty(price);
	}

	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getCount() {
		return count.get();
	}

	public void setCount(String count) {
		this.count.set(count);
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(String price) {
		this.price.set(price);
	}
	
	
}
