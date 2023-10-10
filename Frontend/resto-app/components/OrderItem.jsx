"use client"
import React, { useState, useEffect } from 'react';

const OrderItem = ({ orderItem, user }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [cartItems, setCartItems] = useState([]);
  const [orderDateTime, setOrderDateTime] = useState('');

  useEffect(() => {
    if (isModalOpen) {
      console.log(isModalOpen);
      fetchCartItems(orderItem.id);
    }
  }, [isModalOpen, orderItem.id]);

  useEffect(() => {
    // Convert timestamp to a human-readable date and time
    if (orderItem.timeStamp) {
      const timestamp = new Date(orderItem.timeStamp);
      const formattedDateTime = timestamp.toLocaleString(); // Adjust the formatting as needed
      setOrderDateTime(formattedDateTime);
    }
  }, [orderItem.timeStamp]);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setCartItems([]);
    console.log(isModalOpen); // here it is true even after setting it false
  };

  const getStatusClassName = () => {
    switch (orderItem.status) {
      case 'PENDING':
        return 'pending-status';
      case 'PREPARING':
        return 'preparing-status';
      // Add more cases for other status values if needed
      default:
        return '';
    }
  };

  const getDineTakeawayText = () => {
    return orderItem.dineIn ? 'Dine In' : 'Take Away';
  };

  const fetchCartItems = async (orderId) => {
    try {
      const apiUrl = `http://localhost:8090/api/order/${orderId}/cartitems`;
      const token = localStorage.getItem('token');

      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const data = await fetch(apiUrl, { headers });
      const items = await data.json();
      console.log(items);
      setCartItems(items);
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  const handleAcceptOrder = async (orderId) => {
    if (user) {
      const apiUrl = 'http://localhost:8090/api/order/' + orderId;
      const token = localStorage.getItem('token');
      const headers = {
        Authorization: `Bearer ${token}`,
      };
      let chefId = user.userId;
      let status = 'PREPARING';

      const requestBody = {
        status,
        chefId,
      };
      const response = await fetch(apiUrl, {
        method: 'PUT', // Use PUT for the request
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(requestBody),
      });

      const data = await response.json();
      console.log(data);
    }
  };

  return (
    <>
      <tr className={`order-item-card ${getStatusClassName()}`} onClick={openModal}>
        <td>{orderItem.id}</td>
        <td>{orderDateTime}</td>
        <td>{orderItem.paid ? 'Yes' : 'No'}</td>
        <td>${orderItem.totalAmount?.toFixed(2)}</td>
        <td > <div className={`order-status-item order-status-${orderItem.status?.toLowerCase()}`}>{orderItem.status}</div></td>

      </tr>
      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <div className="modal-content">
              <h2>Order Details</h2>
              <div className="modal-order-detail-container">
              <p>#{orderItem.id}</p>
              <p>{orderDateTime}</p>
            <div className={`order-status-item order-status-${orderItem.status?.toLowerCase()}`}>{orderItem.status}</div>
              <p>Paid: {orderItem.paid ? 'Yes' : 'No'}</p>
              <p>{getDineTakeawayText()}</p>
              </div>
         
              {user?.role === 'ROLE_USER' && (
                <p>Customer Name: {orderItem.customerName}</p>
              )}
              {user?.role === 'ROLE_CHEF' && (
                <p>Chef Name: {orderItem.chefName}</p>
              )}
              <p>Total Amount: ${orderItem.totalAmount?.toFixed(2)}</p>
              <h3>Cart Items:</h3>
              <div className='order-details-scrollable'>
              {cartItems.map((cartItem) => (
                <div key={cartItem.id} className="order-item">
                  <img
                    src={cartItem.image}
                    alt={cartItem.foodName}
                    className="food-image"
                  />
                  <div className="order-item-info">
                    <h3>{cartItem.foodName}</h3>
                    <p>Quantity: {cartItem.quantity}</p>
                  </div>
                </div>
              ))}
              </div>
           
              <div className="modal-btn-container">
              {user?.role === 'ROLE_CHEF' && (
                <button onClick={() => handleAcceptOrder(orderItem.id)}>
                  Accept Order
                </button>
              )}
              <button onClick={closeModal}>Close</button>
              </div>
         
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default OrderItem;
