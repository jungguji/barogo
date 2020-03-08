package adminStats;

import org.apache.ibatis.type.Alias;

@Alias("stats")
public class StatsVO {
	private String day;
	private int sales;
	private int profit;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
}
