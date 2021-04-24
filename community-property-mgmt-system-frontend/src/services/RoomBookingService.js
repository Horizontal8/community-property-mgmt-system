import axios from 'axios'
import authHeader from './UserService';

const EVENTS_REST_API_URL='http://localhost:8080/api/events';

class RoomBookingService {
    getEvents(){
        return axios.get(EVENTS_REST_API_URL, { headers: authHeader() });
    }

    createEvent(event){
        return axios.post(EVENTS_REST_API_URL, event, { headers: authHeader() });
    }

    updateEvent(event, eventId){
        return axios.put(EVENTS_REST_API_URL + '/' + eventId, event, { headers: authHeader() });
    }

    deleteEvent(eventId){
        return axios.delete(EVENTS_REST_API_URL + '/' + eventId, { headers: authHeader() });
    }
}

export default new RoomBookingService();