package com.example.mapmodule.Service;
import com.example.mapmodule.model.User;
import com.example.mapmodule.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class userservice {

    @Autowired
    private Userrepository userRepository;

    public userservice(Userrepository userrepository){
        this.userRepository = userrepository;
    }

    public List<User> findUsersWithinRadius(double lat, double lon, double radius) {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .filter(user -> user.isActive() && calculateDistance(lat, lon, user.getLatitude(), user.getLongitude()) <= radius)
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // Kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
