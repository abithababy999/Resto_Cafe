// FoodItemsProvider.js
"use client"
import React, { createContext, useContext, useEffect, useState } from 'react';

const FoodItemsContext = createContext();

export const useFoodItems = () => {
  return useContext(FoodItemsContext);
};



export const FoodItemsProvider = ({ children }) => {
  const [foodItemsSearch, setFoodItems] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  // Set the initial state of the foodItems state variable after fetching
 
  // You can add functions for filtering and searching here

  return (
    <FoodItemsContext.Provider value={{ foodItemsSearch, setFoodItems, searchQuery, setSearchQuery }}>
      {children}
    </FoodItemsContext.Provider>
  );
};
