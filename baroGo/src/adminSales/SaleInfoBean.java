package adminSales;

public class SaleInfoBean {
	private String ProductName;
	private int ProductType;
	private int Price;
	private int count;
	
	public String getProductName() {
		return ProductName;
	}
	
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}
	
	public int getProductType() {
		return ProductType;
	}
	public void setProductType(int productType) {
		ProductType = productType;
	}
	
	public int getPrice() {
		return Price;
	}
	public void setPrice(int Price) {
		this.Price = Price;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
