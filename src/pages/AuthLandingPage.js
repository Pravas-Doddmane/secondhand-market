import React from 'react';
import { Link } from 'react-router-dom';
import './AuthPages.css';

const AuthLandingPage = () => {
  return (
    <div className="auth-container">
      <h1 className="app-name">SecondHandMarket</h1>
      <div className="auth-options">
        <Link to="/login" className="auth-button">
          LOGIN
        </Link>
        <Link to="/signup" className="auth-button signup-button">
          Signup
        </Link>
      </div>
    </div>
  );
};

export default AuthLandingPage;