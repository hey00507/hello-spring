package me.ethan.hellospring.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
