package com.RapidGrill.service;

import com.RapidGrill.model.Cart;
import com.RapidGrill.model.CartItem;
import com.RapidGrill.request.AddCartItemRequest;

public interface CartService {

    public CartItem addCartItem(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
