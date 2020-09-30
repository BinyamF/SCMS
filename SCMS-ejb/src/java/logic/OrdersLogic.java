/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.NonFiscalReceipt;
import entity.Orders;
import entity.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.OrdersFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class OrdersLogic implements OrdersLogicLocal {

    @EJB
    OrdersFacade ordersFacade;
    
    @Override
    public void edit(Orders orders) {
        ordersFacade.edit(orders);
    }

    @Override
    public List<Orders> findByNonFiscal(NonFiscalReceipt nonFiscalReceipt) {
        return ordersFacade.findByNonFiscal(nonFiscalReceipt);
    }
}
