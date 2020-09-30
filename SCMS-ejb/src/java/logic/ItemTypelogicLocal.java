/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.ItemType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface ItemTypelogicLocal {
    public void create(ItemType itemType);
    public void edit(ItemType itemType);
    public void delete(ItemType itemType);
    public List<ItemType> findAll();
    public ItemType fetchItemInfo(ItemType itemType);
}
