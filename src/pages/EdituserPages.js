import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../api/axios';
import './EditUserPage.css';

const EditUserPage = () => {
  const { user } = useAuth();
  const [username, setUsername] = useState(user?.username || '');
  const [usernameError, setUsernameError] = useState('');

  const handleUsernameSubmit = async (e) => {
    e.preventDefault();
    setUsernameError('');
    if (username === user.username) {
      setUsernameError("Username is the same as current.");
      return;
    }
    try {
      await api.put(`/users/me`, { username });
      alert("Username updated successfully!");
    } catch (err) {
      setUsernameError(err.response?.data?.error || "Failed to update username.");
    }
  };

  const handlePasswordSubmit = async (e) => {
    e.preventDefault();
    alert("Password update logic is not implemented in the backend yet. A backend endpoint would be needed for this.");
  };

  return (
    <div className="edit-user-container">
      <header className="page-header">
        
        <h1 className="app-name">SecondHandMarket</h1>
      </header>
      
      <h2>Edit</h2>

      <form onSubmit={handleUsernameSubmit} className="edit-form">
        <h3>Username</h3>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        <button type="submit">Save</button>
        {usernameError && <p className="error">{usernameError}</p>}
      </form>

      <form onSubmit={handlePasswordSubmit} className="edit-form">
        <h3>Password</h3>
        <p className="password-update-text">Update</p>
        <input type="password" placeholder="New password" />
        <input type="password" placeholder="Confirm password" />
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default EditUserPage;