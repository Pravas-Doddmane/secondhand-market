import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';
import './AddEditProduct.css';

const AddProductPage = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [price, setPrice] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    if (!title || !description || !category || !price) {
      setError("All fields are required.");
      return;
    }

    try {
      await api.post('/products', {
        title,
        description,
        category,
        price: parseFloat(price),
        imageUrl: 'https://dummyimage.com/200x200/d3d3d3/000000&text=Product'
      });
      alert("Product added successfully!");
      // Redirect to the profile page with a state flag to trigger a re-fetch
      navigate('/profile', { state: { refresh: true } });
    } catch (err) {
      setError(err.response?.data?.error || "Failed to add product.");
    }
  };

  return (
    <div className="form-container">
      <h2>Add New Product</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} required />
        <textarea placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} required />
        <input type="text" placeholder="Category" value={category} onChange={(e) => setCategory(e.target.value)} required />
        <input type="number" placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)} required />
        <button type="submit">Submit Listing</button>
        {error && <p className="error">{error}</p>}
      </form>
    </div>
  );
};

export default AddProductPage;