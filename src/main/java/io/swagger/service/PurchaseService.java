package io.swagger.service;

import io.swagger.model.Card;
import io.swagger.model.Cart;
import io.swagger.model.Receipt;
import io.swagger.repository.ReceiptRepository;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  @Autowired private ReceiptRepository receiptRepository;

  /**
   * Create Receipt using the Cart and Card
   *
   * @param cart cart given to show items
   * @param card card given to purchase
   * @return Receipt if card is valid, null if the card is expired
   */
  public Receipt makeReceipt(Cart cart, Card card) {
    if (validateExpDate(card.getExpMonth(), card.getExpYear())) {
      Card secureCard = secureCardNumber(card);
      Receipt receipt = new Receipt(cart, secureCard);
      receiptRepository.save(receipt);
      return receipt;
    }
    return null;
  }

  /**
   * Validate the expiration date of the Card GregorianCalendar month starts from 0(January)
   *
   * @param month month given to check
   * @param year year given to check
   * @return true if the month/year is not expired. false if it is expired.
   */
  public boolean validateExpDate(Integer month, Integer year) {
    GregorianCalendar now = new GregorianCalendar();
    GregorianCalendar expDate = new GregorianCalendar(year, month - 1, 1, 23, 59, 59);
    int lastDate = expDate.getActualMaximum(expDate.DATE);
    expDate.set(expDate.DATE, lastDate);
    return now.compareTo(expDate) <= 0;
  }

  /**
   * Secure the cardNumber by only saving the last four digits
   *
   * @param card card given to secure in the database
   * @return Card with the only last four digits
   */
  public Card secureCardNumber(Card card) {
    String cardNum = card.getCardNumber().substring(card.getCardNumber().length() - 4);
    card.setCardNumber(cardNum);
    return card;
  }
}
