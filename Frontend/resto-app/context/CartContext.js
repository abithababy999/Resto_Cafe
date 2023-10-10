"use client"
import React, { createContext, useContext, useEffect, useState } from 'react';

const CartContext = createContext();

export function useCart() {
  return useContext(CartContext);
}

export function CartProvider({ children }) {

  const initialCart = localStorage?.getItem('cart') ? JSON.parse(localStorage.getItem('cart')) : [];
  const [cart, setCart] = useState(initialCart);
  const [cartIsOpen,setCartIsOpen]=useState(false);

  useEffect(() => {
    // Save the cart to localStorage whenever it changes
    localStorage.setItem('cart', JSON.stringify(cart));
  }, [cart]);

  const addToCart = (foodItem) => {
    setCart((prevCart) => [...prevCart, { ...foodItem, quantity: 1 }]);
  };

  const increaseQuantity = (foodItemId) => {
    setCart((prevCart) =>
      prevCart.map((item) =>
        item.foodId === foodItemId
          ? { ...item, quantity: item.quantity + 1 }
          : item
      )
    );
  };

  const decreaseQuantity = (foodItemId) => {
    setCart((prevCart) =>
      prevCart.map((item) =>
        item.foodId === foodItemId && item.quantity > 1
          ? { ...item, quantity: item.quantity - 1 }
          : item
      )
    );
  };

  const calculateSubtotal = () => {
    return cart.reduce((total, item) => total + item.price * item.quantity, 0);
  };

  const removeFromCart = (foodItemId) => {
    setCart((prevCart) => prevCart.filter((item) => item.foodId !== foodItemId));
  };
  const clearCart=()=>{
    setCart([])
    localStorage.removeItem("cart")
  }

  return (
    <CartContext.Provider
      value={{
        cart,
        clearCart,
        addToCart,
        cartIsOpen,
        setCartIsOpen,
        increaseQuantity,
        decreaseQuantity,
        calculateSubtotal,
        removeFromCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
}
