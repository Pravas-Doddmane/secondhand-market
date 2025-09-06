import React, { useState } from 'react';
import { FaShoppingCart, FaSearch } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import CategoryCard from '../components/CategoryCard';
import './DashboardPage.css';

const DashboardPage = () => {
  const [searchQuery, setSearchQuery] = useState('');

  const categories = ['Apparel', 'Electronics', 'Books', 'Furniture', 'Toys', 'Decor'];

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const filteredCategories = categories.filter(category =>
    category.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <div className="header-left">
          <h1 className="app-name">SecondHandMarket</h1>
        </div>
        <div className="header-right">
          <Link to="/cart">
            <FaShoppingCart className="header-icon" />
          </Link>
          <Link to="/profile">
            <div className="profile-placeholder"></div>
          </Link>
        </div>
      </header>

      <div className="search-bar-container">
        <input
          type="text"
          placeholder="Search for products..."
          value={searchQuery}
          onChange={handleSearchChange}
          className="search-input"
        />
        <FaSearch className="search-icon" />
      </div>
      
      <div className="categories-header">
        <h2>Categories</h2>
        <span className="dropdown-arrow">▼</span>
      </div>

      <div className="categories-grid">
        {filteredCategories.map(category => (
          <Link to={`/products/${category.toLowerCase()}`} key={category} className="category-link">
            <CategoryCard categoryName={category} />
          </Link>
        ))}
      </div>
    </div>
  );
};

export default DashboardPage;