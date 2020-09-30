/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Kommon.addFatalMessage;
import entity.NonFiscalReceipt;
import entity.Orders;
import entity.Products;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.NonFiscalReceiptLogicLocal;
import logic.OrdersLogicLocal;
import logic.ProductslogicLocal;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Billion
 */
@Named(value = "nonFiscalReceiptController")
@ViewScoped
public class NonFiscalReceiptController implements Serializable {

    @Inject
    NonFiscalReceipt nonFiscalReceipt;
    @Inject
    Orders orders;
    @Inject
    Products products;
    @EJB
    NonFiscalReceiptLogicLocal nonFiscalReceiptLogicLocal;
    @EJB
    OrdersLogicLocal ordersLogicLocal;
    @EJB
    ProductslogicLocal productsLogicLocal;

    Orders selectedOrder;
    DataModel<Orders> ordersModel;
    int jumper = 0;
    int popEditFlag = 0;
    int nonDuplicationFlag = 0;

    public int getJumper() {
        return jumper;
    }

    public void setJumper(int jumper) {
        this.jumper = jumper;
    }

    public int getPopEditFlag() {
        return popEditFlag;
    }

    public void setPopEditFlag(int popEditFlag) {
        this.popEditFlag = popEditFlag;
    }

    public NonFiscalReceipt getNonFiscalReceipt() {
        if (nonFiscalReceipt == null) {
            nonFiscalReceipt = new NonFiscalReceipt();
        }
        return nonFiscalReceipt;
    }

    public void setNonFiscalReceipt(NonFiscalReceipt nonFiscalReceipt) {
        this.nonFiscalReceipt = nonFiscalReceipt;
    }

    public Orders getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public DataModel<Orders> getOrdersModel() {
        if (ordersModel == null) {
            ordersModel = new ArrayDataModel<>();
        }
        return ordersModel;
    }

    public void setOrdersModel(DataModel<Orders> ordersModel) {
        this.ordersModel = ordersModel;
    }

    public Orders getOrders() {
        if (orders == null) {
            orders = new Orders();
        }
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        if (products == null) {
            products = new Products();
        }
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public NonFiscalReceiptController() {
    }

    public List<Products> findAllProducts() {
        return productsLogicLocal.findAll();
    }

    public void fetchProductInfo(ValueChangeEvent event) {
        products.setIdproducts(Integer.parseInt(event.getNewValue().toString()));
        products = productsLogicLocal.fetchProductsInfo(products);
        orders.setProductsId(products);
        orders.setAmount(null);
        orders.setTotalPrice(null);
        setPopEditFlag(0);
    }

    public void checkDuplication() {
        if (!(nonFiscalReceipt.getOrdersList().isEmpty())) {     //If item is NOT EMPTY --> Get In The Loop!!!
            for (int i = 0; i < nonFiscalReceipt.getOrdersList().size(); i++) {
                if (orders.getProductsId().equals(nonFiscalReceipt.getOrdersList().get(i).getProductsId()) && getPopEditFlag() == 0) {
                    addFatalMessage("Duplicated items are not allowed!");
                    setJumper(1);
                    nonDuplicationFlag++;
                }
            }
        }
        setPopEditFlag(0);
        if (getJumper() == 0) {
            addOrdersDetail();
            nonDuplicationFlag = 1;
        }
        if (nonDuplicationFlag == 0) {
            addOrdersDetail();
        }
        nonDuplicationFlag = 0;
    }

    public void addOrdersDetail() {
        if (!orders.getTotalPrice().equals(BigDecimal.ZERO)) {
            orders.setProductsId(products);
        }
        nonFiscalReceipt.addOrdersDetail(orders);
        addToDataModel();
        clearOrders();
    }

    private void addToDataModel() {
        ordersModel = new ListDataModel<>();
        ordersModel = new ListDataModel(new ArrayList<>(nonFiscalReceipt.getOrdersList()));
    }

    private void clearOrders() {
        orders = new Orders();
        products = new Products();
    }

    public void totalPrice() {
        if (!orders.getAmount().equals(BigInteger.ZERO)) {
            orders.setTotalPrice(products.getProductPrice().multiply(new BigDecimal(orders.getAmount())));
        }
    }

    public void fetchNonFiscalInfo(ValueChangeEvent event) {
        nonFiscalReceipt.setIdOrders(Integer.parseInt(event.getNewValue().toString()));
        nonFiscalReceipt = nonFiscalReceiptLogicLocal.fetchNonFiscalInfo(nonFiscalReceipt);
        orders = new Orders();
        addToDataModel();
    }

    public void populate(SelectEvent event) {
        orders = (Orders) event.getObject();
        products = orders.getProductsId();
        nonFiscalReceipt = orders.getNonFiscalId();
        setPopEditFlag(1);
    }

    public List<NonFiscalReceipt> findAllNonFiscals() {
        return nonFiscalReceiptLogicLocal.findAll();
    }

    public void create() {

        nonFiscalReceiptLogicLocal.edit(nonFiscalReceipt);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Non Fiscal Receipt", "Data successfully saved."));
    }

}
