import React from 'react';

const OrderItemCard = ({ id, customerId, chefId, timeStamp, cartItems, status, totalAmount, paid, DineIn ,onClick}) => {
  return (
    <div className="order-item-card" onClick={onClick}>
      <h3>Order ID: {id}</h3>
      <p>Customer ID: {customerId}</p>
      <p>Chef ID: {chefId}</p>
      <p>Timestamp: {timeStamp}</p>

      <p>Status: {status}</p>
      <p>Total Amount: {totalAmount}</p>
      <p>Paid: {paid ? 'Yes' : 'No'}</p>
      <p>Dine In: {DineIn ? 'Yes' : 'No'}</p>
    </div>
  );
};

export default OrderItemCard;
