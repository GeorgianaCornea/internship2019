import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Route {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	static String currentDate="2019/03/19 11:30:00";
	public ArrayList<Atm> allAtms;
	Wallet wallet;
	
	public Route() {
		allAtms=new ArrayList<Atm>();
		addAllAtms();
		wallet=new Wallet();
		wallet.initializeWallet();
	}
	
	
	private void addAllAtms() {
		final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");

		String s2="12:00:00";
		LocalTime time=LocalTime.parse(s2,dtf2); 
		s2="18:00:00";
		LocalTime time2=LocalTime.parse(s2,dtf2);  
		Atm atm1=new Atm(ATMs.ATM1,time,time2,5000);
		allAtms.add(atm1);
		
		s2="10:00:00";
		time=LocalTime.parse(s2,dtf2); 
		s2="17:00:00";
		time2=LocalTime.parse(s2,dtf2);  
		Atm atm2=new Atm(ATMs.ATM2,time,time2,5000);
		allAtms.add(atm2);
		
		s2="22:00:00";
		time=LocalTime.parse(s2,dtf2); 
		s2="12:00:00";
		time2=LocalTime.parse(s2,dtf2);  
		Atm atm3=new Atm(ATMs.ATM3,time,time2,5000);
		allAtms.add(atm3);
		
		
		
		s2="17:00:00";
		time=LocalTime.parse(s2,dtf2); 
		s2="01:00:00";
		time2=LocalTime.parse(s2,dtf2);  
		Atm atm4=new Atm(ATMs.ATM4,time,time2,5000);
		allAtms.add(atm4);
		
		
	}


	public List<Atm> getAtmsRoute() {
		ArrayList<Atm> finalRoute=new ArrayList<Atm>();  //the final list of atms
		

		
		
		float targetAmount=7500; //the amount to be withdrawn
		float currentAmount=0;   //the amount withdrawn so far
		
		String s="2019/03/19 11:30:00";
		LocalDateTime currentDateTime=LocalDateTime.parse(s,dtf);  //current date and time
		System.out.println("Current time: "+currentDateTime);
		
		
		long diff=0;      //difference between opening time and current time
		long diffToAdd=0;
		
		
		Wallet wallet=new Wallet();    
		wallet.initializeWallet();   // adding all the cards 
		ArrayList<Card> validCards=wallet.getSortedValidCards(currentDateTime);//the usable cards
		
		int cardIndex=0;   //index of the card with the lowest fee (cards are sorted by fee in ascending order)
		 
		
			
		//find best atm from start point
		ATMs nextAtm=ATMs.Start;
	    ATMs bestStartAtm;
		float possibleAmountToWithdraw=0;
		
		while (currentAmount<targetAmount) {
			final ATMs startAtmLoop;
			bestStartAtm=ATMs.ATM4; //initialize it with a valid atm
			 for (Atm atm:allAtms) {
				 LocalDateTime dateTime = atm.getOpeningTime().atDate(LocalDate.of(2019, 03, 19));
				
				 if (currentDateTime.isBefore(dateTime)) {
					Duration duration = Duration.between(currentDateTime, dateTime);
					 diff = Math.abs(duration.toMinutes()); //number of minutes until the atm is open
					if ((atm.getTimeBetweenATMs(nextAtm, atm.getAtm())+diff)<atm.getTimeBetweenATMs(nextAtm, bestStartAtm)){
						bestStartAtm=atm.getAtm();
						diffToAdd=diff;
					}
				 }
				 else {
					 if ((atm.getTimeBetweenATMs(nextAtm, atm.getAtm()))<atm.getTimeBetweenATMs(nextAtm, bestStartAtm)){
							bestStartAtm=atm.getAtm();
							diffToAdd=0;
					 }
				 }
			 }
			
			 startAtmLoop=bestStartAtm;
			 
			 
			//remove atm from list
			 Atm newAtmLoop = allAtms.stream()
					  .filter(atm -> startAtmLoop.equals(atm.getAtm()))
					  .findAny()
					  .orElse(null);
			 
			//the next atm was found and added to the route
			 finalRoute.add(newAtmLoop);
			 allAtms.removeIf(exp->exp.getAtm().equals(startAtmLoop));
			 
			 System.out.println();
			 System.out.println("STEP : "+(cardIndex+1));
			 System.out.println("Partial route: "+finalRoute);

			 
			 

			 if (diffToAdd> newAtmLoop.getTimeBetweenATMs(nextAtm, newAtmLoop.getAtm())) {
				 currentDateTime=currentDateTime.plusMinutes(diffToAdd);
			 }
			 else {
				 currentDateTime=currentDateTime.plusMinutes(newAtmLoop.getTimeBetweenATMs(nextAtm, newAtmLoop.getAtm()));
			 }
			 
			 Card nextCard=validCards.get(cardIndex);  //choose the card with the lowest fee
			 cardIndex++;

			
			 possibleAmountToWithdraw=0;
			 while (((newAtmLoop.getAvailableAmount())>0)&&(possibleAmountToWithdraw<targetAmount)&&(possibleAmountToWithdraw<nextCard.getLimit())&&(possibleAmountToWithdraw<nextCard.getAvailableAmount())) {
				 possibleAmountToWithdraw++;
			 }

			 currentAmount+=possibleAmountToWithdraw;
			 newAtmLoop.setAvailableAmount(newAtmLoop.getAvailableAmount()-possibleAmountToWithdraw);
			 nextCard.setAvailableAmount(nextCard.getAvailableAmount()-possibleAmountToWithdraw);
			 
			 System.out.println("Withdrawing : "+possibleAmountToWithdraw+" from "+newAtmLoop.getAtm()+" using "+nextCard.toString());
			 System.out.println("The fee was : "+nextCard.getFee()/100*possibleAmountToWithdraw);

			 System.out.println("Current time : "+currentDateTime);
			 System.out.println("Current amount : "+currentAmount);
			 System.out.println(targetAmount-currentAmount+" left to withdraw");

			 
			 nextAtm=newAtmLoop.getAtm();
		
			
		}
		
		
		System.out.println();
		System.out.println("The target amount was reached!");

		
		return finalRoute;
	    
	}

}
