"use client"
import React, { useContext,createContext, useState } from 'react';

const LoadingContext = createContext();

export const LoadingProvider = ({ children }) => {
  const [isLoading, setIsLoading] = useState(false);

  const stopLoading=()=>{
    setIsLoading(false)
  }

  const startLoading=()=>{
    setIsLoading(true)
  }
  return (
    <LoadingContext.Provider value={{ isLoading,startLoading,stopLoading }}>
      {children}
    </LoadingContext.Provider>
  );
};


export function useLoading() {
  return useContext(LoadingContext);
}
