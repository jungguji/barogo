package admin.calculate;

import javafx.beans.property.SimpleStringProperty;

public class SalesDTO {
	private SimpleStringProperty name;
	private SimpleStringProperty count;
	private SimpleStringProperty price;
	
	public SalesDTO(String name, String count, String price)
	{
		this.name = new SimpleStringProperty(name);
		this.count = new SimpleStringProperty(count);
		this.price = new SimpleStringProperty(price);
	}

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getCount() {
        return count;
    }

    public void setCount(SimpleStringProperty count) {
        this.count = count;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleStringProperty price) {
        this.price = price;
    }
	
	
}
