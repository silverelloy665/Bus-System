// Service Worker for features (Feature 21)
self.addEventListener('install', event => {
    console.log('Service Worker installed!');
    self.skipWaiting();
});

self.addEventListener('activate', event => {
    console.log('Service Worker activated!');
});

self.addEventListener('push', function(event) {
    const data = event.data ? event.data.json() : { title: 'Bus Booking Sys', body: 'New notification!' };
    
    const options = {
        body: data.body,
        icon: '/bus-icon.png', // Fallback icon
        vibrate: [100, 50, 100],
        data: {
            dateOfArrival: Date.now(),
            primaryKey: '1'
        }
    };
    
    event.waitUntil(
        self.registration.showNotification(data.title, options)
    );
});
