let map;
let userMarker;
let busMarkers = {};

function initMap(lat = 12.9716, lng = 77.5946) {
    if (!map) {
        map = L.map('map').setView([lat, lng], 13);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors',
            maxZoom: 18
        }).addTo(map);
    } else {
        map.setView([lat, lng], 13);
    }

    if (userMarker) {
        userMarker.setLatLng([lat, lng]);
    } else {
        userMarker = L.marker([lat, lng]).addTo(map).bindPopup("You are here");
    }

    // Auto-refresh bus locations every 10 seconds
    setInterval(fetchAndPlotBuses, 10000);
    fetchAndPlotBuses();
}

async function fetchAndPlotBuses() {
    try {
        const buses = await api.getAllBuses();
        buses.forEach(bus => {
            if (busMarkers[bus.id]) {
                busMarkers[bus.id].setLatLng([bus.currentLat, bus.currentLng]);
            } else {
                // Determine a simple custom icon or use Leaflet default
                busMarkers[bus.id] = L.marker([bus.currentLat, bus.currentLng])
                    .addTo(map)
                    .bindPopup(`Bus #${bus.id} (Route: ${bus.routeId})`);
            }
        });
    } catch (e) {
        console.error('Failed to update buses on map:', e);
    }
}
