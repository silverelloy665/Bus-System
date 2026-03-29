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

    // Initial load
    fetchAndPlotBuses();

    // Connect to WebSocket for real-time updates
    setupBusWebSocket();
}

function setupBusWebSocket() {
    const ws = new WebSocket('ws://localhost:8080/ws/bus-location');
    ws.onmessage = (event) => {
        const bus = JSON.parse(event.data);
        const busId = bus.id || bus.busId;
        if (busMarkers[busId]) {
            busMarkers[busId].setLatLng([bus.currentLat, bus.currentLng]);
            busMarkers[busId].bindPopup(`Bus #${busId} (Route: ${bus.routeId})`).update();
        } else {
            busMarkers[busId] = L.marker([bus.currentLat, bus.currentLng])
                .addTo(map)
                .bindPopup(`Bus #${busId} (Route: ${bus.routeId})`);
        }
    };
    ws.onclose = () => {
        console.log("WebSocket connection closed. Retrying in 5s...");
        setTimeout(setupBusWebSocket, 5000);
    };
}

async function fetchAndPlotBuses() {
    try {
        const buses = await api.getAllBuses();
        buses.forEach(bus => {
            const busId = bus.id || bus.busId;
            if (busMarkers[busId]) {
                busMarkers[busId].setLatLng([bus.currentLat, bus.currentLng]);
            } else {
                // Determine a simple custom icon or use Leaflet default
                busMarkers[busId] = L.marker([bus.currentLat, bus.currentLng])
                    .addTo(map)
                    .bindPopup(`Bus #${busId} (Route: ${bus.routeId})`);
            }
        });
    } catch (e) {
        console.error('Failed to update buses on map:', e);
    }
}

let currentPolylines = [];
let routeMarkers = [];

async function drawBusRoute(routeId, fromStopId, toStopId) {
    // Clear old route
    currentPolylines.forEach(p => map.removeLayer(p));
    routeMarkers.forEach(m => map.removeLayer(m));
    currentPolylines = [];
    routeMarkers = [];

    try {
        const stops = await api.getAllStops();
        // In a real app we'd get only stops for this route and sort them
        // For here we just draw all stops for visual effect since backend route-stops may not be fully implemented
        
        let pathCoords = [];
        stops.forEach((stop, index) => {
            if (stop.lat && stop.lng) {
                pathCoords.push([stop.lat, stop.lng]);
                
                let iconUrl = 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png';
                let label = stop.name;
                
                if (stop.stopId == fromStopId) {
                    iconUrl = 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-gold.png';
                    label = "⭐ Boarding: " + stop.name;
                } else if (stop.stopId == toStopId) {
                    iconUrl = 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png';
                    label = "🏁 Destination: " + stop.name;
                }

                const customIcon = L.icon({
                    iconUrl: iconUrl,
                    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
                    iconSize: [25, 41],
                    iconAnchor: [12, 41],
                    popupAnchor: [1, -34],
                    shadowSize: [41, 41]
                });

                const marker = L.marker([stop.lat, stop.lng], {icon: customIcon})
                    .addTo(map)
                    .bindPopup(label);
                
                routeMarkers.push(marker);
            }
        });

        if (pathCoords.length > 0) {
            const polyline = L.polyline(pathCoords, {color: '#2563eb', weight: 4, opacity: 0.7}).addTo(map);
            currentPolylines.push(polyline);
            map.fitBounds(polyline.getBounds(), {padding: [50, 50]});
        }
    } catch(e) {
        console.error("Error drawing route on map", e);
    }
}
