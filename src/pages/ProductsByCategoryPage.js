import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import api from '../api/axios';
import { useAuth } from '../context/AuthContext';
import ProductItemCard from '../components/ProductItemCard';
import './ProductsByCategoryPage.css';

const ProductsByCategoryPage = () => {
  const { category } = useParams();
  const { user } = useAuth();
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      setLoading(true);
      try {
        const res = await api.get(`/products?category=${category}`);
        setProducts(res.data.content);
        setError(null);
      } catch (err) {
        setError("Failed to load products. Please try again.");
        console.error("Error fetching products:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, [category]);

  const handleAddToCart = async (productId) => {
    if (!user) {
      alert("Please log in to add items to your cart.");
      return;
    }
    try {
      await api.post(`/cart/${productId}`);
      alert("Product added to cart!");
    } catch (err) {
      alert("Failed to add product to cart.");
      console.error("Add to cart error:", err);
    }
  };

  if (loading) return <div className="loading-message">Loading products...</div>;
  if (error) return <div className="error-message">{error}</div>;

  return (
    <div className="products-by-category-container">
      <header className="category-header">
        <Link to="/dashboard" className="back-button">← Back</Link>
        <h1>{category.charAt(0).toUpperCase() + category.slice(1)} Products</h1>
      </header>
      <div className="product-list">
        {products.length > 0 ? (
          products.map(product => (
            <ProductItemCard key={product.id} product={product} onAddToCart={() => handleAddToCart(product.id)} />
          ))
        ) : (
          <p>No products found in this category.</p>
        )}
      </div>
    </div>
  );
};

export default ProductsByCategoryPage;