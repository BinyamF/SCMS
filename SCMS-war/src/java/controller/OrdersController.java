/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.NonFiscalReceipt;
import entity.Orders;
import entity.Products;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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

/**
 *
 * @author Billion
 */
@Named(value = "ordersController")
@ViewScoped
public class OrdersController implements Serializable {

    @Inject
    Orders orders;
    @Inject
    NonFiscalReceipt nonFiscalReceipt;
    @Inject
    Products products;
    @EJB
    OrdersLogicLocal ordersLogicLocal;
    @EJB
    NonFiscalReceiptLogicLocal nonFiscalReceiptLogicLocal;
    @EJB
    ProductslogicLocal productsLogicLocal;
    List<Orders> ordersList = new ArrayList<>();
    DataModel<Orders> ordersModel;

    public Orders getOrders() {
        if (orders == null) {
            orders = new Orders();
        }
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<Orders> getOrdersList() {
        if (ordersList == null) {
            ordersList = new ArrayList<>();
        }
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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

    public NonFiscalReceipt getNonFiscalReceipt() {
        if (nonFiscalReceipt == null) {
            nonFiscalReceipt = new NonFiscalReceipt();
        }
        return nonFiscalReceipt;
    }

    public void setNonFiscalReceipt(NonFiscalReceipt nonFiscalReceipt) {
        this.nonFiscalReceipt = nonFiscalReceipt;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public OrdersController() {
    }

    //=====================================
    public void add() {
        try {
            if (!orders.getTotalPrice().equals(BigDecimal.ZERO)) {
                orders.setNonFiscalId(nonFiscalReceipt);
                orders.setProductsId(products);
                getOrdersList().add(orders);
                addToDataModel();
            }
        }catch(NullPointerException nullx){
            throw nullx;
        }
    }

    public void addToDataModel() {
        ordersModel = new ListDataModel(new ArrayList(ordersList));
        orders = new Orders();
    }

    public void totalPrice() {
        if (!orders.getAmount().equals(BigInteger.ZERO)) {
            orders.setTotalPrice(products.getProductPrice().multiply(new BigDecimal(orders.getAmount())));
        }
    }

    public void create() {
        for (int i = 0; i < ordersList.size(); i++) {
            ordersList.get(i).setNonFiscalId(nonFiscalReceipt);
            ordersLogicLocal.edit(ordersList.get(i));
        }
    }

    //==================================================
    public List<NonFiscalReceipt> findAllNonFiscals() {
        return nonFiscalReceiptLogicLocal.findAll();
    }

    public List<Products> findAllProducts() {
        return productsLogicLocal.findAll();
    }

    public void fetchProductInfo(ValueChangeEvent event) {
        products.setIdproducts(Integer.parseInt(event.getNewValue().toString()));
        products = productsLogicLocal.fetchProductsInfo(products);
        orders.setProductsId(products);
    }

    public void fetchNonFiscalInfo(ValueChangeEvent event) {
        nonFiscalReceipt.setIdOrders(Integer.parseInt(event.getNewValue().toString()));
        nonFiscalReceipt = nonFiscalReceiptLogicLocal.fetchNonFiscalInfo(nonFiscalReceipt);
        ordersList = ordersLogicLocal.findByNonFiscal(nonFiscalReceipt);
        addToDataModel();
    }

}
