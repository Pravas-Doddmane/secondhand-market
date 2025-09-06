import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../api/axios';
import './AddEditProduct.css';

const EditProductPage = () => {
  const { id } = useParams();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [price, setPrice] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const res = await api.get(`/products/${id}`);
        const product = res.data;
        setTitle(product.title);
        setDescription(product.description);
        setCategory(product.category);
        setPrice(product.price);
      } catch (err) {
        setError("Failed to load product details.");
      } finally {
        setLoading(false);
      }
    };
    fetchProduct();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    // Frontend validation
    if (!title || !description || !category || !price) {
      setError("All fields are required.");
      return;
    }

    try {
      await api.put(`/products/${id}`, {
        title,
        description,
        category,
        price: parseFloat(price),
        imageUrl: 'https://dummyimage.com/200x200/d3d3d3/000000&text=Product' // Include imageUrl
      });
      alert("Product updated successfully!");
      navigate('/profile');
    } catch (err) {
      setError(err.response?.data?.error || "Failed to update product.");
    }
  };

  if (loading) return <div className="loading-message">Loading product for editing...</div>;
  
  return (
    <div className="form-container">
      <h2>Edit Product</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} required />
        <textarea placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} required />
        <input type="text" placeholder="Category" value={category} onChange={(e) => setCategory(e.target.value)} required />
        <input type="number" placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)} required />
        <button type="submit">Save Changes</button>
        {error && <p className="error">{error}</p>}
      </form>
    </div>
  );
};

export default EditProductPage;