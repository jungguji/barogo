package adminCalculate;

import org.apache.ibatis.type.Alias;

@Alias("calculate")
public class CalculateVO {
	private int iCount;
	private int iSales;
	private int iReturnCount;
	private int iReturnSales;
	
	public void setiReturnSales(int iReturnSales) {
		this.iReturnSales = iReturnSales;
	}
	public int getiCount() {
		return iCount;
	}
	public void setiCount(int iCount) {
		this.iCount = iCount;
	}
	public int getiSales() {
		return iSales;
	}
	public void setiSales(int iSales) {
		this.iSales = iSales;
	}
	public int getiReturnCount() {
		return iReturnCount;
	}
	public void setiReturnCount(int iReturnCount) {
		this.iReturnCount = iReturnCount;
	}
	public int getiReturnSales() {
		return iReturnSales;
	}
	
	public int time_product_price_sum(int a_iTime, int a_iProduct)
	{
		return a_iTime + a_iProduct;
	}
	
	public int time_product_count_sum(int a_iTime, int a_iProduct)
	{
		return a_iTime + a_iProduct;
	}
}