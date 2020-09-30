/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.BinCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface BinCardLogicLocal {
    public void edit(BinCard binCard);
    public void create(BinCard binCard);
    public BinCard fetchBinCard(BinCard binCard);
    public List<BinCard> fetchItems();
}
