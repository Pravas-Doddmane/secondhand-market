import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import AuthLandingPage from './pages/AuthLandingPage';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import DashboardPage from './pages/DashboardPage';
import ProductsByCategoryPage from './pages/ProductsByCategoryPage';
import ProfilePage from './pages/ProfilePage';
import EditUserPage from './pages/EditUserPage';
import AddProductPage from './pages/AddProductPage';
import EditProductPage from './pages/EditProductPage';
import CartPage from './pages/CartPage';
import PreviousOrdersPage from './pages/PreviousOrdersPage';

const ProtectedRoute = ({ children }) => {
    const { user, loading } = useAuth();
    if (loading) {
        return <div>Loading...</div>;
    }
    return user ? children : <Navigate to="/login" />;
};

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<AuthLandingPage />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/signup" element={<SignupPage />} />

                    {/* Protected Routes (require login) */}
                    <Route path="/dashboard" element={<ProtectedRoute><DashboardPage /></ProtectedRoute>} />
                    <Route path="/products/:category" element={<ProtectedRoute><ProductsByCategoryPage /></ProtectedRoute>} />
                    <Route path="/profile" element={<ProtectedRoute><ProfilePage /></ProtectedRoute>} />
                    <Route path="/profile/edit" element={<ProtectedRoute><EditUserPage /></ProtectedRoute>} />
                    <Route path="/products/new" element={<ProtectedRoute><AddProductPage /></ProtectedRoute>} />
                    <Route path="/products/edit/:id" element={<ProtectedRoute><EditProductPage /></ProtectedRoute>} />
                    <Route path="/cart" element={<ProtectedRoute><CartPage /></ProtectedRoute>} />
                    <Route path="/orders" element={<ProtectedRoute><PreviousOrdersPage /></ProtectedRoute>} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

export default App;