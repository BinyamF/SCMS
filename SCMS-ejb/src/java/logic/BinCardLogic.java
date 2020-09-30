/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.BinCard;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.BinCardFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class BinCardLogic implements BinCardLogicLocal {

    @EJB
    BinCardFacade binCardFacade;
    
    @Override
    public void edit(BinCard binCard) {
        binCardFacade.edit(binCard);
    }
    @Override
    public void create(BinCard binCard) {
        binCardFacade.create(binCard);
    }

    @Override
    public BinCard fetchBinCard(BinCard binCard) {
        return binCardFacade.fetchBinCard(binCard);
    }
    
    @Override
    public List<BinCard> fetchItems(){
        return binCardFacade.fetchItems();
    }
}
