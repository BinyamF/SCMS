/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.NonFiscalReceipt;
import entity.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface OrdersLogicLocal {
    public void edit(Orders orders);
    public List<Orders> findByNonFiscal(NonFiscalReceipt nonFiscalReceipt);
}
