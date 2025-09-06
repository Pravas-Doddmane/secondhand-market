import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/axios';
import CartItemCard from '../components/CartItemCard';
import './CartPage.css';

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const fetchCartItems = async () => {
    setLoading(true);
    try {
      const res = await api.get('/cart');
      setCartItems(res.data);
      setError(null);
    } catch (err) {
      setError("Failed to load cart items. Please log in.");
      console.error("Error fetching cart:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCartItems();
  }, []);

  const handleDelete = async (cartItemId) => {
    try {
      await api.delete(`/cart/${cartItemId}`);
      setCartItems(cartItems.filter(item => item.cartItemId !== cartItemId));
      alert("Item deleted from cart.");
    } catch (err) {
      alert("Failed to delete item from cart.");
      console.error("Delete error:", err);
    }
  };

  const handleCheckout = async () => {
    if (cartItems.length === 0) {
      alert("Your cart is empty.");
      return;
    }
    try {
      await api.post('/orders/checkout');
      alert("Purchase successful! Your cart has been cleared.");
      navigate('/orders');
    } catch (err) {
      alert("Checkout failed. Please try again.");
      console.error("Checkout error:", err);
    }
  };

  if (loading) return <div className="loading-message">Loading cart...</div>;
  if (error) return <div className="error-message">{error}</div>;

  return (
    <div className="cart-container">
      <header className="cart-header">

        <h1 className="app-name">SecondHandMarket</h1>
        <h2>CART</h2>
      </header>
      <div className="cart-list">
        {cartItems.length > 0 ? (
          cartItems.map(item => (
            <CartItemCard
              key={item.cartItemId}
              item={item}
              onDelete={handleDelete}
            />
          ))
        ) : (
          <p className="empty-cart-message">Your cart is empty.</p>
        )}
      </div>
      <div className="buy-button-container">
        <button onClick={handleCheckout} className="pay-and-buy-btn">
          Pay and Buy
        </button>
      </div>
    </div>
  );
};

export default CartPage;