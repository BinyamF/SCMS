/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.PurchaseInfo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.PurchaseInfoFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class PurchaseInfologic implements PurchaseInfologicLocal {

    @Inject
    PurchaseInfo purchaseInfo;
    @EJB
    PurchaseInfoFacade purchaseInfoFacade;
    
    @Override
    public void create(PurchaseInfo purchasedInfo) {
        purchaseInfoFacade.create(purchasedInfo);
    }

    @Override
    public void edit(PurchaseInfo purchasedInfo) {
        purchaseInfoFacade.edit(purchasedInfo);
    }

    @Override
    public void delete(PurchaseInfo purchasedInfo) {
        purchaseInfoFacade.remove(purchasedInfo);
    }

    @Override
    public List<PurchaseInfo> findAll() {
       return purchaseInfoFacade.findAll();
    }

    @Override
    public ArrayList<PurchaseInfo> searchPurchaseInfo(PurchaseInfo purchaseInfo) {
        return purchaseInfoFacade.searchPurchaseInfo(purchaseInfo);
    }

    @Override
    public PurchaseInfo fetchPurchaseInfo(PurchaseInfo purchaseInfo) {
        return purchaseInfoFacade.fetchPurchaseInfo(purchaseInfo);
    }    
}
