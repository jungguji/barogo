package adminSales;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductModel {
	private SimpleStringProperty ProductName;
	private SimpleIntegerProperty Price;
	private SimpleIntegerProperty Count;
	
	public ProductModel(String ProductName, Integer Price, Integer Count)
	{
		this.ProductName = new SimpleStringProperty(ProductName);
		this.Price = new SimpleIntegerProperty(Price);
		this.Count = new SimpleIntegerProperty(Count);
	}
	
	public String getProductName()
	{
		return ProductName.get();
	}
	
	public void setProductName(String ProductName)
	{
		this.ProductName.set(ProductName);
	}
	
	public Integer getPrice()
	{
		return Price.get();
	}
	
	public void setPrice(Integer Price)
	{
		this.Price.set(Price);
	}
	
	public Integer getCount()
	{
		return Count.get();
	}
	
	public void setCount(Integer Count)
	{
		this.Count.set(Count);
	}
}









