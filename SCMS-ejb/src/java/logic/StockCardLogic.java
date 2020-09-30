/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StockCard;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.StockCardFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class StockCardLogic implements StockCardLogicLocal {

    @EJB
    StockCardFacade stockCardFacade;
    
    @Override
    public void create(StockCard stockCard) {
        stockCardFacade.create(stockCard);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void edit(StockCard stockCard) {
        stockCardFacade.edit(stockCard);
    }
}
