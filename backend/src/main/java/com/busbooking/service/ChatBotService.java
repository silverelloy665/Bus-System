package com.busbooking.service;

import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    public String processQuery(String query) {
        if (query == null) return "I didn't understand that.";
        
        String q = query.toLowerCase();
        if (q.contains("timing") || q.contains("when")) {
            return "Most of our buses run from 6 AM to 11 PM. Please check the booking page for exact schedules between stops.";
        } else if (q.contains("ticket") || q.contains("book")) {
            return "You can book tickets from our Booking panel and pay via Wallet or card.";
        } else if (q.contains("cancel")) {
            return "You can cancel tickets up to 2 hours before the journey from your profile.";
        } else if (q.contains("contact") || q.contains("help")) {
            return "You can reach us at 1800-BUS-HELP or support@busbooking.com";
        } else {
            return "I am a bus assistant bot. Try asking me about 'bus timings', 'booking tickets', or 'cancellations'.";
        }
    }
}
