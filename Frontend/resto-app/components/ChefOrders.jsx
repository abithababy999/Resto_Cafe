import { formatTimestamp } from '@/util/formatter';
import React, { useEffect, useState } from 'react';
const getCartItemsForOrder = async (orderId) => {
    // Fetch cart items for the selected order from an API
    const apiUrl = `http://localhost:8090/api/order/${orderId}/cartitems`;
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    const data = await fetch(apiUrl, { headers });
    const cartItems = await data.json();
    return cartItems;
  };

const ChefOrders = ({ user }) => {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [cartItems, setCartItems] = useState([]);
  const [newStatus, setNewStatus] = useState(''); // Store the new status in state

  useEffect(() => {
    // Fetch the orders belonging to the chef's ID
    const fetchOrders = async () => {
      try {
        if (user) {
          const apiUrl = `http://localhost:8090/api/order/chef/${user.userId}`;
          const token = localStorage.getItem('token');
          const headers = {
            Authorization: `Bearer ${token}`,
          };
          const response = await fetch(apiUrl, { headers });

          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }

          const data = await response.json();
         
          setOrders(data?.content);
        }
      } catch (error) {
        console.error('Error fetching chef orders:', error);
      }
    };

    fetchOrders();
  }, [user]);

  const handleOrderStatusChange = async (orderId, newStatus) => {
    // Update the order status
    if(user){
        console.log(newStatus)
        try {
            // Make a PUT request to update the order status
            const apiUrl = `http://localhost:8090/api/order/${orderId}`;
            const token = localStorage.getItem('token');
            const headers = {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`,
            };
            const requestBody = {
              chefId:user.userId,
              status: newStatus,
            };
      
            const response = await fetch(apiUrl, {
              method: 'PUT',
              headers,
              body: JSON.stringify(requestBody),
            });
      
            if (!response.ok) {
              throw new Error(`HTTP error! Status: ${response.status}`);
            }
      
      closeCartModal();
      
            // Assuming you want to update the UI with the new status, you can re-fetch the orders here or update the specific order in the 'orders' state.
      
          } catch (error) {
            console.error('Error updating order status:', error);
            closeCartModal();
          }
    }
   
  };


  const openCartModal = async (orderId) => {
    try {
      const items = await getCartItemsForOrder(orderId); // Implement getCartItemsForOrder function
      setCartItems(items);
      setSelectedOrder(orderId);
      const selectedOrderObj = orders.find((order) => order.id === orderId);
      if (selectedOrderObj) {
        setNewStatus(selectedOrderObj.status);
      }
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  const closeCartModal = () => {
    setSelectedOrder(null);
    setCartItems([]);
  };


  return (
    <div className="chef-orders-container">
      <h1 className="chef-orders-title">Chef Orders</h1>
      <table className="chef-orders-table">
  <thead>
    <tr>
      <th>Order ID</th>
      <th>Order Date</th>
      <th>Paid</th>
      <th>Total Amount</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
    {orders?.map((order) => (
      <tr key={order.id} className="chef-orders-item" onClick={() => openCartModal(order.id)}>
        <td>{order.id}</td>
        <td>{formatTimestamp(order.timeStamp)}</td>
        <td>{order.paid ? 'Yes' : 'No'}</td>
        <td>${order.totalAmount?.toFixed(2)}</td>
        <td > <div className={`order-status-item order-status-${order.status?.toLowerCase()}`}>{order.status}</div></td>
      </tr>
    ))}
  </tbody>
</table>

      {selectedOrder !== null && (
        <div className="modal-overlay">
          <div className="modal">
            <div className="modal-content">
              <h2>Order Details</h2>
              <div className="modal-order-item-container">
              {cartItems.map((cartItem) => (
                <div key={cartItem.id} className="order-item">
                  <img src={cartItem.image} alt={cartItem.foodName} className="food-image" />
                  <div className="order-item-info">
                    <h3>{cartItem.foodName}</h3>
                    <p>Quantity: {cartItem.quantity}</p>
                  </div>
                </div>
              ))}
              </div>
          
              {/* Drop-down for changing order status */}
              <label htmlFor="status">Change Status:</label>
              <select
                id="status"
                value={newStatus}
                onChange={(e) => setNewStatus(e.target.value)}
                className="chef-order-select"
              >   
                    <option value="PENDING">Pending</option>
              <option value="PREPARING">Preparing</option>

          
             
                <option value="COOKED">Cooked</option>
                <option value="DELIVERED">Delivered</option>
              </select>
              <div className="modal-btn-container">
              <button onClick={() => handleOrderStatusChange(selectedOrder,newStatus)}>Update Status</button>
       
       <button onClick={closeCartModal}>Close</button>
              </div>
           
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ChefOrders;
