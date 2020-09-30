/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StockCard;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface StockCardLogicLocal {
    public void create(StockCard stockCard);
    public void edit(StockCard stockCard);
}
