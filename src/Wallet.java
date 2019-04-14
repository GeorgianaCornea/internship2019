import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Wallet  {
	private List<Card> cards;
	
	public Wallet() {
		cards=new ArrayList<Card>();  
	}
	
	public void initializeWallet() {
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime dateTime;	

		
		String s="2020/05/23 00:00:00";  
		dateTime = LocalDateTime.parse(s, dtf);
		Card cardSilver=new Card(Type.SILVER, 0.2f, 4500, 20000, dateTime);
		cards.add(cardSilver);
		
		s="2018/08/15 00:00:00";  
		dateTime = LocalDateTime.parse(s, dtf);
		Card cardGold=new Card(Type.GOLD, 0.1f, 3000, 25000, dateTime);
		cards.add(cardGold);

		s="2019/03/20 00:00:00";  
		dateTime = LocalDateTime.parse(s, dtf);
		Card cardPlatinum=new Card(Type.PLATINUM, 0.0f, 4000, 3000, dateTime);
		cards.add(cardPlatinum);
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Card> getSortedValidCards(LocalDateTime now) {
		ArrayList<Card> validCards=new ArrayList<Card>();

		for (Card cc :this.cards) {
			if (!cc.isExpired(now)) validCards.add(cc);
		}

		Collections.sort(validCards);
		
		
		return validCards;
		
	}

}
