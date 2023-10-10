import React, { useState, useEffect } from 'react';
import { useFoodItems } from '@/context/FoodItemContext';
import { AiOutlinePlus, AiOutlineEdit } from 'react-icons/ai';

const MenuItems = () => {
  const [menuItems, setMenuItems] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [isCreateMode, setIsCreateMode] = useState(true); // Track create/update mode
  const [selectedItemId, setSelectedItemId] = useState(null); // Track selected item for update
  const { foodItems, searchQuery, setSearchQuery } = useFoodItems();

  // State to hold initial data for update
  const [initialUpdateData, setInitialUpdateData] = useState({
    name: '',
    description: '',
    availability: false,
    price: 0,
    image: '',
    category: '',
    dietry: '',
  });

  useEffect(() => {
   
    if (selectedItemId !== null && !isCreateMode) {
      // Find the item with the matching ID
      const selectedItem = foodItems.find((item) => item.foodId === selectedItemId);
console.log(selectedItem)
      if (selectedItem) {
        // Populate the initial data for update
        setInitialUpdateData({
          name: selectedItem.name,
          description: selectedItem.description,
          availability: selectedItem.availability,
          price: selectedItem.price,
          image: selectedItem.image,
          category: selectedItem.category,
          dietry: selectedItem.dietry,
        });
      }
    }
  }, [selectedItemId, isCreateMode, foodItems]);

  const openModal = () => {
    setModalOpen(true);
    setIsCreateMode(true); // Set to create mode by default
    setSelectedItemId(null); // Reset selected item ID
  };

  const closeModal = () => {
    setModalOpen(false);
    setIsCreateMode(true); // Reset to create mode when closing
    setSelectedItemId(null); // Reset selected item ID when closing
  setInitialUpdateData({
    name: '',
    description: '',
    availability: false,
    price: 0,
    image: '',
    category: '',
    dietry: '',
  })
  };

  const createNewMenuItem = async (formData) => {
    console.log(formData);
    try {
      const apiUrl = 'http://localhost:8089/api/foodservice/addFoodItem';
  
      const token = localStorage.getItem('token'); // Get the token from local storage
  
      if (!token) {
        // Handle the case where the token is not available
        console.error('Token is missing.');
        return;
      }
  
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`, // Include the token in the "Authorization" header
        },
        body: JSON.stringify(formData),
      });
  
      if (response.ok) {
        // Item created successfully, you can fetch the updated list here if needed
        closeModal();
        alert('Menu item created successfully!');
      } else {
        alert('Error creating menu item.');
      }
    } catch (error) {
      console.error('Error creating menu item:', error);
    }
  };
 
  const updateMenuItem = async (formData, itemId) => {
    // Implement update logic here
    console.log('Updating menu item ID', itemId, 'with data:', formData);
    
    try {
      const apiUrl = 'http://localhost:8089/api/foodservice/updatefooditem/'+itemId;
  
      const token = localStorage.getItem('token'); // Get the token from local storage
  
      if (!token) {
        // Handle the case where the token is not available
        console.error('Token is missing.');
        return;
      }
  
      const response = await fetch(apiUrl, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`, // Include the token in the "Authorization" header
        },
        body: JSON.stringify(formData),
      });
  
      if (response.ok) {
        // Item created successfully, you can fetch the updated list here if needed
        closeModal();
        alert('Menu item created successfully!');
      } else {
        alert('Error creating menu item.');
      }
    } catch (error) {
      console.error('Error creating menu item:', error);
    }
    // ...
  };

  const handleUpdateClick = (itemId) => {
    // Set the selected item ID and switch to update mode
    setSelectedItemId(itemId);
    setIsCreateMode(false);
    setModalOpen(true);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const imageSet = formData.get('image').name.trim() === '' ? (initialUpdateData.image || '/assest/food/default_image.jpg') :  "/assests/food/" +formData.get('image').name;

    if (isCreateMode) {
      createNewMenuItem({
        name: formData.get('name'),
        description: formData.get('description'),
        availability: formData.get('availability') === 'on',
        price: parseFloat(formData.get('price')),
        image: imageSet,
        category: formData.get('category'),
        dietry: formData.get('dietry'),
      });
    } else {
      // In update mode, call the updateMenuItem function with the selected item ID
      updateMenuItem(
        {
          name: formData.get('name'),
          description: formData.get('description'),
          availability: formData.get('availability') === 'on',
          price: parseFloat(formData.get('price')),
          image:imageSet,
          category: formData.get('category'),
          dietry: formData.get('dietry'),
        },
        selectedItemId
      );
    }

    closeModal();
  };

  return (
    <div className="product-management-container">
      <h1 className="product-management-title">Product Management</h1>
      <div className="menu-items-container">
      <button className="menu-item-add-btn" onClick={openModal}>
          <span>
            {isCreateMode ? <AiOutlinePlus className='menu-item-icon' /> : <AiOutlineEdit />}{' '}
          </span>
          <span>{isCreateMode ? 'Add New Menu' : 'Update Menu'}</span>
        </button>
        {foodItems.map((item, index) => (
          <div key={index} className="menu-item-card">
            <h2 className="menu-item-name">{item.name}</h2>
            <p className="menu-item-description">{item.description}</p>
            <p className="menu-item-availability">
              Availability: {item.availability ? 'Yes' : 'No'}
            </p>
            <p className="menu-item-price">Price: ${item.price}</p>
            <p className="menu-item-category">Category: {item.category}</p>
            <p className="menu-item-dietary">Dietary: {item.dietry}</p>
            <img className="menu-item-image" src={item.image} alt={item.name} />
            <button
              className="menu-item-update-button"
              onClick={() => handleUpdateClick(item.foodId)} // Pass the item ID for update
            >
              <AiOutlineEdit /> Update
            </button>
          </div>
        ))}
   
      </div>
      {modalOpen && (
        <div  className="menu-item-modal">
          <div className="menu-item-modal-content">
            <h2>
              {isCreateMode ? 'Add New Menu Item' : 'Update Menu Item'}
            </h2>
            <form onSubmit={handleSubmit}>
              <div className="product-management-modal-form-group">
                <label htmlFor="name">Name</label>
                <input type="text" name="name" defaultValue={initialUpdateData.name} />
              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="description">Description</label>
                <input type="text" name="description" defaultValue={initialUpdateData.description} />
              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="availability">Availability</label>
                <input
                  type="checkbox"
                  name="availability"
                  defaultChecked={initialUpdateData.availability}
                />
              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="price">Price</label>
                <input type="number" name="price" value={initialUpdateData.price} onChange={()=>{}}/>

              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="image">Image</label>
                <input type="file" name="image"  />
              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="category">Category</label>
                <input type="text" name="category" defaultValue={initialUpdateData.category} />
              </div>
              <div className="product-management-modal-form-group">
                <label htmlFor="dietry">Dietary</label>
                <input type="text" name="dietry" defaultValue={initialUpdateData.dietry} />
              </div>
              <div className="modal-btn-container">
              <button type="submit">
                {isCreateMode ? 'Create Menu Item' : 'Update Menu Item'}
              </button>
              <button
                onClick={closeModal}
                className="product-management-modal-close"
              >
                Close
              </button>
              </div>
          
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default MenuItems;
