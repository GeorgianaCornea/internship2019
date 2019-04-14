import java.time.LocalDateTime;

enum Type{
	SILVER, GOLD,PLATINUM
}
public class Card implements Comparable{
	private Type type;
	private float fee;
	private float limit;
	private float availableAmount;
	private LocalDateTime expirationDate;

	
	public Card() {
		this.setType(null);
		this.setFee(0.0f);
		this.setLimit(0.0f);
		this.setAvailableAmount(0.0f);
		this.setExpirationDate(null);
		
	}
	public Card(Type t, float f, float l, float aA, LocalDateTime exp) {
		this.setType(t);
		this.setFee(f);
		this.setLimit(l);
		this.setAvailableAmount(aA);
		this.setExpirationDate(exp);
		
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public float getLimit() {
		return limit;
	}
	public void setLimit(float limit) {
		this.limit = limit;
	}
	public float getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(float availableAmount) {
		this.availableAmount = availableAmount;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String toString() {
		return this.getType()+" CREDIT CARD.";
	}
	
	public boolean isExpired(LocalDateTime currentDate) {
	
  		if (currentDate.isAfter(this.getExpirationDate())) return true;
  		return false;
		
	}

	
	@Override
	public int compareTo(Object comp) {
		int compareFee=(int) (((Card)comp).getFee()*10);
        return (int) (this.fee*10-compareFee);
	}

}
