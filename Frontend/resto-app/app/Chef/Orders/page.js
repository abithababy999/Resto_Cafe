"use client"
import {useState,useEffect} from 'react';
import HomeHeader from '@/components/HomeHeader'
import FoodItemList from '@/components/FoodItemList';
import CartSidebar from '@/components/CartSidebar';

import ChefOrders from '@/components/ChefOrders';
import Image from 'next/image'

import { Roboto,self } from 'next/font/google'
import { useUser } from '@/context/UserContext';
import { Router } from 'next/navigation';
import { useRouter } from "next/navigation";




const roboto=Roboto({weight:['400','700'],subsets: ['latin']})




export default function ChefOrder() {
const {user}=useUser();
const router=useRouter();

const [isUserDataLoaded, setIsUserDataLoaded] = useState(false);


useEffect(() => {
  setIsUserDataLoaded(true);
}, [user]);

if (!isUserDataLoaded) {
  return null;
}
if (!user || user.role !== 'ROLE_CHEF') {
  router.push('/Login');
  return null;
}

  return (
    <>
    <main className={roboto.className}>
  
     <HomeHeader/>

   <ChefOrders user={user}/>


    
    </main>

    
        </>
  )
}
