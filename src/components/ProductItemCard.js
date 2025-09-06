import React from 'react';
import './ProductItemCard.css';

const ProductItemCard = ({ product, onAddToCart }) => {
  return (
    <div className="product-item-card">
      <div className="card-left">
        <div className="image-placeholder-block"></div>
      </div>
      <div className="card-right">
        <h3 className="product-title">{product.title}</h3>
        <p className="product-description">{product.description}</p>
        <p className="product-category">Category: {product.category}</p>
        <p className="product-price">₹{product.price}</p>
        <button onClick={onAddToCart} className="add-to-cart-btn">ADD TO CART</button>
      </div>
    </div>
  );
};

export default ProductItemCard;