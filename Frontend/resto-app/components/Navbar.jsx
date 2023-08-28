"use client"
import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import{AiOutlineShoppingCart,AiOutlineHome,AiOutlineHistory,} from "react-icons/ai";
import { BiLogOut } from "react-icons/bi";
const Navbar = () => {

  const [active,setActive]=React.useState("Home");

  const handleClick=(itemName)=>{
setActive(itemName);

console.log(active);
  }
  return (
    <nav>
        <div className="logo"></div>
        <ul className='nav-icon-container'>
            <li className={`nav-icon-block ${active=="Home"?"active":""}`}   onClick={() => handleClick('Home')}> <AiOutlineHome className='nav-icon'/></li>
            <li className={`nav-icon-block ${active=="Cart"?"active":""}`}   onClick={() => handleClick('Cart')}> <AiOutlineShoppingCart className='nav-icon'/> </li>
            <li className={`nav-icon-block ${active=="History"?"active":""}`}   onClick={() => handleClick('History')}><AiOutlineHistory className='nav-icon'/></li>
            <li className="nav-icon-block"><BiLogOut className='nav-icon'/></li>
  
        </ul>
    </nav>
  )
}

export default Navbar