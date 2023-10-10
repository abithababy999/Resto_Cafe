"use client"
import React, { useState, useEffect } from 'react';
import { useUser } from '@/context/UserContext';
import { useFoodItems } from '@/context/FoodItemContext';
import debounce from 'lodash/debounce';

import { capitalize } from '@/util/formatter';
const HomeHeader = () => {
  const { user } = useUser();
  
  const {foodItemsSearch, setFoodItems,searchQuery, setSearchQuery}=useFoodItems()



  const formatDate = (date) => {
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString(undefined, options);
  };

  const today = new Date();

  const handleInputChange = (e) => {
    const newValue = e.target.value;
    setSearchQuery(newValue);
  
  };

  useEffect(() => {
    const debouncedFetchFoodItems = debounce(async () => {
      if (searchQuery.length === 0) {
        setFoodItems([]);
    
        return;
      }



      try {
        const response = await fetch(`http://localhost:8089/api/foodservice/food/search?searchTerm=${searchQuery}`);

        if (!response.ok) {
          throw new Error(`HTTP Error! Status: ${response.status}`);
        }

        const data = await response.json();
        
        setFoodItems(data);
      } catch (error) {
   
      } finally {
      
      }
    }, 300);

    debouncedFetchFoodItems();

    return () => {
      debouncedFetchFoodItems.cancel();
    };
  }, [searchQuery]);

  return (
    <header className="home-header">
      <div className="greeter-container">
        <h2>Hello {user ? capitalize(user.firstName) : ''}</h2>
        <div className="date-header">{formatDate(today)}</div>
      </div>
      {(user && user.role === "ROLE_USER") || !user ? (
        <div className="search-bar">
          <input
            type="text"
            placeholder="Search..."
            value={searchQuery}
            onChange={handleInputChange}
          />
        </div>
      ) : null}
    </header>
  );
};

export default HomeHeader;