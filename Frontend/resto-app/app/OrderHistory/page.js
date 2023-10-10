"use client"
import HomeHeader from '@/components/HomeHeader'
import FoodItemList from '@/components/FoodItemList';
import CartSidebar from '@/components/CartSidebar';
import OrderItemList from '@/components/OrderItemList';
import Image from 'next/image'

import { Roboto,self } from 'next/font/google'
import { useUser } from '@/context/UserContext';
import { Router } from 'next/navigation';
import { useRouter } from "next/navigation";




const roboto=Roboto({weight:['400','700'],subsets: ['latin']})



export default function Chef() {
const {user}=useUser();
const router=useRouter();

// if(!user || user.role !=="ROLE_CHEF"){
// router.push("/Login")
// }

  return (
    <>
    <main className={roboto.className}>
  
     <HomeHeader/>
     <OrderItemList user={user}/>
     


    
    </main>

    
        </>
  )
}
