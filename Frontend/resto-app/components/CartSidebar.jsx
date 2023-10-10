"use client"
import React, { useEffect, useState } from 'react';
import { useCart } from '../context/CartContext';
import { useUser } from '@/context/UserContext';
import {AiOutlineDelete} from "react-icons/ai"


const placeOrder = async (cart, user, token,deliveryOption,total) => {
  // Create the request body
  const requestBody = {
    customerId: user.userId, // Replace with the actual user ID
    cartItems: cart.map((item) => ({ foodId: item.foodId, quantity: item.quantity })),
    dineIn: deliveryOption==="dine-in",
    paid: true, // Modify as needed
    totalAmount:total
  };

  // Define the headers with the authorization token
  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`, 
  };
  try {
    const response = await fetch('http://localhost:8090/api/order', {
      method: 'POST',
      headers,
      body: JSON.stringify(requestBody),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
   const data= await response.json()

    return data;
  } catch (error) {
    console.error('Error placing order:', error);
    return null; // Return null to indicate that the order placement failed
  }
  // Send the POST request

  
};


  
const CartSidebar = () => {
  const {
    cart,
    clearCart,
    cartIsOpen,
    setCartIsOpen,
    increaseQuantity,
    decreaseQuantity,
    calculateSubtotal,
    removeFromCart,
  } = useCart();
  
  const [showModal, setShowModal] = useState(false);
  const [deliveryOption, setDeliveryOption] = useState('dine-in'); 
  // Default option

const {user}=useUser();
  useEffect(() => {
    // Open the sidebar when there are items in the cart
    if (cart.length > 0) {
      setCartIsOpen(true);
    }
  }, [cart]);

  const closeSidebar = () => {
    setCartIsOpen(false);
  };

  const openModal = () => {
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
  };

  const handleCheckout = async () => {
    // Get the logged-in user from localStorage

  
    // Check if the user is logged in
    if (!user) {
      // If the user is not logged in, show a message to log in
      alert('Please log in to place an order.'); // You can replace this with a modal or navigate to the login page.
      return;
    }
  
    // Get the user's token from localStorage
    const token = localStorage.getItem('token');
  
    // Try to place the order
    try {
      // Place the order using the user's information and token
      let total=calculateSubtotal().toFixed(2)
      const response = await placeOrder(cart, user, token, deliveryOption,total);
  
      // Check if the order was placed successfully
      if (response && response.id) {
        // Show a success message to the user
        alert('Order placed successfully!'); // You can replace this with a more user-friendly message
  
        // Clear the cart
        clearCart();
  
        // Close the checkout modal
        closeModal();
      } else {
        // Show an error message to the user
        alert('Error placing order. Please try again later.'); // You can replace this with a more user-friendly message
      }
    } catch (error) {
      // Handle any errors that occur while placing the order
      console.error('Error placing order:', error);
  
      // Show a generic error message to the user
      alert('An error occurred while placing your order. Please try again later.');
    }
  };
  

  return (
    <div className={`cart-sidebar ${cartIsOpen ? 'open' : ''}`}>
      <button className="close-button" onClick={closeSidebar}>
        Close
      </button>
      <h2>Your Cart</h2>
      <ul className="cart-item-list">
        {cart.map((item) => (
          <li key={item.foodId} className="cart-item">
            <div className="cart-item-details">
              <img src={item.image} alt={item.name} className="cart-item-image" />
              <div className="cart-item-info">
                <h4>{item.name}</h4>
                <p> ${item.price}</p>
                {/* <p>Rating: {item.ratingScore}</p> */}
                <button onClick={() => removeFromCart(item.foodId)}><AiOutlineDelete className='delete-btn'/></button>
              </div>
            </div>
            <div className="cart-item-quantity">
              <button onClick={() => decreaseQuantity(item.foodId)}>-</button>
              <span>Quantity: {item.quantity}</span>
              <button onClick={() => increaseQuantity(item.foodId)}>+</button>
            </div>
          </li>
        ))}
      </ul>
      <div className="cart-footer">
      <div className="cart-subtotal">
        Subtotal: ${calculateSubtotal().toFixed(2)}
      </div>
      <button className="checkout-button" onClick={openModal}>
        Checkout
      </button>
      </div>
  

      {showModal && (
        <div className="modal-overlay">
          <div className="modal-checkout">
            <div className="modal-content">
              <h2>Order Details</h2>
              <select
                value={deliveryOption}
                onChange={(e) => setDeliveryOption(e.target.value)}
              >
                <option value="dine-in">Dine In</option>
                <option value="take-away">Take Away</option>
              </select>
              <div className="order-details-scrollable">
                {cart.map((item) => (
                  <div className="order-item" key={item.foodId}>
                    <img
                    src={item.image}
                    alt={item.foodName}
                    className="food-image"
                  />
                    <div className="order-item-info">
                      <h3>{item.name}</h3>
                      <p>Price: ${item.price}</p>
                    </div>
                    <div className="order-item-quantity">
                      <span>Quantity: {item.quantity}</span>
                      <p>Subtotal: ${(item.price * item.quantity).toFixed(2)}</p>
                    </div>
                  </div>
                ))}
              </div>
              <div className="modal-total">
                Total: ${calculateSubtotal().toFixed(2)}
              </div>
              <div className="checkout-button-container">
              <button onClick={handleCheckout}>Confirm Order</button>
              <button onClick={closeModal}>Cancel</button>
              </div>
         
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartSidebar;
