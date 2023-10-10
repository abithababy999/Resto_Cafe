// OrderItemList.js

import React, { useEffect, useState } from 'react';
import OrderItem from './OrderItem'; // Import the OrderItem component

const getOrderItemsForUser = async (userId) => {
  console.log(userId)
   const apiUrl = `http://localhost:8090/api/order/${userId}/all`;
    const token=localStorage.getItem("token")
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    const data = await fetch(apiUrl, { headers });
    const list = await data.json();
    console.log(list);
  
    return list.content;
};

const getOrderItemsForChef= async ()=>{
    console.log('Fetching order items');
    const apiUrl = 'http://localhost:8090/api/order/all';
    const token=localStorage.getItem("token")
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    const data = await fetch(apiUrl, { headers });
    const list = await data.json();
    console.log(list);
  
    return list.content;
}



const OrderItemList = ({ user }) => {
  const [orderItems, setOrderItems] = useState([]);
  const userRole = user ? user.role : null;
console.log(user)
  useEffect(() => {
 if(user){
    const fetchOrderItems = async () => {
        try {
          if(userRole=="ROLE_CHEF"){
              const items = await getOrderItemsForChef();
              setOrderItems(items);
          }else if(userRole=="ROLE_USER"){
              const items = await getOrderItemsForUser(user.userId);
              setOrderItems(items);

          }
        
        } catch (error) {
          console.error('Error fetching order items:', error);
        }
      };
  
      fetchOrderItems();
 }

  }, [userRole]);

  return (
    <div>
      <div className="order-items-container">

      <table className="order-item-table">
  <thead>
    <tr>
      <th>Order ID</th>
      <th>Date</th>
      <th>Payment</th>
      <th>Total Amount</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
  {orderItems?.map((orderItem) => (
          <OrderItem key={orderItem.id} orderItem={orderItem} user={user} />
        ))}
  </tbody>
</table>

      
      </div>
    </div>
  );
};

export default OrderItemList;
