/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.NonFiscalReceipt;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface NonFiscalReceiptLogicLocal {
    public void create (NonFiscalReceipt nonFiscalReceipt);
    public void edit (NonFiscalReceipt nonFiscalReceipt);
    public List<NonFiscalReceipt> findAll();
    public NonFiscalReceipt fetchNonFiscalInfo(NonFiscalReceipt nonFiscalReceipt);
}
