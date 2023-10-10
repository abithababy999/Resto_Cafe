"use client"
import HomeHeader from '@/components/HomeHeader'
import FoodItemList from '@/components/FoodItemList';
import CartSidebar from '@/components/CartSidebar';
import OrderItemList from '@/components/OrderItemList';
import Image from 'next/image'
import { useState,useEffect } from 'react';
import { Roboto,self } from 'next/font/google'
import { useUser } from '@/context/UserContext';
import { Router } from 'next/navigation';
import { useRouter } from "next/navigation";
import UserManagement from '@/components/UserManagement';




const roboto=Roboto({weight:['400','700'],subsets: ['latin']})



export default function Admin() {
const {user}=useUser();
const router=useRouter();


const [isUserDataLoaded, setIsUserDataLoaded] = useState(false);

useEffect(() => {
  setIsUserDataLoaded(true);
}, [user]);

if (!isUserDataLoaded) {
  return null;
}
if (!user || user.role !== 'ROLE_ADMIN') {
  router.push('/Login');
  return null;
}
  return (
    <>
    <main className={roboto.className}>
  
   <HomeHeader/>
     <UserManagement/>


    
    </main>

    
        </>
  )
}
