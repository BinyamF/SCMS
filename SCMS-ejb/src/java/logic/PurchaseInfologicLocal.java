/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.PurchaseInfo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface PurchaseInfologicLocal {
   public void create(PurchaseInfo purchasedInfo);
   public void edit(PurchaseInfo purchasedInfo);
   public void delete(PurchaseInfo purchasedInfo);
   public List<PurchaseInfo> findAll();
   public ArrayList<PurchaseInfo> searchPurchaseInfo(PurchaseInfo purchaseInfo);
   public PurchaseInfo fetchPurchaseInfo(PurchaseInfo purchaseInfo);   
}
