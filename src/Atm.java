import java.time.LocalTime;

enum ATMs {
	Start,ATM1,ATM2,ATM3, ATM4
}


public class Atm {
	private ATMs atm;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private float availableAmount;
	
	public Atm() {
		this.setAtm(null);
		this.setOpeningTime(null);
        this.setClosingTime(null);
        this.setAvailableAmount(0);
	}
	


	public Atm(ATMs i,LocalTime from, LocalTime to, int amount) {
		this.setAtm(i);
		this.setOpeningTime(from);
        this.setClosingTime(to);
        this.setAvailableAmount(amount);
	}
	
	
	private void setAtm(ATMs i) {
		this.atm=i;
		
	}
	public ATMs getAtm() {
		return this.atm;
		
	}
	public LocalTime getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}
	public LocalTime getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}
	public float getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(float availableAmount) {
		this.availableAmount = availableAmount;
	}
	
	public String toString() {
		return this.getAtm()+"";
	}
	
	public int getTimeBetweenATMs(ATMs from, ATMs to) {
		if( (from==ATMs.ATM1 && to==ATMs.ATM2) || (from==ATMs.ATM2 && to==ATMs.ATM1) ) return 40;
		else if ( (from==ATMs.ATM1 && to==ATMs.ATM3) || (from==ATMs.ATM3 && to==ATMs.ATM1) ) return 40;
		else if ( (from==ATMs.ATM1 && to==ATMs.ATM4) || (from==ATMs.ATM4 && to==ATMs.ATM1) ) return 45;
		else if ( (from==ATMs.ATM2 && to==ATMs.ATM3) || (from==ATMs.ATM3 && to==ATMs.ATM2) ) return 15;
		else if ( (from==ATMs.ATM2 && to==ATMs.ATM4) || (from==ATMs.ATM4 && to==ATMs.ATM2) ) return 30;
		else if ( (from==ATMs.ATM3 && to==ATMs.ATM4) || (from==ATMs.ATM4 && to==ATMs.ATM3) ) return 15;
		else if ( (from==ATMs.Start && to==ATMs.ATM1) || (from==ATMs.ATM1 && to==ATMs.Start) ) return 5;
		else if ( (from==ATMs.Start && to==ATMs.ATM2) || (from==ATMs.ATM2 && to==ATMs.Start) ) return 60;
		else if ( (from==ATMs.Start && to==ATMs.ATM3) || (from==ATMs.ATM3 && to==ATMs.Start) ) return 30;
		else if ( (from==ATMs.Start && to==ATMs.ATM4) || (from==ATMs.ATM4 && to==ATMs.Start) ) return 45;
		return 0;

		
	}

	

}
