import React from 'react';
import './CategoryCard.css';

const CategoryCard = ({ categoryName }) => {
  return (
    <div className="category-card">
      <div className="category-image-container">
        <div className="image-placeholder-block"></div>
      </div>
      <p className="category-name">{categoryName}</p>
    </div>
  );
};

export default CategoryCard;