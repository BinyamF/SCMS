/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.NonFiscalReceipt;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.NonFiscalReceiptFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class NonFiscalReceiptLogic implements NonFiscalReceiptLogicLocal {

    @EJB
    NonFiscalReceiptFacade nonFiscalReceiptFacade;
    @Override
    public void create(NonFiscalReceipt nonFiscalReceipt) {
        nonFiscalReceiptFacade.create(nonFiscalReceipt);
    }
    
    @Override
    public void edit(NonFiscalReceipt nonFiscalReceipt) {
        nonFiscalReceiptFacade.edit(nonFiscalReceipt);
    }

    @Override
    public List<NonFiscalReceipt> findAll() {
        return nonFiscalReceiptFacade.findAll();
    }

    @Override
    public NonFiscalReceipt fetchNonFiscalInfo(NonFiscalReceipt nonFiscalReceipt) {
        return nonFiscalReceiptFacade.fetchNonFiscalReceiptInfo(nonFiscalReceipt);
    }

}
