
"use client"
import React from 'react';
import { useCart } from '@/context/CartContext';
import { useUser } from '@/context/UserContext';
import Rating from '@mui/material/Rating';
const FoodItemCard = ({ foodId, image, price, name,availability, description, rating,dietry}) => {
  const { cart, addToCart, increaseQuantity } = useCart();

  const handleAddToCart = () => {
    console.log(availability)
    const existingCartItem = cart.find((item) => item.foodId === foodId);

    if (existingCartItem) {
      // If the item is already in the cart, increase its quantity
      increaseQuantity(foodId);
    } else {
      // If the item is not in the cart, add it as a new item
      const cartItem = {
        foodId,
        image,
        price,
        name,
        description,
        rating,
      };

      addToCart(cartItem);
    }
  };

  return (
    <div className={`food-item-card ${!availability?"disabled":""}`}>
      <img src={image} alt={name} className="food-item-image" />
      <div className="food-item-details">
        <h4>{name}</h4>
        <p className="food-item-description" data-text={description}>{description}</p>
        <p className="food-item-price">${price}</p>
        <p className={`food-item-dietry ${dietry.toLowerCase()==="non veg"?"non":"veg"}`}>{dietry.toLowerCase()}</p>
        <div className="food-item-rating">
        
        </div>
     
      </div>
      <button  onClick={handleAddToCart}disabled={!availability}>Add to Cart</button>
    </div>
  );
};

export default FoodItemCard;
