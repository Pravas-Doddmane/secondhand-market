import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../api/axios';
import './EditUserPage.css';

const EditUserPage = () => {
  const { user, login } = useAuth();
  const [username, setUsername] = useState(user?.username || '');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [passwordError, setPasswordError] = useState('');

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
    setPasswordError('');
    if (newPassword !== confirmPassword) {
      setPasswordError("Passwords do not match.");
      return;
    }
    // Your backend doesn't have a specific password change endpoint,
    // so for a hackathon, we can simply alert.
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
        <input type="password" placeholder="New password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
        <input type="password" placeholder="Confirm password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
        <button type="submit">Save</button>
        {passwordError && <p className="error">{passwordError}</p>}
      </form>
    </div>
  );
};

export default EditUserPage;