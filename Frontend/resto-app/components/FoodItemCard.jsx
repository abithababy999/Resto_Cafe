
"use client"
import React from 'react';
import { useCart } from '@/context/CartContext';
import { useUser } from '@/context/UserContext';

const FoodItemCard = ({ foodId, image, price, name, description, rating }) => {
  const { cart, addToCart, increaseQuantity } = useCart();

  const handleAddToCart = () => {
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
    <div className="food-item-card">
      <img src={image} alt={name} className="food-item-image" />
      <div className="food-item-details">
        <h4>{name}</h4>
        <p className="food-item-description">{description}</p>
        <p className="food-item-price">${price}</p>
        <div className="food-item-rating">
          {/* Rating: {rating} */}
        </div>
     
      </div>
      <button  onClick={handleAddToCart}>Add to Cart</button>
    </div>
  );
};

export default FoodItemCard;
