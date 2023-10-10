"use client"
import React, { createContext, useContext, useEffect, useState } from 'react';
import jwt from 'jsonwebtoken';
import { useRouter } from "next/navigation";
const UserContext = createContext();

export function useUser() {
  return useContext(UserContext);
}

export function UserProvider({ children }) {
  const [user, setUser] = useState(null);
  const router = useRouter()
  useEffect(() => {
    // Fetch the user data from localStorage on initial render
    const storedToken = localStorage?.getItem('token');
    if (storedToken) {
      // Decode the JWT token to extract user data (e.g., user ID, username, etc.)
      try {
        const userDecoded = decodeToken(storedToken);
        // Set the user state to the fetched data
        setUser(userDecoded);
      } catch (error) {
        // Handle token decoding error (e.g., expired token)
        console.error('Token decoding error:', error);
        // Redirect the user to the login page
        router.push('/Login');
      }
    } 
  }, []);
  

  // You can define a login function to set the user data after successful authentication
  const login = (token) => {
   const decodedUser= decodeToken(token);
  
   setUser(decodedUser);
   

   
    localStorage.setItem('token', token);

    if(decodedUser?.role ==="ROLE_CHEF"){
router.push("/Chef")
    }else if (decodedUser?.role==="ROLE_ADMIN"){
     
      router.push("/Admin")
    }else   if (decodedUser?.role==="ROLE_USER") {
      router.push("/")
    }
   
  };

  // You can define a logout function to clear the user data and token
  const logout = () => {
    // Clear the user data
    setUser(null);

   
    localStorage.removeItem('token'); 
    router.push("/Login")
    

  };

  // Define your JWT decoding logic here
  const decodeToken = (token) => {
    const decodedToken = jwt.decode(token);
 
    if(!decodedToken){
console.error("invalid token")
    }
    return {userName:decodedToken.sub,firstName:decodedToken.firstName,lastName:decodedToken.lastName,phoneNumber:decodedToken.phoneNumber,userId:decodedToken.userId,role:decodedToken.roles[0]} 

  
  };

  return (
    <UserContext.Provider value={{ user, login, logout }}>
      {children}
    </UserContext.Provider>
  );
}
