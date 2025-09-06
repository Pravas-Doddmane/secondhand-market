import React from 'react';
import './CartItemCard.css';

const CartItemCard = ({ item, onDelete }) => {
  return (
    <div className="cart-item-card">
      <div className="card-left">
        <img src={item.imageUrl} alt={item.title} className="product-image-placeholder" />
      </div>
      <div className="card-right">
        <h3 className="product-title">{item.title}</h3>
        <p className="product-description">{item.description}</p>
        <p className="product-category">Category: {item.category}</p>
        <p className="product-price">₹{item.price}</p>
        <button onClick={() => onDelete(item.cartItemId)} className="delete-btn">
          Delete
        </button>
      </div>
    </div>
  );
};

export default CartItemCard;