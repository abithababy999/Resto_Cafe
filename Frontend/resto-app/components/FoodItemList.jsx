"use client"
import React from 'react';
import FoodItemCard from './FoodItemCard';
import { useUser } from '@/context/UserContext';
import { useFoodItems } from '@/context/FoodItemContext';

const FoodItemList = ({ foodItems }) => {
  const { foodItemsSearch, searchQuery } = useFoodItems();

  // Determine which set of food items to display based on the search query
  const itemsToDisplay = searchQuery ? foodItemsSearch : foodItems;

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
