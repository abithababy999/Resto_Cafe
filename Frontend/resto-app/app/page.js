
import React, { Suspense } from 'react';
import HomeHeader from '@/components/HomeHeader';
import FoodItemList from '@/components/FoodItemList';
import CartSidebar from '@/components/CartSidebar';
import Loading from './loading';

const getFoodItems = async () => {

  const apiUrl = 'http://localhost:8089/api/foodservice/foodItems';
  const data = await fetch(apiUrl, { next: { revalidate: 3600 } });
  const list = await data.json();

  return list.fooditems;
};
const FoodSearchPage = async () => {
const foodItems=await getFoodItems();


  return (
    <Suspense fallback={<Loading/>}>
      <main>
        <HomeHeader />

       <FoodItemList foodItems={foodItems} />

        <CartSidebar />
      </main>
    </Suspense>
  );
};

export default FoodSearchPage;
