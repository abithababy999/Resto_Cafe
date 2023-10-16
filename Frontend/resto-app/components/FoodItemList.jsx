"use client"
import React from 'react';
import FoodItemCard from './FoodItemCard';
import { useUser } from '@/context/UserContext';
import { useFoodItems } from '@/context/FoodItemContext';
const NoFoodItemsIcon = () => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width="100"
    height="100"
    viewBox="0 0 100 100"
    fill="#ccc" // Customize the color as needed
  >
    <circle cx="50" cy="50" r="40" />
    <text x="30" y="55" fontSize="18" fill="#fff">No food items found</text>
  </svg>
);
const FoodItemList = ({ foodItems }) => {
  const { foodItemsSearch, searchQuery } = useFoodItems();

  // Determine which set of food items to display based on the search query
  const itemsToDisplay = searchQuery ? foodItemsSearch : foodItems;
  if (itemsToDisplay.length === 0) {
    return (
      <div className="no-food-items">
        <NoFoodItemsIcon />
        {/* You can add additional UI elements or messages here */}
      </div>
    );
  }
  return (
    <div>
    
      <div className="food-items-container">
        {itemsToDisplay.map((foodItem) => (
          <FoodItemCard key={foodItem.foodId} {...foodItem} />
        ))}
      </div>
    </div>
  );
};

export default FoodItemList;
