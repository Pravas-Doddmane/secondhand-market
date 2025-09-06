import React, { useState, useEffect } from 'react';
import api from '../api/axios';
import './PreviousOrdersPage.css';

const PreviousOrdersPage = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOrders = async () => {
      setLoading(true);
      try {
        const res = await api.get('/orders');
        setOrders(res.data);
        setError(null);
      } catch (err) {
        setError("Failed to load previous purchases.");
        console.error("Error fetching orders:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, []);

  if (loading) return <div className="loading-message">Loading purchases...</div>;
  if (error) return <div className="error-message">{error}</div>;

  return (
    <div className="orders-container">
      <header className="orders-header">
        
        <h1 className="app-name">SecondHandMarket</h1>
        <h2>Previous Purchases</h2>
      </header>
      <div className="orders-list">
        {orders.length > 0 ? (
          orders.map(order => (
            <div key={order.id} className="order-item-card">
              <div className="card-left">
                <img src={order.product.imageUrl} alt={order.product.title} className="product-image-placeholder" />
              </div>
              <div className="card-right">
                <h3 className="product-title">{order.product.title}</h3>
                <p className="product-description">{order.product.description}</p>
                <p className="product-price">₹{order.product.price} x {order.quantity}</p>
                <button className="bought-btn">Bought</button>
              </div>
            </div>
          ))
        ) : (
          <p className="empty-orders-message">You have no previous purchases.</p>
        )}
      </div>
    </div>
  );
};

export default PreviousOrdersPage;