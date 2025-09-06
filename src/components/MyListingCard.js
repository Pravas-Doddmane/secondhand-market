import React from 'react';
import { Link } from 'react-router-dom';
import './MyListingCard.css';

const MyListingCard = ({ product, onDelete }) => {
  return (
    <div className="my-listing-card">
      <div className="card-image-container">
        <img src={product.imageUrl} alt={product.title} className="product-image-placeholder" />
      </div>
      <div className="card-info">
        <h3>{product.title}</h3>
        <p className="description">{product.description}</p>
        <p className="category">Category: {product.category}</p>
        <p className="price">₹{product.price}</p>
      </div>
      <div className="card-actions">
        <Link to={`/products/edit/${product.id}`} className="edit-btn">Edit</Link>
        <button onClick={() => onDelete(product.id)} className="delete-btn">Delete</button>
      </div>
    </div>
  );
};

export default MyListingCard;