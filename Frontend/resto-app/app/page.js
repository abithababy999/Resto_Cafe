import HomeHeader from '@/components/HomeHeader'
import Image from 'next/image'
import styles from './page.module.css'
import { Roboto,self } from 'next/font/google'

const roboto=Roboto({weight:['400','700'],subsets: ['latin']})
export default function Home() {
  return (
    <main className={roboto.className}>
  
     <HomeHeader/>
    
    </main>
  )
}
