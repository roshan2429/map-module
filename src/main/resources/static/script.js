document.getElementById('locationForm').onsubmit = function(event) {
    event.preventDefault(); // Prevent form submission
    const lat = parseFloat(document.getElementById('lat').value);
    const lon = parseFloat(document.getElementById('lon').value);
    const radius = parseFloat(document.getElementById('radius').value);

    // Clear previous map instances
    var container = L.DomUtil.get('mapid');
    if(container != null){
        container._leaflet_id = null;
    }

    // Initialize the map
    var mymap = L.map('mapid').setView([lat, lon], 13);

    // Set up the OpenStreetMap layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap contributors'
    }).addTo(mymap);

    // Add a marker at the given location
    L.marker([lat, lon]).addTo(mymap);

    // Add a circle to represent the radius
    L.circle([lat, lon], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: radius
    }).addTo(mymap);

    // Fetch and add service provider markers

    // Convert radius from meters to kilometers before sending the request
    fetch(`/api/users/within-radius?lat=${lat}&lon=${lon}&radius=${radius/1000}`)

        .then(response => response.json())
        .then(providers => {
            providers.forEach(provider => {
                L.marker([provider.latitude, provider.longitude])
                    .addTo(mymap)
                    .bindPopup(`Name: ${provider.name}`);
            });
        })
        .catch(error => console.error('Error fetching providers:', error));
};
