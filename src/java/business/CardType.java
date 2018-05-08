package business;

public enum CardType {

   VISA("visa"), MASTER_CARD("masterCard");
   
   private final String name;
   
   private CardType(String name) {
      this.name = name;
   }
   
  
   public static CardType fromString(String stringCardType) {
      CardType cardType = null;
      for (CardType ct : CardType.values()) {
         if (stringCardType.equals(ct.name)) {
            cardType = ct;
            break;
         }
      }
      return cardType;
   }
}
