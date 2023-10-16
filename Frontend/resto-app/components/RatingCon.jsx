import React, { useEffect, useState } from 'react';
import Rating from '@mui/material/Rating';

const RatingCon = ({ orderId, foodId }) => {
  const [currentValue, setCurrentValue] = useState(0);

  useEffect(() => {
    const fetchRating = async () => {
      const apiUrl = `http://localhost:8089/api/foodservice/food/${foodId}/order/${orderId}`;
      const token = localStorage.getItem('token');

      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const response = await fetch(apiUrl, { headers });

      if (response.ok) {
        const data = await response.json();
        
        // Assuming the rating value is in the 'ratingScore' field of the response
        if (data && data.ratingScore !== undefined) {
          setCurrentValue(data.ratingScore);
        }
      } else {
        // Handle the case where the response is not OK (e.g., non-200 status code)
        setCurrentValue(0);
      }
    };

    fetchRating(); // Call the fetchRating function

  }, [orderId, foodId]); // Add 'orderId' and 'foodId' to the dependency array

  return (
    <Rating name="half-rating" value={currentValue} onChange={(event, newValue) => console.log(newValue)} />
  );
}

export default RatingCon;
