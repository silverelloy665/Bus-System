const BASE_URL = 'http://localhost:8080/api';

const api = {
    // Users
    async registerUser(user) {
        const res = await fetch(`${BASE_URL}/users/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });
        if (!res.ok) throw new Error('Registration failed');
        return res.json();
    },
    async loginUser(username, passwordHash) {
        const res = await fetch(`${BASE_URL}/users/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, passwordHash })
        });
        if (!res.ok) throw new Error('Login failed');
        return res.json();
    },
    async updateLocation(id, lat, lng) {
        const params = new URLSearchParams({ lat, lng });
        const res = await fetch(`${BASE_URL}/users/${id}/location?${params}`, { method: 'PUT' });
        return res.json();
    },

    // Buses
    async getAllBuses() {
        const res = await fetch(`${BASE_URL}/buses`);
        if (!res.ok) throw new Error('Failed to fetch buses');
        return res.json();
    },
    async getNearbyBuses(lat, lng) {
        const params = new URLSearchParams({ lat, lng });
        const res = await fetch(`${BASE_URL}/buses/nearby?${params}`);
        if (!res.ok) throw new Error('Failed to fetch nearby buses');
        return res.json();
    },

    // Bookings
    async createBooking(booking) {
        const res = await fetch(`${BASE_URL}/bookings`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(booking)
        });
        if (!res.ok) throw new Error('Failed to create booking');
        return res.json();
    },
    async cancelBooking(id) {
        const res = await fetch(`${BASE_URL}/bookings/${id}/cancel`, { method: 'PUT' });
        if (!res.ok) throw new Error('Failed to cancel booking');
        return res.json();
    },

    // Fares
    async calculateFare(routeId) {
        const res = await fetch(`${BASE_URL}/fares/route/${routeId}/calculate`);
        if (!res.ok) throw new Error('Failed to calculate fare');
        return res.json();
    },
    async validateFare(oldFare, newFare) {
        const params = new URLSearchParams({ oldFare, newFare });
        const res = await fetch(`${BASE_URL}/fares/validate?${params}`);
        if (!res.ok) throw new Error('Failed to validate fare');
        return res.text();
    }
};
