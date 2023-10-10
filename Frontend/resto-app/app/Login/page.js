"use client"
import React, { useState ,useEffect} from 'react';
import { useRouter } from "next/navigation";
import { useUser } from '@/context/UserContext';

import Loading from './loading';

const Login = () => {

    
  const [isLogin, setIsLogin] = useState(true); // To toggle between login and signup
  const [error, setError] = useState(null);
 const {login,user} =useUser();
 const [isLoading, setIsLoading] = useState(false);

 const router = useRouter();

 useEffect(() => {
  if (user) {
    if (user.role === 'ROLE_CHEF') {
      router.push('/Chef');
    } else if (user.role === 'ROLE_ADMIN') {
      router.push('/Admin');
    } else if (user.role === 'ROLE_USER') {
      router.push('/');
    }
  }
}, [user, router]);

  const toggleForm = () => {
    setIsLogin(!isLogin);
    setError(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setIsLoading(true)

    const userName = e.target.userName.value;
    const userPassword = e.target.userPassword.value;

    const requestBody = {
      userName,
      userPassword,
    };

    if (!isLogin) {
      const firstName = e.target.firstName.value;
      const lastName = e.target.lastName.value;
      const phoneNumber = e.target.phoneNumber.value;

      requestBody.firstName = firstName;
      requestBody.lastName = lastName;
      requestBody.phoneNumber = phoneNumber;
    }

    try {
      if (isLogin) {
        // Perform login
        const response = await fetch('http://localhost:8088/api/auth/authenticate', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(requestBody),
        });

        if (response.ok) {
          // Login successful

          const data = await response.json();
        
          login(data.jwtToken)
  
          
    
        } else {
          setError('Login failed. Please check your credentials.');
        
        }
      } else {
        // Perform sign-up
        console.log(requestBody);
        const response = await fetch('http://localhost:8088/api/auth/registerNewUser', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(requestBody),
        });

        if (response.ok) {
          // Sign-up successful, you can optionally redirect to the login page
          setIsLogin(true);
        } else {
          setError('Sign-up failed. Please try again later.');
        }
      }
    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred. Please try again later.');
    }
    finally{
      setIsLoading(false);
    }
  };
  if (isLoading) {
    // Show a loading indicator
    return <Loading/>;
  }


  return (
   <div className='page-container'>
 <div className='content-container'>

    <div className="login-container">
      <h1 className="title">{isLogin ? 'Login' : 'Sign Up'}</h1>
      {error && <p className="error">{error}</p>}
      <form className="form-container" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="userName">User Name:</label>
          <input type="text" id="userName" name="userName" required />
        </div>
        {!isLogin && (
          <>
            <div className="form-group">
              <label htmlFor="firstName">First Name:</label>
              <input type="text" id="firstName" name="firstName" required />
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last Name:</label>
              <input type="text" id="lastName" name="lastName" required />
            </div>
            <div className="form-group">
              <label htmlFor="phoneNumber">Phone Number:</label>
              <input type="text" id="phoneNumber" name="phoneNumber" required />
            </div>
          </>
        )}
        <div className="form-group">
          <label htmlFor="userPassword">Password:</label>
          <input type="password" id="userPassword" name="userPassword" required />
        </div>
        <button type="submit" className="submit-button">
          {isLogin ? 'Login' : 'Sign Up'}
        </button>
      </form>
      <p className="toggle-text">
        {isLogin ? "Don't have an account?" : 'Already have an account?'}{' '}
        <button type="button" onClick={toggleForm} className="toggle-button">
          {isLogin ? 'Sign Up' : 'Login'}
        </button>
      </p>
    </div>
    </div>
    </div>
  );
};

export default Login;
