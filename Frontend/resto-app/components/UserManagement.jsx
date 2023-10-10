import React, { useState, useEffect } from 'react';

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);


  const fetchUsers = async () => {
    try {
      const apiUrl = 'http://localhost:8088/api/auth/authority-user'; // Replace with your API endpoint
      const token = localStorage.getItem('token'); // Assuming you have user authentication
      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const response = await fetch(apiUrl, { headers });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  useEffect(() => {
    // Fetch users with roles 'admin' or 'chef' from your API

    fetchUsers();
  }, []);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
  
    const formData = new FormData(event.target);
    const role = formData.get('role');
    console.log(...formData);
  
    // Create the request body using the UserRegistrationRequest structure
    const requestBody = {
      userName: formData.get('userName'),
      firstName: formData.get('firstName'),
      lastName: formData.get('lastName'),
      userPassword: formData.get('userPassword'), // Assuming 'userPassword' matches your class structure
      phoneNumber: formData.get('phoneNumber'),
   
    };
  
    console.log(requestBody);
  
    const response = await fetch(`http://localhost:8088/api/auth/auth-adder?role=${formData.get('role').toUpperCase()}`, {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    });
  
    if (response.ok) {
      closeModal();
      alert('User created successfully!');
      fetchUsers();
    } else {
      alert('Error creating user.');
    }
  };
  
  return (
    <div className="user-management-container">
        <div className="user-management-header">

        <h1 className="user-management-title">User Management</h1>
      <button onClick={openModal}>Add New User</button>
        </div>

      <table className="user-management-table">
        <thead>
          <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          {users?.map((user) => (
            <tr key={user.userid}>
              <td>{user.userid}</td>
              <td>{user.userName}</td>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
              <td>{user.phoneNumber}</td>
              <td>{user.role}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {isModalOpen && (
        <div className="user-management-modal">
          <div className="user-management-modal-content">
            <h2>Create New User</h2>
            <form className="user-management-modal-form"onSubmit={handleSubmit}>
              <div className="user-management-modal-form-group">
                <label>User Name</label>
                <input type="text" name="userName" />
              </div>
              <div className="user-management-modal-form-group">
                <label>First Name</label>
                <input type="text" name="firstName" />
              </div>
              <div className="user-management-modal-form-group">
                <label>Last Name</label>
                <input type="text" name="lastName" />
              </div>
              <div className="user-management-modal-form-group">
                <label>Phone Number</label>
                <input type="text" name="phoneNumber" />
              </div>
              <div className="user-management-modal-form-group">
                <label>Password</label>
                <input type="password" name="userPassword" />
              </div>
              <div className="user-management-modal-form-group">
                <label>Role</label>
                <select name="role">
                  <option value="admin">Admin</option>
                  <option value="chef">Chef</option>
                </select>
              </div>
              <button type="submit">Create User</button>
            </form>
            <button onClick={closeModal} className="user-management-modal-close">
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserManagement;
