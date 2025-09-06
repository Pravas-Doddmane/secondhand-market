// src/pages/ProfilePage.js
import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import api from '../api/axios';
import MyListingCard from '../components/MyListingCard';
import './ProfilePage.css';

const ProfilePage = () => {
  const { user } = useAuth();
  const location = useLocation();

  const [listings, setListings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchListings = async () => {
    if (!user) {
      setLoading(false);
      return;
    }
    setLoading(true);
    try {
      // 👇 If your backend supports `/products/me`, use that instead
      const res = await api.get(`/products`);

      // Handle both array and { content: [] }
      const data = Array.isArray(res.data) ? res.data : res.data.content || [];

      setListings(data);
      setError(null);
    } catch (err) {
      setError("Failed to load your listings.");
      console.error("Failed to fetch listings:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchListings();
  }, [location.state]); // 👈 refresh when redirected from AddProductPage

  const handleDelete = async (productId) => {
    try {
      await api.delete(`/products/${productId}`);
      alert("Listing deleted successfully!");
      fetchListings();
    } catch (err) {
      alert("Failed to delete listing.");
      console.error("Delete error:", err);
    }
  };

  if (loading) return <div className="loading-message">Loading listings...</div>;
  if (error) return <div className="error-message">{error}</div>;

  return (
    <div className="profile-container">
      <header className="profile-header">
        <h1 className="app-name">SecondHandMarket</h1>
        <Link to="/profile/edit" className="more-options-btn">
          <span className="more-icon">☰</span>
        </Link>
      </header>

      <div className="profile-content">
        <Link to="/products/new" className="add-new-btn">Add new</Link>

        <div className="listings-grid">
          {listings.length > 0 ? (
            listings.map((listing) => (
              <MyListingCard
                key={listing.id}
                product={listing}
                onDelete={handleDelete}
              />
            ))
          ) : (
            <p className="no-listings">You have no products listed.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
