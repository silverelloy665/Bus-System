const BASE_URL = 'http://localhost:8080/api';

const api = {
    // Utilities
    notify(title, body) {
        if ('Notification' in window && navigator.serviceWorker) {
            if (Notification.permission === 'granted') {
                navigator.serviceWorker.ready.then(registration => {
                    registration.showNotification(title, {
                        body: body,
                        icon: '/bus-icon.png',
                        vibrate: [200, 100, 200]
                    });
                });
            }
        }
    },

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
    async calculateFare(routeId, busId) {
        let url = `${BASE_URL}/fares/route/${routeId}/calculate`;
        if (busId) {
            url += `?busId=${busId}`;
        }
        const res = await fetch(url);
        if (!res.ok) throw new Error('Failed to calculate fare');
        return res.json();
    },
    async validateFare(oldFare, newFare) {
        const params = new URLSearchParams({ oldFare, newFare });
        const res = await fetch(`${BASE_URL}/fares/validate?${params}`);
        if (!res.ok) throw new Error('Failed to validate fare');
        return res.text();
    },    async saveFare(fare) {
        const res = await fetch(`${BASE_URL}/fares`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(fare)
        });
        if (!res.ok) throw new Error('Failed to save fare');
        return res.json();
    },
    // Routes
    async getAllRoutes() {
        const res = await fetch(`${BASE_URL}/routes`);
        if (!res.ok) return [];
        return res.json();
    },
    // Stops
    async getAllStops() {
        const res = await fetch(`${BASE_URL}/stops`);
        if (!res.ok) throw new Error('Failed to fetch stops');
        return res.json();
    }
};
