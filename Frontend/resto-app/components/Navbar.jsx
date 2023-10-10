"use client"
import React, { useState,useEffect,useMemo } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useUser } from '@/context/UserContext';
import {
  AiOutlineShoppingCart,
  AiOutlineHome,
  AiOutlineHistory,


} from 'react-icons/ai';
import { BiLogOut,BiLogIn} from 'react-icons/bi';
import { PiCookingPotBold } from 'react-icons/pi';
import Link from 'next/link';
import {FaPizzaSlice} from 'react-icons/fa'

import { useRouter,usePathname } from 'next/navigation';
import { useCart } from '@/context/CartContext';
const Navbar = () => {
  const [active, setActive] = useState('/');
  const { user, logout } = useUser();
  const {setCartIsOpen}=useCart();

  const handleClick = (itemName) => {
    setActive(itemName);
  };

  const router = useRouter();
  const pathname = usePathname();

  // Use the useRouter() hook to get the current pathname
  const currentPathname = useMemo(() => pathname, [router]);

  // Set the pathname state to the current pathname on refresh
  useEffect(() => {
    setActive(currentPathname);
    console.log(currentPathname)
  }, [currentPathname]);

  const handleLogout = () => {
    // Call the logout function from the user context when the logout button is clicked
    logout();
  };
  const toggleCart=()=>{
setCartIsOpen(prev=>!prev);
  }

  const getNavbarItems = () => {
    if (user?.role === 'ROLE_CHEF') {
      return [
        { name: '/Chef', icon: <AiOutlineHome className='nav-icon' />, link: '/Chef' },
        { name: '/Chef/Orders', icon: <PiCookingPotBold  className='nav-icon' />, link: '/Chef/Orders' },
        { name: '/Logout', icon: <BiLogOut  className='nav-icon' />, onClick: handleLogout },
      ];
    } else if (user?.role === 'ROLE_USER') {
      return [
        { name: '/', icon: <AiOutlineHome  className='nav-icon' />, link: '/' },
        { name: '/Cart', icon: <AiOutlineShoppingCart  className='nav-icon' />, onClick:toggleCart},
        {
          name: '/OrderHistory',
          icon: <AiOutlineHistory  className='nav-icon' />,
          link: '/OrderHistory',
        },
        { name: '/Logout', icon: <BiLogOut  className='nav-icon' />, onClick: handleLogout },
      ];
    } else if (user?.role === 'ROLE_ADMIN') {
      return [
        { name: '/Admin', icon: <AiOutlineHome  className='nav-icon' />, link: '/Admin' },
        { name: '/Admin/Menu', icon: <FaPizzaSlice className='nav-icon'/>, link: '/Admin/Menu' },
        { name: '/Logout', icon: <BiLogOut  className='nav-icon' />, onClick: handleLogout },
      ];
    }
    return [];
  };

  const navbarItems = getNavbarItems();

  return (
    <nav>
      <div className="logo"></div>
      <ul className="nav-icon-container">
        {user ? ( // Check if user is logged in
          navbarItems.map((item) => (
            <li
              key={item.name}
              className={`nav-icon-block ${
                active === item.name ? 'active' : ''
              }`}
              onClick={() => handleClick(item.name)}
            >
              {item.link ? (
                <Link href={item.link}>{item.icon}</Link>
              ) : (
                <button onClick={item.onClick}>{item.icon}</button>
              )}
            </li>
          ))
        ) : (
          // Render navigation items for non-logged-in users
          <>
            <li className={`nav-icon-block ${active === '/' ? 'active' : ''}`}>
              <Link href='/'><AiOutlineHome className='nav-icon' /></Link>
            </li>
            <li className={`nav-icon-block ${active === '/Login' ? 'active' : ''}`}>
              <Link href='/Login'><BiLogIn className='nav-icon'/></Link>
            </li>
            {/* Add more non-logged-in navigation items as needed */}
          </>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
